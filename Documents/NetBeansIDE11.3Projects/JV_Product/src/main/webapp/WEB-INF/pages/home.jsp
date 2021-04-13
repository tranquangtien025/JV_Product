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
                    <h2>List Products</h2>
                </div>
            </div>

            <div class="row" style="padding-bottom: 10px">
                <div class="col-md-6 col-lg-6">
                    <button onclick="location.href = '<c:url value="/add-product"/>'"
                            class="btn btn-info">
                        Add Product
                    </button>
                </div>

                <div class="col-md-6 col-lg-6">
                    <button onclick="location.href = '<c:url value="/orderList"/>'"
                            class="btn btn-info float-right">
                        Order List
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 col-lg-12">
                <!-- table -->
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <tr>                            
                                <th>Name</th>
                                <th>Price</th>
                                <th>Image</th>
                                <th>Category</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="p" items="${products}">
                                <tr>
                                    <td>${p.name}</td>
                                    <td>
                                        <fmt:setLocale value="vi_VN" scope="session"/>
                                        <fmt:formatNumber type="currency"
                                                          value="${p.price}"/>
                                    </td>
                                    <td>
                                        <ol>
                                        <c:forEach items="${p.images}" var="image">
                                            <li style="list-style: none">
                                                <img src="<c:url value="/resources/images/${image.name}"/>" width="70px" height="70px">
                                            </li><br>
                                        </c:forEach>
                                        </ol>
                                    </td>                                    
                                    <td>${p.category.name}</td>
                                                                        
                                    <td>
                                        <button onclick="location.href = '<c:url 
                                                    value="/order-product/${p.id}"/>'"
                                                    class="btn btn-warning">
                                            Order
                                        </button>
                                                    
                                        <button onclick="location.href = '<c:url 
                                                    value="/edit-product/${p.id}"/>'"
                                                    class="btn btn-info">
                                            Edit
                                        </button>            
                                                    
                                        <button onclick="location.href = '<c:url 
                                                    value="/delete-product/${p.id}"/>'"
                                                    class="btn btn-danger">
                                            Delete
                                        </button>
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
