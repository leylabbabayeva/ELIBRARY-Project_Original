/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class StaffDaoImpl implements StaffDao {

    @Override
    public List<Staff> getStaffList() throws Exception {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM STAFF\n"
                + "WHERE ACTIVE = 1 ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getLong("ID"));
                s.setName(rs.getString("NAME"));
                s.setSurname(rs.getString("SURNAME"));
                s.setPhone(rs.getString("PHONE"));
                staffList.add(s);
            }
        }
        return staffList;
    }

    @Override
    public void addStaff(Staff staff) throws Exception {
        String sql = "INSERT INTO STAFF(ID, NAME, SURNAME, PHONE)\n"
                + "VALUES(STAFF_SEQ.NEXTVAL, ?, ?, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, staff.getName());
            ps.setString(2, staff.getSurname());
            ps.setString(3, staff.getPhone());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public Staff getStaffById(long staffId) throws Exception {
        Staff s = new Staff();
        String sql = "SELECT * FROM STAFF\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, staffId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setId(rs.getLong("ID"));
                s.setName(rs.getString("NAME"));
                s.setSurname(rs.getString("SURNAME"));
                s.setPhone(rs.getString("PHONE"));
            }
        }
        return s;
    }

    @Override
    public void updateStaff(Staff staff) throws Exception {
        String sql = "UPDATE STAFF SET NAME = ?, SURNAME = ?, PHONE = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, staff.getName());
            ps.setString(2, staff.getSurname());
            ps.setString(3, staff.getPhone());
            ps.setLong(4, staff.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteStaff(long staffId) throws Exception {
        String sql = "UPDATE STAFF SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, staffId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Staff> searchStaffData(String keyword) throws Exception {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM STAFF\n"
                + "WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?)) ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getLong("ID"));
                s.setName(rs.getString("NAME"));
                s.setSurname(rs.getString("SURNAME"));
                staffList.add(s);
            }
        }

        return staffList;
    }

}
