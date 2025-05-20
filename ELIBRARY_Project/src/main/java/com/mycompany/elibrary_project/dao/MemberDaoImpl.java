/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class MemberDaoImpl implements MemberDao {

    @Override
    public List<Member> getMemberList() throws Exception {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM MEMBER\n"
                + "WHERE ACTIVE = 1 ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getLong("ID"));
                m.setName(rs.getString("NAME"));
                m.setSurname(rs.getString("SURNAME"));
                m.setPhone(rs.getString("PHONE"));
                memberList.add(m);
            }
        }
        return memberList;
    }

    @Override
    public void addMember(Member member) throws Exception {
        String sql = "INSERT INTO MEMBER(ID, NAME, SURNAME, PHONE)\n"
                + "VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getSurname());
            ps.setString(3, member.getPhone());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public Member getMemberById(long memberId) throws Exception {
        Member m = new Member();
        String sql = "SELECT * FROM MEMBER\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, memberId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setId(rs.getLong("ID"));
                m.setName(rs.getString("NAME"));
                m.setSurname(rs.getString("SURNAME"));
                m.setPhone(rs.getString("PHONE"));
            }
        }
        return m;
    }

    @Override
    public void updateMember(Member member) throws Exception {
        String sql = "UPDATE MEMBER SET NAME = ?, SURNAME = ?, PHONE = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getSurname());
            ps.setString(3, member.getPhone());
            ps.setLong(4, member.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteMember(long memberId) throws Exception {
        String sql = "UPDATE MEMBER SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, memberId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Member> searchMemberData(String keyword) throws Exception {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM MEMBER\n"
                + "WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?)) ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getLong("ID"));
                m.setName(rs.getString("NAME"));
                m.setSurname(rs.getString("SURNAME"));
                memberList.add(m);
            }
        }

        return memberList;
    }

}
