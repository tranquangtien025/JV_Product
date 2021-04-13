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
                    <c:if test="${action == 'add'}">
                        <h2>Add Product</h2>
                    </c:if>
                    <c:if test="${action == 'edit'}">
                        <h2>Edit Product</h2>
                    </c:if>
                </div>
            </div>

            <mvc:form id="productForm" action="${pageContext.request.contextPath}/result-product/${action}"
                      method="post" 
                      modelAttribute="product"
                      enctype="multipart/form-data"
                      >
                
                <c:if test="${action == 'edit'}">
                    <input type="text" name="id" 
                           value="${product.id}" hidden>
                </c:if>
                    
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="nameId">Name</label>
                            <input type="text" id="nameId"
                                   class="form-control" name="name"
                                   value="${product.name}"/>
                        </div>
                    </div>
                        
                    <div class="col-md-6 col-lg-6">
                        <br>
                        <p style="color: red">
                            <mvc:errors path="name"/>
                        </p>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="priceId">Price</label>
                            <input type="number" id="priceId"
                                   class="form-control" name="price"
                                   value="${product.price}"/>
                        </div>
                    </div>
                        
                    <div class="col-md-6 col-lg-6">
                        <br>
                        <p style="color: red">
                            <mvc:errors path="price"/>
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category.id" class="form-control">
                                <c:forEach var="c" items="${categories}">
                                    <c:if test="${product.category.id == c.id}">
                                        <option value="${c.id}" selected>${c.name}</option>
                                    </c:if>
                                    <c:if test="${product.category.id != c.id}">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="file">Image</label>     
                            
                            <br>
                            <input name="files" id="file" type="file" 
                                   accept="image/png, image/jpg, image/gif" 
                                   multiple="multiple"/>
                        </div>
                    </div>
                </div>        
                        
                
            </mvc:form>
            
            <c:if test="${action == 'edit'}">
                <table>
                <tr>
                <c:forEach items="${product.images}" var="image">
                    <td>
                        <img src="<c:url value="/resources/images/${image.name}"/>" width="100px" height="100px">
                        <br>&nbsp;&nbsp;                                       
                        <button onclick="location.href = '<c:url 
                                value="/delete-image/${product.id}/${image.id}"/>'"
                                class="btn btn-danger">
                            Delete
                        </button>
                    </td>
                </c:forEach>
                </tr>
                </table>
            </c:if>
                    
            <div class="row">
                <div class="col-md-12 col-lg-12" 
                     style="text-align: center">
                    <button form="productForm" type="submit" 
                            class="btn btn-success">Submit</button>
                </div>
            </div>
        </div>
    </body>
</html>
