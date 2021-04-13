<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" 
          prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="<c:url value="/webjars/bootstrap/4.5.3/css/bootstrap.min.css"/>"
              type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-12"
                     style="text-align: center">
                    <h2>Order</h2>
                </div>
            </div>
            
            

                <div class="row" style="padding-bottom: 10px">
                    <div class="col-md-12 col-lg-12"
                         style="text-align: center">
                        <p>Order Time: 
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${orderEntity.orderDate}"/>
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 col-lg-12">
                    <!-- table -->
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <tr>                            
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Price</th>                                
                                    <th>Quantity</th>
                                </tr>
                                <c:forEach var="o" items="${orderDetailList}">  
                                    <tr>
                                        <td>${o.product.name}</td>                                    
                                        <td>
                                            <ol>
                                            <c:forEach items="${o.product.images}" var="image">
                                                <li style="list-style: none">
                                                    <img src="<c:url value="/resources/images/${image.name}"/>" width="70px" height="70px">
                                                </li><br>
                                            </c:forEach>
                                            </ol>
                                        </td>
                                        <td>
                                            <fmt:setLocale value="vi_VN" scope="session"/>
                                            <fmt:formatNumber type="currency"
                                                              value="${o.product.price}"/>
                                        </td>
                                        <td>${o.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    <!-- end table -->
                    </div>
                </div>  

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6 col-lg-6">
                        
                    </div>

                    <div class="col-md-6 col-lg-6" style="text-align: right">
                        <p>Total Price: 
                            <fmt:setLocale value="vi_VN" scope="session"/>
                            <fmt:formatNumber type="currency" value="${orderEntity.totalPrice}"/>
                        </p>
                    </div>
                </div>
            
        </div>
    </div>
</body>
</html>
