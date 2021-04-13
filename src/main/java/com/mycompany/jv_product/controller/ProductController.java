/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.controller;


import com.mycompany.jv_product.entities.ImageEntity;
import com.mycompany.jv_product.entities.OrderDetailEntity;
import com.mycompany.jv_product.entities.OrderEntity;
import com.mycompany.jv_product.entities.ProductEntity;
import com.mycompany.jv_product.service.CategoryService;
import com.mycompany.jv_product.service.ImageService;
import com.mycompany.jv_product.service.OrderDetailService;
import com.mycompany.jv_product.service.OrderService;
import com.mycompany.jv_product.service.ProductService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TIEN
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderDetailService orderDetailService;
    
    @Autowired
    public JavaMailSender emailSender;
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "home";
    }
    
    @RequestMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("action", "add");
        model.addAttribute("categories", categoryService.getCategories());
        return "product";
    }
    
    @RequestMapping(value = "/result-product/{action}", method = RequestMethod.POST)
    public String result(Model model,
            @Valid @ModelAttribute("product") ProductEntity product,
            BindingResult result,
            @PathVariable("action") String action,
            @RequestParam("files") MultipartFile[] files,
            HttpServletRequest request) {
        if(result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("action", action);
            model.addAttribute("categories", categoryService.getCategories());
            return "product";
        } else {
        
            //save table with relative one first
            productService.saveProduct(product);

            for (MultipartFile file : files) {                
                if (!file.isEmpty()) {
                    ImageEntity imageEntity = new ImageEntity();
                    //Create custom filename 
                    String fileName = file.getOriginalFilename();
                    String newFileName = "";

                    //check image name exsits or not           
                    if (imageService.getListImageNameLike(fileName).isEmpty()) {
                        newFileName = fileName;
                    } else {
                        boolean cont = true;
                        do {
                            newFileName = RandomStringUtils.randomAlphabetic(4) + '_' + fileName;
                            if (imageService.getListImageNameLike(newFileName).isEmpty()) {
                               cont = false;
                            }
                        } while (cont);
                    }

                    imageEntity.setName(newFileName);
                    imageEntity.setProduct(product);
                    imageService.saveImage(imageEntity);

                    //save file to server
                    try {
                        byte[] bytes = file.getBytes();
                        ServletContext context = request.getServletContext();
                        String pathUrl = context.getRealPath("/images");
                        
                        int index = pathUrl.indexOf("target");
                        String pathFolder = pathUrl.substring(0, index) + "\\src\\main\\webapp\\resources\\images";                    
                        Path path = Paths.get(pathFolder + File.separator + newFileName);
                        Files.write(path, bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return "redirect:/home";
        }
    }
    
    @RequestMapping(value = "/result-product/{action}", method = RequestMethod.GET)
    public String resultProductAction(Model model,
            @ModelAttribute("product") ProductEntity product,
            @PathVariable("action") String action) {  
        model.addAttribute("product", product);
        model.addAttribute("action", action);
        model.addAttribute("categories", categoryService.getCategories());
        return "product";
    }
    
    @RequestMapping("/edit-product/{productId}")
    public String editProduct(Model model, @PathVariable("productId") int productId) {
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("action", "edit");
        model.addAttribute("categories", categoryService.getCategories());
        return "product";
    }
    
    @RequestMapping("/delete-image/{productId}/{imageId}")
    public String deleteImage(Model model,
            @PathVariable("productId") int productId,
            @PathVariable("imageId") int imageId,
            HttpServletRequest request) {
        
        //delete file on server
        try {
            ServletContext context = request.getServletContext();
            String pathUrl = context.getRealPath("/images");
            //file and image have same name
            String fileName = imageService.getImagetById(imageId).getName();

            int index = pathUrl.indexOf("target");
            String pathFolder = pathUrl.substring(0, index) + "\\src\\main\\webapp\\resources\\images";

            Path path = Paths.get(pathFolder + File.separator + fileName);
            
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //delete image in database
        imageService.deleteImage(imageId);
        
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("action", "edit");
        model.addAttribute("categories", categoryService.getCategories());
        return "product";
    }
    
    @RequestMapping("/delete-product/{productId}")
    public String deleteProduct(Model model, 
            @PathVariable("productId") int productId,
            HttpServletRequest request) {  
        
        //delete file on server
        for(ImageEntity i : imageService.getImagesOfProductId(productId)) {
            try {
                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");
                //file and image have same name

                String fileName = i.getName();

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "\\src\\main\\webapp\\resources\\images";

                Path path = Paths.get(pathFolder + File.separator + fileName);

                Files.delete(path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        imageService.deleteAllImageWithSameProductId(productId);
        productService.deleteProduct(productId);
        
        return "redirect:/home";
    }
    
    @RequestMapping("/order-product/{productId}")
    public String orderProduct(Model model, HttpSession session,
            @PathVariable("productId") int productId){
        
        OrderEntity orderSession = (OrderEntity) session.getAttribute("orderSession");
        if (orderSession == null) {
            
            OrderEntity orderEntity = new OrderEntity();
            ProductEntity productEntity = productService.getProductById(productId);

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setProduct(productEntity);
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setPrice(productEntity.getPrice());
            orderDetailEntity.setQuantity(1);
            
            HashMap<Integer, OrderDetailEntity> orderDetailList = new HashMap<>();
            orderDetailList.put(productId, orderDetailEntity);

            orderEntity.setOrderDate(new Date());
            orderEntity.setTotalPrice(totalPrice(orderDetailList));

            session.setAttribute("orderDetailList", orderDetailList);
            session.setAttribute("orderSession", orderEntity);
            
        } else {
            HashMap<Integer, OrderDetailEntity> orderDetailList = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("orderDetailList");
            
            if(orderDetailList.containsKey(productId)) {
                orderDetailList.get(productId).setQuantity(orderDetailList.get(productId).getQuantity()+1);
            } else {
                ProductEntity productEntity = productService.getProductById(productId);

                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setProduct(productEntity);
                orderDetailEntity.setOrder(orderSession);
                orderDetailEntity.setPrice(productEntity.getPrice());
                orderDetailEntity.setQuantity(1);
                orderDetailList.put(productId, orderDetailEntity);                
            }
                
            orderSession.setOrderDate(new Date());
            orderSession.setTotalPrice(totalPrice(orderDetailList));    

            session.setAttribute("orderDetailList", orderDetailList);
            session.setAttribute("orderSession", orderSession);
        }
        return "redirect:/order";
    }
    
    public double totalPrice(HashMap<Integer, OrderDetailEntity> orderDetailList) {
        double totalPrice = 0;
        for(Map.Entry<Integer, OrderDetailEntity> o : orderDetailList.entrySet()) {
            totalPrice += o.getValue().getPrice() * o.getValue().getQuantity();
        }
        return totalPrice;
    }
    
    @RequestMapping("/remove-product/{productId}")
    public String removeOrderDetail(HttpSession session,
            @PathVariable("productId") int productId) {
        OrderEntity orderSession = (OrderEntity) session.getAttribute("orderSession");
        HashMap<Integer, OrderDetailEntity> orderDetailList = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("orderDetailList");
        orderDetailList.remove(productId);
        
        orderSession.setOrderDate(new Date());
        orderSession.setTotalPrice(totalPrice(orderDetailList));    


        session.setAttribute("orderDetailList", orderDetailList);
        session.setAttribute("orderSession", orderSession);
        return "order";
    }
    
    @RequestMapping("/customer-info")
    public String fillOutCustomerInfo(Model model) {
        model.addAttribute("order", new OrderEntity());
        return "customerInfo";
    }
    
    @RequestMapping(value = "/result-order", method = RequestMethod.POST)
    public String resultOrder(Model model, HttpSession session,
                @Valid @ModelAttribute("order") OrderEntity orderEntity,
                BindingResult result) throws MessagingException {
        if(result.hasErrors()) {
            model.addAttribute("order", orderEntity);
            return "customerInfo";
        } else {
            HashMap<Integer, OrderDetailEntity> orderDetailList = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("orderDetailList");

            orderEntity.setOrderDate(new Date());
            orderEntity.setTotalPrice(totalPrice(orderDetailList));
            orderService.saveOrder(orderEntity);
            for (Map.Entry<Integer, OrderDetailEntity> o : orderDetailList.entrySet()) {
                o.getValue().setOrder(orderEntity);
                orderDetailService.saveOrderDetail(o.getValue());
            }       
            
            if(orderEntity.getCustomerEmail() != null) {
                //Create a Mail Message
                MimeMessage message = emailSender.createMimeMessage();
                boolean multipart = true;
                MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
                String htmlMsg = "<h3>Click bellow link to view your order detail:</h3>"
                        + "http://localhost:8080/JV_Product/detailsOfOrder/" + orderEntity.getId();
                message.setContent(htmlMsg, "text/html");
                helper.setTo(orderEntity.getCustomerEmail());
                helper.setSubject("Order Informational Mail To Customer");

                //Send Message
                emailSender.send(message);
            }
            
            model.addAttribute("orders", orderService.getOrders());
            session.invalidate();
            return "redirect:/orderList";
        }
    }
    
    @RequestMapping(value = "/result-order", method = RequestMethod.GET)
    public String resultOrder(Model model) {
        model.addAttribute("order", new OrderEntity());
        return "customerInfo";
    }
    
    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "orderList";
    }
    
    @RequestMapping(value = "/order-quantity", method = RequestMethod.POST)
    public String editOrderQuantity(HttpSession session,
            @RequestParam("quantity") int quantity,
            @RequestParam("product.id") int productId) {
        OrderEntity orderSession = (OrderEntity) session.getAttribute("orderSession");
        HashMap<Integer, OrderDetailEntity> orderDetailList = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("orderDetailList");
        orderDetailList.get(productId).setQuantity(quantity);
        
        orderSession.setOrderDate(new Date());
        orderSession.setTotalPrice(totalPrice(orderDetailList));    


        session.setAttribute("orderDetailList", orderDetailList);
        session.setAttribute("orderSession", orderSession);
        
        return "redirect:/order";
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        return "order";
    }
    
    @RequestMapping("detailsOfOrder/{orderId}") 
    public String detailsOfOrder(Model model, @PathVariable("orderId") int orderId) {
        OrderEntity orderEntity = orderService.getOrderById(orderId);
        List<OrderDetailEntity> orderDetailList = orderDetailService.findOrderDetailsByOrder(orderEntity);
        model.addAttribute("orderEntity", orderEntity);
        model.addAttribute("orderDetailList", orderDetailList);
        return "detailsOfOrder";
    }
    
}
