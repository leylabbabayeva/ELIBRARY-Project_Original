/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.model.Book;
import java.util.List;

/**
 *
 * @author user
 */
public interface BookService {
    
    List<Book> getBookList() throws Exception;
    
    // add new book
    void addBook(Book book) throws Exception;
    
    // update book data
    Book getBookById(long bookId) throws Exception;
    
    void updateBook(Book book) throws Exception;
    
    // delete book
    void deleteBook(long bookId) throws Exception;
    
    // search book
    List<Book>  searchBookData(String keyword) throws Exception;
}
