/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.GenreDao;
import com.mycompany.elibrary_project.model.Genre;
import java.util.List;

/**
 *
 * @author user
 */
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getGenreList() throws Exception {

        return genreDao.getGenreList();
    }

    @Override
    public void addGenre(Genre genre) throws Exception {
        genreDao.addGenre(genre);
    }

    @Override
    public Genre getGenreById(long genreId) throws Exception {
        return genreDao.getGenreById(genreId);
    }

    @Override
    public void updateGenre(Genre genre) throws Exception {
        genreDao.updateGenre(genre);
    }

    @Override
    public void deleteGenre(long genreId) throws Exception {
        genreDao.deleteGenre(genreId);
    }

    @Override
    public List<Genre> searchGenreData(String keyword) throws Exception {
        return genreDao.searchGenreData(keyword);
    }

}
