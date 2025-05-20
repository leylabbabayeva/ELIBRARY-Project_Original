/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Author;
import com.mycompany.elibrary_project.model.Book;
import com.mycompany.elibrary_project.model.Genre;
import com.mycompany.elibrary_project.model.Language;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class BookDaoImpl implements BookDao {

    @Override
    public List<Book> getBookList() throws Exception {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT B.ID,\n"
                + "       B.NAME AS BOOK_NAME,\n"
                + "       A.ID AS AUTHOR_ID,\n"
                + "       A.NAME AS AUTHOR_NAME,\n"
                + "       A.SURNAME AS AUTHOR_SURNAME,\n"
                + "       G.ID AS GENRE_ID,\n"
                + "       G.NAME AS GENRE_NAME,\n"
                + "       L.ID AS LANGUAGE_ID,\n"
                + "       L.NAME AS LANGUAGE_NAME\n"
                + "FROM BOOK B\n"
                + "     INNER JOIN AUTHOR A ON B.AUTHOR_ID = A.ID\n"
                + "     INNER JOIN GENRE G ON B.GENRE_ID = G.ID\n"
                + "     INNER JOIN LANGUAGE L ON B.LANGUAGE_ID = L.ID\n"
                + "WHERE B.ACTIVE = 1 ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("ID"));
                book.setName(rs.getString("BOOK_NAME"));

                Author author = new Author();
                author.setId(rs.getLong("AUTHOR_ID"));
                author.setName(rs.getString("AUTHOR_NAME"));
                author.setSurname(rs.getString("AUTHOR_SURNAME"));
                book.setAuthor(author);

                Genre genre = new Genre();
                genre.setId(rs.getLong("GENRE_ID"));
                genre.setName(rs.getString("GENRE_NAME"));
                book.setGenre(genre);

                Language language = new Language();
                language.setId(rs.getLong("LANGUAGE_ID"));
                language.setName(rs.getString("LANGUAGE_NAME"));
                book.setLanguage(language);

                bookList.add(book);
            }
        }
        return bookList;
    }

    @Override
    public void addBook(Book book) throws Exception {
        String sql = "INSERT INTO BOOK(ID, NAME, AUTHOR_ID, GENRE_ID, LANGUAGE_ID)\n"
                + "VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.getName());
            ps.setLong(2, book.getAuthor().getId());
            ps.setLong(3, book.getGenre().getId());
            ps.setLong(4, book.getLanguage().getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public Book getBookById(long bookId) throws Exception {
        Book book = new Book();
        String sql = "SELECT B.ID,\n"
                + "       B.NAME AS BOOK_NAME,\n"
                + "       A.ID AS AUTHOR_ID,\n"
                + "       A.NAME AS AUTHOR_NAME,\n"
                + "       A.SURNAME AS AUTHOR_SURNAME,\n"
                + "       G.ID AS GENRE_ID,\n"
                + "       G.NAME AS GENRE_NAME,\n"
                + "       L.ID AS LANGUAGE_ID,\n"
                + "       L.NAME AS LANGUAGE_NAME\n"
                + "FROM BOOK B\n"
                + "     INNER JOIN AUTHOR A ON B.AUTHOR_ID = A.ID\n"
                + "     INNER JOIN GENRE G ON B.GENRE_ID = G.ID\n"
                + "     INNER JOIN LANGUAGE L ON B.LANGUAGE_ID = L.ID\n"
                + "WHERE B.ACTIVE = 1 AND B.ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setId(rs.getLong("ID"));
                book.setName(rs.getString("BOOK_NAME"));

                Author author = new Author();
                author.setId(rs.getLong("AUTHOR_ID"));
                author.setName(rs.getString("AUTHOR_NAME"));
                author.setSurname(rs.getString("AUTHOR_SURNAME"));
                book.setAuthor(author);

                Genre genre = new Genre();
                genre.setId(rs.getLong("GENRE_ID"));
                genre.setName(rs.getString("GENRE_NAME"));
                book.setGenre(genre);

                Language language = new Language();
                language.setId(rs.getLong("LANGUAGE_ID"));
                language.setName(rs.getString("LANGUAGE_NAME"));
                book.setLanguage(language);

            }
        }
        return book;
    }

    @Override
    public void updateBook(Book book) throws Exception {
        String sql = "UPDATE BOOK SET AUTHOR_ID = ?, GENRE_ID = ?, LANGUAGE_ID = ?, NAME = ? WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(4, book.getName());
            ps.setLong(1, book.getAuthor().getId());
            ps.setLong(2, book.getGenre().getId());
            ps.setLong(3, book.getLanguage().getId());
            ps.setLong(5, book.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteBook(long bookId) throws Exception {
        String sql = "UPDATE BOOK SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Book> searchBookData(String keyword) throws Exception {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT b.ID, b.NAME, b.GENRE_ID, b.LANGUAGE_ID, b.AUTHOR_ID, "
                + "a.NAME AS AUTHOR_NAME, a.SURNAME AS AUTHOR_SURNAME, "
                + "g.NAME AS GENRE_NAME, l.NAME AS LANGUAGE_NAME "
                + "FROM BOOK b "
                + "JOIN AUTHOR a ON b.AUTHOR_ID = a.ID "
                + "JOIN GENRE g ON b.GENRE_ID = g.ID "
                + "JOIN LANGUAGE l ON b.LANGUAGE_ID = l.ID "
                + "WHERE b.ACTIVE = 1 AND (LOWER(b.NAME) LIKE LOWER(?) "
                + "OR LOWER(a.NAME) LIKE LOWER(?) OR LOWER(a.SURNAME) LIKE LOWER(?)) "
                + "ORDER BY b.ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getLong("ID"));
                    book.setName(rs.getString("NAME"));

                    // Set the Author object in the Book
                    Author author = new Author();
                    author.setId(rs.getLong("AUTHOR_ID"));
                    author.setName(rs.getString("AUTHOR_NAME"));
                    author.setSurname(rs.getString("AUTHOR_SURNAME"));
                    book.setAuthor(author);

                    // Set the Genre object in the Book
                    Genre genre = new Genre();
                    genre.setId(rs.getLong("GENRE_ID"));
                    genre.setName(rs.getString("GENRE_NAME"));
                    book.setGenre(genre);

                    // Set the Language object in the Book
                    Language language = new Language();
                    language.setId(rs.getLong("LANGUAGE_ID"));
                    language.setName(rs.getString("LANGUAGE_NAME"));
                    book.setLanguage(language);

                    bookList.add(book);
                }
            }
        }

        return bookList;
    }

}
