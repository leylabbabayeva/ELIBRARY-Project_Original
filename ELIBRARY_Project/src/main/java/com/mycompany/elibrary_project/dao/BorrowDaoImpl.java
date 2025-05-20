/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Author;
import com.mycompany.elibrary_project.model.Book;
import com.mycompany.elibrary_project.model.Borrow;
import com.mycompany.elibrary_project.model.Member;
import com.mycompany.elibrary_project.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author user
 */
public class BorrowDaoImpl implements BorrowDao {

    @Override
    public List<Borrow> getBorrowList() throws Exception {
        List<Borrow> borrowList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    B.ID AS BORROW_ID, \n"
                + "    BK.ID AS BOOK_ID, BK.NAME AS BOOK_NAME,\n"
                + "    A.ID AS AUTHOR_ID, A.NAME AS AUTHOR_NAME, A.SURNAME AS AUTHOR_SURNAME,\n"
                + "    M.ID AS MEMBER_ID, M.NAME AS MEMBER_NAME, M.SURNAME AS MEMBER_SURNAME,\n"
                + "    S.ID AS STAFF_ID, S.NAME AS STAFF_NAME, S.SURNAME AS STAFF_SURNAME,\n"
                + "    B.BORROW_DATE, B.ACTIVE\n"
                + "FROM BORROW B\n"
                + "INNER JOIN BOOK BK ON B.BOOK_ID = BK.ID\n"
                + "INNER JOIN AUTHOR A ON BK.AUTHOR_ID = A.ID\n"
                + "INNER JOIN MEMBER M ON B.MEMBER_ID = M.ID\n"
                + "INNER JOIN STAFF S ON B.STAFF_ID = S.ID\n"
                + "WHERE B.ACTIVE = 1 ORDER BY B.ID"; // to show data of active borrows

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setId(rs.getLong("BORROW_ID"));
                borrow.setActive(rs.getInt("ACTIVE"));
                borrow.setBorrowDate(rs.getDate("BORROW_DATE"));

                // Book + Author
                Book book = new Book();
                book.setId(rs.getLong("BOOK_ID"));
                book.setName(rs.getString("BOOK_NAME"));
                Author author = new Author();
                author.setId(rs.getLong("AUTHOR_ID"));
                author.setName(rs.getString("AUTHOR_NAME"));
                author.setSurname(rs.getString("AUTHOR_SURNAME"));
                book.setAuthor(author);

                borrow.setBook(book);

                // Member
                Member member = new Member();
                member.setId(rs.getLong("MEMBER_ID"));
                member.setName(rs.getString("MEMBER_NAME"));
                member.setSurname(rs.getString("MEMBER_SURNAME"));

                borrow.setMember(member);

                // Staff
                Staff staff = new Staff();
                staff.setId(rs.getLong("STAFF_ID"));
                staff.setName(rs.getString("STAFF_NAME"));
                staff.setSurname(rs.getString("STAFF_SURNAME"));

                borrow.setStaff(staff);

                borrowList.add(borrow);
            }
        }
        return borrowList;
    }

    @Override
    public void addBorrow(Borrow borrow) throws Exception {
        String sql = "INSERT INTO BORROW(ID, BOOK_ID, MEMBER_ID, STAFF_ID, BORROW_DATE)\n"
                + "VALUES(BORROW_SEQ.NEXTVAL, ?, ?, ?, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, borrow.getBook().getId());
            ps.setLong(2, borrow.getMember().getId());
            ps.setLong(3, borrow.getStaff().getId());
            ps.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            ps.execute();
            c.commit();
        }
    }

    @Override
    public Borrow getBorrowById(long borrowId) throws Exception {
        Borrow borrow = new Borrow();
        String sql = "SELECT \n"
                + "    B.ID AS BORROW_ID, \n"
                + "    BK.ID AS BOOK_ID, BK.NAME AS BOOK_NAME,\n"
                + "    A.ID AS AUTHOR_ID, A.NAME AS AUTHOR_NAME, A.SURNAME AS AUTHOR_SURNAME,\n"
                + "    M.ID AS MEMBER_ID, M.NAME AS MEMBER_NAME, M.SURNAME AS MEMBER_SURNAME,\n"
                + "    S.ID AS STAFF_ID, S.NAME AS STAFF_NAME, S.SURNAME AS STAFF_SURNAME,\n"
                + "    B.BORROW_DATE, B.ACTIVE\n"
                + "FROM BORROW B\n"
                + "INNER JOIN BOOK BK ON B.BOOK_ID = BK.ID\n"
                + "INNER JOIN AUTHOR A ON BK.AUTHOR_ID = A.ID\n"
                + "INNER JOIN MEMBER M ON B.MEMBER_ID = M.ID\n"
                + "INNER JOIN STAFF S ON B.STAFF_ID = S.ID\n"
                + "WHERE B.ACTIVE = 1 AND B.ID = ?"; // to show data of active borrows and late returns(2)

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, borrowId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                borrow.setId(rs.getLong("BORROW_ID"));
                borrow.setActive(rs.getInt("ACTIVE"));
                borrow.setBorrowDate(rs.getDate("BORROW_DATE"));

                // Book + Author
                Book book = new Book();
                book.setId(rs.getLong("BOOK_ID"));
                book.setName(rs.getString("BOOK_NAME"));

                Author author = new Author();
                author.setId(rs.getLong("AUTHOR_ID"));
                author.setName(rs.getString("AUTHOR_NAME"));
                author.setSurname(rs.getString("AUTHOR_SURNAME"));
                book.setAuthor(author);

                borrow.setBook(book);

                // Member
                Member member = new Member();
                member.setId(rs.getLong("MEMBER_ID"));
                member.setName(rs.getString("MEMBER_NAME"));
                member.setSurname(rs.getString("MEMBER_SURNAME"));
                borrow.setMember(member);

                // Staff
                Staff staff = new Staff();
                staff.setId(rs.getLong("STAFF_ID"));
                staff.setName(rs.getString("STAFF_NAME"));
                staff.setSurname(rs.getString("STAFF_SURNAME"));
                borrow.setStaff(staff);

            }
        }
        return borrow;
    }

    @Override
    public void updateBorrow(Borrow borrow) throws Exception {
        String sql = "UPDATE BORROW SET BOOK_ID = ?, MEMBER_ID = ?, STAFF_ID = ?, BORROW_DATE = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, borrow.getBook().getId());
            ps.setLong(2, borrow.getMember().getId());
            ps.setLong(3, borrow.getStaff().getId());
            ps.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            ps.setLong(5, borrow.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteBorrow(long borrowId) throws Exception {
        String sql = "UPDATE BORROW SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, borrowId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Borrow> searchBorrowData(String keyword) throws Exception {
        List<Borrow> borrowList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    B.ID AS BORROW_ID, \n"
                + "    BK.ID AS BOOK_ID, BK.NAME AS BOOK_NAME,\n"
                + "    A.ID AS AUTHOR_ID, A.NAME AS AUTHOR_NAME, A.SURNAME AS AUTHOR_SURNAME,\n"
                + "    M.ID AS MEMBER_ID, M.NAME AS MEMBER_NAME, M.SURNAME AS MEMBER_SURNAME,\n"
                + "    S.ID AS STAFF_ID, S.NAME AS STAFF_NAME, S.SURNAME AS STAFF_SURNAME,\n"
                + "    B.BORROW_DATE, B.ACTIVE\n"
                + "FROM BORROW B\n"
                + "INNER JOIN BOOK BK ON B.BOOK_ID = BK.ID\n"
                + "INNER JOIN AUTHOR A ON BK.AUTHOR_ID = A.ID\n"
                + "INNER JOIN MEMBER M ON B.MEMBER_ID = M.ID\n"
                + "INNER JOIN STAFF S ON B.STAFF_ID = S.ID\n"
                + "WHERE B.ACTIVE = 1 AND (LOWER(BK.NAME) LIKE LOWER(?) "
                + "OR LOWER(M.NAME) LIKE LOWER(?) OR LOWER(M.SURNAME) LIKE LOWER(?)) "
                + "ORDER BY b.ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Borrow borrow = new Borrow();

                    borrow.setId(rs.getLong("BORROW_ID"));
                    borrow.setActive(rs.getInt("ACTIVE"));
                    borrow.setBorrowDate(rs.getDate("BORROW_DATE"));

                    // Book + Author
                    Book book = new Book();
                    book.setId(rs.getLong("BOOK_ID"));
                    book.setName(rs.getString("BOOK_NAME"));
                    Author author = new Author();
                    author.setId(rs.getLong("AUTHOR_ID"));
                    author.setName(rs.getString("AUTHOR_NAME"));
                    author.setSurname(rs.getString("AUTHOR_SURNAME"));
                    book.setAuthor(author);

                    borrow.setBook(book);

                    // Member
                    Member member = new Member();
                    member.setId(rs.getLong("MEMBER_ID"));
                    member.setName(rs.getString("MEMBER_NAME"));
                    member.setSurname(rs.getString("MEMBER_SURNAME"));

                    borrow.setMember(member);

                    // Staff
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("STAFF_ID"));
                    staff.setName(rs.getString("STAFF_NAME"));
                    staff.setSurname(rs.getString("STAFF_SURNAME"));

                    borrow.setStaff(staff);

                    borrowList.add(borrow);
                }
            }
        }
        return borrowList;
    }

}
