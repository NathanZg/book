package com.bookstore.test;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassName: BookDaoImplTest
 * Description:
 * date: 2022/1/29 15:37
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class BookDaoImplTest {

    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "如何傍富婆", "author", new BigDecimal(888), 19999, 100000, null));
    }

    @Test
    public void deleteBookById() {

    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "如何傍富婆", "fucker", new BigDecimal(888), 19999, 100000, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCountTest() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountTestByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 60));
    }

    @Test
    public void queryForPageItemsTest() {
        List<Book> books = bookDao.queryForPageItems(0,4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsTestByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0,4,10,60);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}