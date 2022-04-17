package com.bookstore.dao;

import com.bookstore.pojo.Book;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * ClassName: BookDao
 * Description:
 * date: 2022/1/29 15:07
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin,Integer pageSize);

    public Integer queryForPageTotalCountByPrice(int min, int max);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
