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
                    <h2>Order List</h2>
                </div>
            </div>

            <div class="row" style="padding-bottom: 10px">
                <div class="col-md-6 col-lg-6">
                    <button onclick="location.href = '<c:url value="/home"/>'"
                            class="btn btn-info">
                        Return Home
                    </button>
                </div>

                <div class="col-md-6 col-lg-6">

                </div>
            </div>

            <div class="row">
                <div class="col-md-12 col-lg-12">
                <!-- table -->
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <tr>                            
                                <th>Customer Name</th>                                
                                <th>Customer Phone</th>
                                <th>Customer Address</th>
                                <th>Customer Email</th>
                                <th>Order Date</th>
                                <th>Total Price</th>                                
                            </tr>
                            <c:forEach var="o" items="${orders}">
                                <tr>
                                    <td>${o.customerName}</td>                                    
                                    <td>${o.customerPhone}</td>
                                    <td>${o.customerAddress}</td>
                                    <td>${o.customerEmail}</td>
                                    <td>
                                        <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${o.orderDate}"/>
                                    </td>
                                    <td>
                                        <fmt:setLocale value="vi_VN" scope="session"/>
                                        <fmt:formatNumber type="currency"
                                                          value="${o.totalPrice}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                <!-- end table -->
                </div>
            </div>
        </div>
    </div>
</body>
</html>
