package com.bookstore.test;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassName: BookServiceImplTest
 * Description:
 * date: 2022/1/29 16:59
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class BookServiceImplTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "sexy", "sexer", new BigDecimal(111), 89, 899, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(24, "sexy", "user", new BigDecimal(111), 89, 899, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(24));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void pageTest(){
        System.out.println(bookService.page(6, 4));
    }

    @Test
    public void pageByPriceTest(){
        System.out.println(bookService.pageByPrice(6, 4,10,60));
    }
}