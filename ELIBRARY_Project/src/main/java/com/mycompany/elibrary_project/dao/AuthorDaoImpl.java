package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public List<Author> getAuthorList() throws Exception {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM AUTHOR\n"
                + "WHERE ACTIVE = 1 ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Author a = new Author();
                a.setId(rs.getLong("ID"));
                a.setName(rs.getString("NAME"));
                a.setSurname(rs.getString("SURNAME"));
                authorList.add(a);
            }
        }

        return authorList;
    }

    @Override
    public void addAutor(Author author) throws Exception {
        String sql = "INSERT INTO AUTHOR(ID, NAME, SURNAME)\n"
                + "VALUES(AUTHOR_SEQ.NEXTVAL, ?, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, author.getName()); //first(number) is order of questions in sql
            ps.setString(2, author.getSurname());
            ps.execute(); //to upload data in sql
            c.commit();
        }
    }

    @Override
    public Author getAuthorById(long authorId) throws Exception {
        Author a = new Author();
        String sql = "SELECT * FROM AUTHOR\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, authorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setId(rs.getLong("ID"));
                a.setName(rs.getString("NAME"));
                a.setSurname(rs.getString("SURNAME"));
            }
        }
        return a;
    }

    @Override
    public void updateAuthor(Author author) throws Exception {
        String sql = "UPDATE AUTHOR SET NAME = ?, SURNAME = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, author.getName()); //first(number) is order of questions in sql
            ps.setString(2, author.getSurname());
            ps.setLong(3, author.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteAuthor(long authorId) throws Exception {
        String sql = "UPDATE AUTHOR SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, authorId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Author> searchAuthorData(String keyword) throws Exception {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM AUTHOR\n"
                + "WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?)) ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author a = new Author();
                a.setId(rs.getLong("ID"));
                a.setName(rs.getString("NAME"));
                a.setSurname(rs.getString("SURNAME"));
                authorList.add(a);
            }
        }

        return authorList;
    }

}
