/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.BookDao;
import com.mycompany.elibrary_project.model.Book;
import java.util.List;

/**
 *
 * @author user
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getBookList() throws Exception {

        return bookDao.getBookList();
    }

    @Override
    public void addBook(Book book) throws Exception {
        bookDao.addBook(book);
    }

    @Override
    public Book getBookById(long bookId) throws Exception {

        return bookDao.getBookById(bookId);
    }

    @Override
    public void updateBook(Book book) throws Exception {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(long bookId) throws Exception {
        bookDao.deleteBook(bookId);
    }

    @Override
    public List<Book> searchBookData(String keyword) throws Exception {
        return bookDao.searchBookData(keyword);
    }

}
