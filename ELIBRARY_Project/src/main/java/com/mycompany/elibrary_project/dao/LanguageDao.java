/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Language;
import java.util.List;

/**
 *
 * @author user
 */
public interface LanguageDao {
    
    List<Language> getLanguageList() throws Exception;
    
    //add new language
    void addLanguage(Language language) throws Exception;
    
    //update language data
    Language getLanguageById(long languageId) throws Exception;
    
    void updateLanguage(Language language) throws Exception;
    
    //delete language data
    void deleteLanguage(long languageId) throws Exception;
    
    // search language
    List<Language>  searchLanguageData(String keyword) throws Exception;
}
