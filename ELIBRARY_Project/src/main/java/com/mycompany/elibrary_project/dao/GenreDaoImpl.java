/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class GenreDaoImpl implements GenreDao {

    @Override
    public List<Genre> getGenreList() throws Exception {
        List<Genre> genreList = new ArrayList<>();
        String sql = "SELECT * FROM GENRE\n"
                + "WHERE ACTIVE = 1 ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Genre g = new Genre();
                g.setId(rs.getLong("ID"));
                g.setName(rs.getString("NAME"));
                genreList.add(g);
            }
        }
        return genreList;
    }

    @Override
    public void addGenre(Genre genre) throws Exception {
        String sql = "INSERT INTO GENRE(ID, NAME)\n"
                + "VALUES(GENRE_SEQ.NEXTVAL, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, genre.getName()); //first(number) is order of questions in sql
            ps.execute(); //to upload data in sql
            c.commit();
        }
    }

    @Override
    public Genre getGenreById(long genreId) throws Exception {
        Genre g = new Genre();
        String sql = "SELECT * FROM GENRE\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, genreId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                g.setId(rs.getLong("ID"));
                g.setName(rs.getString("NAME"));
            }
        }
        return g;
    }

    @Override
    public void updateGenre(Genre genre) throws Exception {
        String sql = "UPDATE GENRE SET NAME = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, genre.getName()); //first(number) is order of questions in sql
            ps.setLong(2, genre.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteGenre(long genreId) throws Exception {
        String sql = "UPDATE GENRE SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, genreId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Genre> searchGenreData(String keyword) throws Exception {
        List<Genre> genreList = new ArrayList<>();
        String sql = "SELECT * FROM GENRE\n"
                + "WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER(?)) ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genre g = new Genre();
                g.setId(rs.getLong("ID"));
                g.setName(rs.getString("NAME"));
                genreList.add(g);
            }
        }

        return genreList;
    }
}

