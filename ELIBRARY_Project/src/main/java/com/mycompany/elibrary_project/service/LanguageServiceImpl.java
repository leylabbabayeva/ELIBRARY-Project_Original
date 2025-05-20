/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.LanguageDao;
import com.mycompany.elibrary_project.model.Language;
import java.util.List;

/**
 *
 * @author user
 */
public class LanguageServiceImpl implements LanguageService {

    private LanguageDao languageDao;

    public LanguageServiceImpl(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public List<Language> getLanguageList() throws Exception {

        return languageDao.getLanguageList();
    }

    @Override
    public void addLanguage(Language language) throws Exception {
        languageDao.addLanguage(language);
    }

    @Override
    public Language getLanguageById(long languageId) throws Exception {
        return languageDao.getLanguageById(languageId);
    }

    @Override
    public void updateLanguage(Language language) throws Exception {
        languageDao.updateLanguage(language);
    }

    @Override
    public void deleteLanguage(long languageId) throws Exception {
        languageDao.deleteLanguage(languageId);
    }

    @Override
    public List<Language> searchLanguageData(String keyword) throws Exception {
        return languageDao.searchLanguageData(keyword);
    }

}
