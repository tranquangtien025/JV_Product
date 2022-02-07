/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv40_spring_mvc_book.controller;

import com.mycompany.jv40_spring_mvc_book.entities.BookEntity;
import com.mycompany.jv40_spring_mvc_book.service.BookService;
import com.mycompany.jv40_spring_mvc_book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model,
            @RequestParam(name = "mesType",
                    required = false) String mesType,
            @RequestParam(name = "mes",
                    required = false) String mes) {
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("mes", mes);
        model.addAttribute("mesType", mesType);
        return "home";
    }

    @RequestMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("categories",
                categoryService.getCategories());
        model.addAttribute("action", "add");
        return "book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String resultAddBook(Model model,
            @ModelAttribute("book") BookEntity book) {
//        book.getBookDetail().setBook(book);
        book.setBookDetail(book.getBookDetail());
        bookService.save(book);
        return "redirect:/home";
    }

    @RequestMapping("/edit-book/{id}")
    public String editBook(Model model,
            @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("categories",
                categoryService.getCategories());
        model.addAttribute("action", "edit");
        return "book";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String resultEditBook(Model model,
            @ModelAttribute("book") BookEntity book) {
        bookService.save(book);
        return "redirect:/home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchBook(Model model,
            @ModelAttribute("strSearch") String strSearch) {
        model.addAttribute("books", bookService.searchBook(strSearch));
        return "home";
    }

    @RequestMapping("/delete-book/{id}")
    public String deleteBook(Model model,
            @PathVariable("id") int id) {
        if (!bookService.deleteBook(id)) {
            return "redirect:/home?"
                    + "mesType=success&mes=Delete book id: " + id + " success!!!";
        } else {
            return "redirect:/home?"
                    + "mesType=error&mes=Delete book id: " + id + " fail!!!";
        }
    }
}
