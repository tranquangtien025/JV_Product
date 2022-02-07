/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv40_spring_mvc_book.api;

import com.mycompany.jv40_spring_mvc_book.entities.BookEntity;
import com.mycompany.jv40_spring_mvc_book.model.Book;
import com.mycompany.jv40_spring_mvc_book.service.BookService;
import com.mycompany.jv40_spring_mvc_book.utils.ConvertUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class BookAPI {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
        List<BookEntity> bookEntities = bookService.getBooks();
        if (CollectionUtils.isEmpty(bookEntities)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            List<Book> books = new ArrayList<>();
            for (BookEntity bookEntity : bookEntities) {
                books.add(
                        ConvertUtils.convertBookFromBookEntity(bookEntity));
            }
            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(
            @PathVariable("id") int id) {
        BookEntity bookEntity = bookService.findBookById(id);
        if (bookEntity != null && bookEntity.getId() > 0) {
            Book book = ConvertUtils.convertBookFromBookEntity(bookEntity);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(
            @RequestBody BookEntity bookEntity,
            UriComponentsBuilder builder) {
        bookService.save(bookEntity);
        if (bookEntity.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("/api/book/{id}")
                            .buildAndExpand(bookEntity.getId())
                            .toUri()
            );

            return new ResponseEntity(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(
            @RequestBody BookEntity bookEntity,
            UriComponentsBuilder builder) {
        bookService.save(bookEntity);
        if (bookEntity.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("/api/book/{id}")
                            .buildAndExpand(bookEntity.getId())
                            .toUri()
            );

            return new ResponseEntity(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(
            @PathVariable("id") int id) {
        if (!bookService.deleteBook(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> searchBook(
            @ModelAttribute("strSearch") String strSearch) {
        List<BookEntity> bookEntities = bookService.searchBook(strSearch);
        if (CollectionUtils.isEmpty(bookEntities)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            List<Book> books = new ArrayList<>();
            for (BookEntity bookEntity : bookEntities) {
                books.add(
                        ConvertUtils.convertBookFromBookEntity(bookEntity));
            }
            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        }
    }
}
