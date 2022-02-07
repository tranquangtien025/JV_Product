/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv40_spring_mvc_book.repository;

import com.mycompany.jv40_spring_mvc_book.entities.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface BookRepository
        extends CrudRepository<BookEntity, Integer> {

    List<BookEntity>
            findByNameContainingOrAuthorContaining(String name, String author);

    List<BookEntity> findByAuthorContainingOrderByAuthorDesc(String strSearch);

    @Query("Select b from BookEntity b where "
            + "b.category.name Like ?1 and "
            + "b.bookDetail.price < ?2 "
            + "order by b.name desc")
    List<BookEntity> findBookByCategoryAndPrice(String name, double price);

    @Query(value = "Select * from book b"
            + " join book_detail bd on b.book_detail_id = bd.id"
            + " join category c on c.id = b.category_id"
            + " where c.name like ?1"
            + " and bd.price < ?2"
            + " order by b.name desc", nativeQuery = true)
    List<BookEntity> findBookByCategoryAndPriceNative(String name, double price);

    List<BookEntity> findByCategoryNameContainingAndBookDetailPriceLessThan(String name, double price);
    List<BookEntity> findByCategory_NameContainingAndBookDetail_PriceLessThan(String name, double price);
}
