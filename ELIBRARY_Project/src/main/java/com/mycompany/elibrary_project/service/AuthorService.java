/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.model.Author;
import java.util.List;

/**
 *
 * @author user
 */
public interface AuthorService {
    
    List<Author> getAuthorList() throws Exception;
    
    //add new author
    void addAuthor(Author author) throws Exception;
    
    //update Author data
    Author getAuthorById(long authorId) throws Exception;
    
    void updateAuthor(Author author) throws Exception;
    
    //delete author data
    void deleteAuthor(long authorId) throws Exception;
    
    // search author
    List<Author>  searchAuthorData(String keyword) throws Exception;
}
