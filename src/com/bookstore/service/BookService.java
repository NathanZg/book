package com.bookstore.service;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;

import java.util.List;

/**
 * ClassName: BookService
 * Description:
 * date: 2022/1/29 16:52
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(Integer pageNum, Integer pageSize);

    public Page<Book> pageByPrice(int pageNum, int pageSize, int min, int max);
}
