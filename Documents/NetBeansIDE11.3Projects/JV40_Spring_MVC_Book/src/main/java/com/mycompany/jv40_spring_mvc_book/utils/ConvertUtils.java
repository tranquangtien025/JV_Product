/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv40_spring_mvc_book.utils;

import com.mycompany.jv40_spring_mvc_book.entities.BookEntity;
import com.mycompany.jv40_spring_mvc_book.model.Book;
import com.mycompany.jv40_spring_mvc_book.model.BookDetail;
import com.mycompany.jv40_spring_mvc_book.model.Category;

/**
 *
 * @author ASUS
 */
public class ConvertUtils {

    public static Book convertBookFromBookEntity(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setAuthor(bookEntity.getAuthor());

        BookDetail bookDetail = new BookDetail();
        bookDetail.setDescription(bookEntity.getBookDetail().getDescription());
        bookDetail.setId(bookEntity.getBookDetail().getId());
        bookDetail.setIsbn(bookEntity.getBookDetail().getIsbn());
        bookDetail.setNumberOfPage(bookEntity.getBookDetail().getNumberOfPage());
        bookDetail.setPrice(bookEntity.getBookDetail().getPrice());
        bookDetail.setPublishDate(bookEntity.getBookDetail().getPublishDate());

        Category category = new Category();
        category.setId(bookEntity.getCategory().getId());
        category.setName(bookEntity.getCategory().getName());
        category.setDescription(bookEntity.getCategory().getDescription());

        book.setBookDetail(bookDetail);
        book.setCategory(category);

        return book;
    }
}
