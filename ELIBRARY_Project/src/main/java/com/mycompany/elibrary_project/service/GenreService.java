/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.model.Genre;
import java.util.List;

/**
 *
 * @author user
 */
public interface GenreService {
    
    List<Genre> getGenreList() throws Exception;
    
    //add new Genre
    void addGenre(Genre genre) throws Exception;
    
    //update Genre data
    Genre getGenreById(long genreId) throws Exception;
    
    void updateGenre(Genre genre) throws Exception;
    
    //delete Genre data
    void deleteGenre(long genreId) throws Exception;
    
    // search genre
    List<Genre>  searchGenreData(String keyword) throws Exception;
}
