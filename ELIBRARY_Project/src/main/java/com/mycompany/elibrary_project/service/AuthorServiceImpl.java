/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.AuthorDao;
import com.mycompany.elibrary_project.model.Author;
import java.util.List;

/**
 *
 * @author user
 */
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getAuthorList() throws Exception {

        return authorDao.getAuthorList();
    }

    @Override
    public void addAuthor(Author author) throws Exception {
        authorDao.addAutor(author);
    }

    @Override
    public Author getAuthorById(long authorId) throws Exception {
        return authorDao.getAuthorById(authorId);
    }

    @Override
    public void updateAuthor(Author author) throws Exception {
        authorDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(long authorId) throws Exception {
        authorDao.deleteAuthor(authorId);
    }

    @Override
    public List<Author> searchAuthorData(String keyword) throws Exception {
        return authorDao.searchAuthorData(keyword);
    }

}
