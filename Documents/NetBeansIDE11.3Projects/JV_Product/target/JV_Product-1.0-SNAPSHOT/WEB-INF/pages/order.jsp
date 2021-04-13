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
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${orderSession.orderDate}"/>
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
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="o" items="${orderDetailList}">
                                    <tr>
                                        <td>${o.value.product.name}</td>                                    
                                        <td>
                                            <ol>
                                            <c:forEach items="${o.value.product.images}" var="image">
                                                <li style="list-style: none">
                                                    <img src="<c:url value="/resources/images/${image.name}"/>" width="70px" height="70px">
                                                </li><br>
                                            </c:forEach>
                                            </ol>
                                        </td>
                                        <td>
                                            <fmt:setLocale value="vi_VN" scope="session"/>
                                            <fmt:formatNumber type="currency"
                                                              value="${o.value.product.price}"/>
                                        </td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/order-quantity"
                                                method="post">
                                                <div style="display: flex; width: 200px">
                                                    <input type="number" id="quantityId"
                                                        class="form-control" name="quantity"
                                                        min="0" oninput="check(this)"
                                                        value="${o.value.quantity}"/>
                                                    
                                                    <input type="number" id="productId"
                                                        class="form-control" name="product.id"
                                                        value="${o.value.product.id}" hidden/>

                                                    <button type="submit" class="btn btn-warning">
                                                        Update
                                                    </button>
                                                </div>                                                
                                            </form>
                                            <script>
                                                function check(input) {
                                                  if (input.value == 0) {
                                                    input.setCustomValidity('Order quantity must not be zero.');
                                                  } else {
                                                    // input is fine -- reset the error message
                                                    input.setCustomValidity('');
                                                  }
                                                }
                                            </script>
                                        </td>
                                        <td>
                                            <button onclick="location.href = '<c:url value="/remove-product/${o.value.product.id}"/>'"
                                                    class="btn btn-danger">
                                                Remove
                                            </button>
                                        </td>
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
                            <fmt:formatNumber type="currency" value="${orderSession.totalPrice}"/>
                        </p>
                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6 col-lg-6">
                        <button onclick="location.href = '<c:url value="/home"/>'"
                                class="btn btn-info">
                            Continue
                        </button>
                    </div>
                                    
                    <div class="col-md-6 col-lg-6"
                         style="text-align: right">
                        <button onclick="location.href = '<c:url value="/customer-info"/>'"
                                    class="btn btn-success">
                            Next
                        </button>
                    </div>
                </div>
            
        </div>
    </div>
</body>
</html>
