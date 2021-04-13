<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book</title>
        <link href="<c:url value="/webjars/bootstrap/4.5.3/css/bootstrap.min.css"/>"
              type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-12"
                     style="text-align: center">
                    <h2>Customer Information</h2>
                </div>
            </div>

            <mvc:form action="${pageContext.request.contextPath}/result-order"
                      method="post"
                      modelAttribute="order">

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="customerNameId">Customer Name <span style="color: red">(*)</span></label>
                            <input type="text" id="customerNameId"
                                   class="form-control" name="customerName"/>
                        </div>
                    </div>
                    
                    <div class="col-md-6 col-lg-6">
                        <br>
                        <p style="color: red">
                            <mvc:errors path="customerName"/>
                        </p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="customerPhoneId">Customer Phone <span style="color: red">(*)</span></label>
                            <input type="text" id="customerPhoneId"
                                   class="form-control" name="customerPhone"/>
                        </div>
                    </div>
                    
                    <div class="col-md-6 col-lg-6">
                        <br>
                        <p style="color: red">
                            <mvc:errors path="customerPhone"/>
                        </p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="customerAddressId">Customer Address <span style="color: red">(*)</span></label>
                            <input type="text" id="customerAddressId"
                                   class="form-control" name="customerAddress"/>
                        </div>
                    </div>
                    
                    <div class="col-md-6 col-lg-6">
                        <br>
                        <p style="color: red">
                            <mvc:errors path="customerAddress"/>
                        </p>
                    </div>
                </div>
                        
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="customerEmailId">Customer Email</label>
                            <input type="email" id="customerEmailId" pattern="^(.+)@(.+)$"
                                   class="form-control" name="customerEmail"/>
                        </div>
                    </div>
                    
                    <div class="col-md-6 col-lg-6">
                        
                    </div>
                </div>
                        
                <div class="row">
                    <div class="col-md-12 col-lg-12" 
                         style="text-align: center">
                        <button type="submit" 
                                class="btn btn-success">Submit</button>
                    </div>
                </div>
            </mvc:form>
        </div>
    </body>
</html>
