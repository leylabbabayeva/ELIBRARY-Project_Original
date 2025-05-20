/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

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
public class LanguageDaoImpl implements LanguageDao {

    @Override
    public List<Language> getLanguageList() throws Exception {
        List<Language> languageList = new ArrayList<>();
        String sql = "SELECT * FROM LANGUAGE\n"
                + "WHERE ACTIVE = 1 ORDER BY ID";
        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Language l = new Language();
                l.setId(rs.getLong("ID"));
                l.setName(rs.getString("NAME"));
                languageList.add(l);
            }
        }
        return languageList;
    }

    @Override
    public void addLanguage(Language language) throws Exception {
        String sql = "INSERT INTO LANGUAGE(ID, NAME)\n"
                + "VALUES(LANGUAGE_SEQ.NEXTVAL, ?)";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, language.getName()); //first(number) is order of questions in sql
            ps.execute(); //to upload data in sql
            c.commit();
        }
    }

    @Override
    public Language getLanguageById(long languageId) throws Exception {
        Language l = new Language();
        String sql = "SELECT * FROM LANGUAGE\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, languageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getLong("ID"));
                l.setName(rs.getString("NAME"));
            }
        }
        return l;
    }

    @Override
    public void updateLanguage(Language language) throws Exception {
        String sql = "UPDATE LANGUAGE SET NAME = ?\n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, language.getName()); //first(number) is order of questions in sql
            ps.setLong(2, language.getId());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteLanguage(long languageId) throws Exception {
        String sql = "UPDATE LANGUAGE SET ACTIVE = 0 \n"
                + "WHERE ID = ?";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, languageId);
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Language> searchLanguageData(String keyword) throws Exception {
        List<Language> languageList = new ArrayList<>();
        String sql = "SELECT * FROM LANGUAGE\n"
                + "WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER(?)) ORDER BY ID";

        try ( Connection c = DbConnect.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Language l = new Language();
                l.setId(rs.getLong("ID"));
                l.setName(rs.getString("NAME"));
                languageList.add(l);
            }
        }

        return languageList;
    }

}
