<%-- 
    Document   : book
    Created on : Jan 5, 2021, 8:02:14 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>book</title>
        <link href="<c:url value="/webjars/bootstrap/4.5.3/css/bootstrap.min.css"/>"
              type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-12"
                     style="text-align: center">
                    <c:if test="${action == 'add'}">
                        <h2>Add Book</h2>
                    </c:if>
                    <c:if test="${action == 'edit'}">
                        <h2>Edit Book</h2>
                    </c:if>
                </div>
            </div>

            <mvc:form action="${pageContext.request.contextPath}/${action}"
                      method="post" modelAttribute="book">
                <c:if test="${action == 'edit'}">
                    <input type="text" name="id" 
                           value="${book.id}" hidden />
                </c:if>

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="nameId">Name</label>
                            <input type="text" id="nameId"
                                   class="form-control" name="name"
                                   value="${book.name}"/>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="authorId">Author</label>
                            <input type="text" id="authorId"
                                   class="form-control" name="author"
                                   value="${book.author}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category.id" class="form-control">
                                <c:forEach var="c" items="${categories}">
                                    <c:if test="${book.category.id == c.id}">
                                        <option value="${c.id}" selected>${c.name}</option>
                                    </c:if>
                                    <c:if test="${book.category.id != c.id}">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="isbnId">ISBN</label>
                            <input type="text" id="isbnId"
                                   class="form-control" 
                                   name="bookDetail.isbn"
                                   value="${book.bookDetail.isbn}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="nopId">Number Of Page</label>
                            <input type="text" id="nopId"
                                   class="form-control"
                                   name="bookDetail.numberOfPage"
                                   value="${book.bookDetail.numberOfPage}"/>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="priceId">Price</label>
                            <input type="text" id="priceId"
                                   class="form-control"
                                   name="bookDetail.price"
                                   value="${book.bookDetail.price}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="publishDateId">Publish Date</label>
                            <input type="date" id="publishDateId"
                                   class="form-control"
                                   name="bookDetail.publishDate"
                                   value="${book.bookDetail.publishDate}"/>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="descriptionId">Description</label>
                            <textarea id="descriptionId"
                                      class="form-control"
                                      name="bookDetail.description">${book.bookDetail.description}</textarea>
                        </div>
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
