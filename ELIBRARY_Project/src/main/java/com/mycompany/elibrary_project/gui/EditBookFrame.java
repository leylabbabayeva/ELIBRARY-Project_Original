/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elibrary_project.gui;

import com.mycompany.elibrary_project.model.Author;
import com.mycompany.elibrary_project.model.Book;
import com.mycompany.elibrary_project.model.Genre;
import com.mycompany.elibrary_project.model.Language;
import com.mycompany.elibrary_project.service.AuthorService;
import com.mycompany.elibrary_project.service.BookService;
import com.mycompany.elibrary_project.service.GenreService;
import com.mycompany.elibrary_project.service.LanguageService;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class EditBookFrame extends javax.swing.JFrame {

    private BookService bookService;
    private Long selectedRowId;
    private GenreService genreService;
    private LanguageService languageService;
    private AuthorService authorService;
    private List<Author> authorList;
    private List<Genre> genreList;
    private List<Language> languageList;

    /**
     * Creates new form EditBookFrame
     */
    public EditBookFrame(BookService bookService, LanguageService languageService, GenreService genreService, AuthorService authorService, Long selectedRowId) {
        initComponents();
        this.bookService = bookService;
        this.selectedRowId = selectedRowId;
        this.authorService = authorService;
        this.genreService = genreService;
        this.languageService = languageService;
        showBookOldData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genreCmb = new javax.swing.JComboBox<>();
        languageCmb = new javax.swing.JComboBox<>();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        authorCmb = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        genreCmb.setModel(new javax.swing.DefaultComboBoxModel<>());

        languageCmb.setModel(new javax.swing.DefaultComboBoxModel<>());

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Author");

        jLabel3.setText("Genre");

        jLabel4.setText("Language");

        authorCmb.setModel(new javax.swing.DefaultComboBoxModel<>());
        authorCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorCmbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(authorCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(languageCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(genreCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(authorCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            String name = nameTxt.getText();
            int authorIndex = authorCmb.getSelectedIndex();
            int genreIndex = genreCmb.getSelectedIndex();
            int languageIndex = languageCmb.getSelectedIndex();

            // Defensive check
            if (name.isEmpty() || authorIndex < 0 || genreIndex < 0 || languageIndex < 0) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Author selectedAuthor = authorList.get(authorIndex);
            Genre selectedGenre = genreList.get(genreIndex);
            Language selectedLanguage = languageList.get(languageIndex);

            Book book = bookService.getBookById(selectedRowId);
            if (book == null) {
                JOptionPane.showMessageDialog(this, "Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            book.setName(name);
            book.setAuthor(selectedAuthor);
            book.setGenre(selectedGenre);
            book.setLanguage(selectedLanguage);

            bookService.updateBook(book);
            JOptionPane.showMessageDialog(this, "Book has been successfully updated!");
            this.dispose();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error while updating book!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while updating book!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        nameTxt.setText("");
        authorCmb.setSelectedItem(null);
        genreCmb.setSelectedItem(null);
        languageCmb.setSelectedItem(null);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void authorCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authorCmbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> authorCmb;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> genreCmb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> languageCmb;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

    private void showBookOldData() {
        try {
            Book book = bookService.getBookById(selectedRowId);
            nameTxt.setText(book.getName());

            // --- Load and map authors ---
            authorCmb.removeAllItems();
            authorList = authorService.getAuthorList();
            for (Author author : authorList) {
                authorCmb.addItem(author.getName() + " " + author.getSurname());
            }
            // Set the selected author by index
            for (int i = 0; i < authorList.size(); i++) {
                if (authorList.get(i).getId().equals(book.getAuthor().getId())) {
                    authorCmb.setSelectedIndex(i);
                    break;
                }
            }

            // --- Load and map genres ---
            genreCmb.removeAllItems();
            genreList = genreService.getGenreList();
            for (Genre genre : genreList) {
                genreCmb.addItem(genre.getName());
            }
            for (int i = 0; i < genreList.size(); i++) {
                if (genreList.get(i).getId().equals(book.getGenre().getId())) {
                    genreCmb.setSelectedIndex(i);
                    break;
                }
            }

            // --- Load and map languages ---
            languageCmb.removeAllItems();
            languageList = languageService.getLanguageList();
            for (Language language : languageList) {
                languageCmb.addItem(language.getName());
            }
            for (int i = 0; i < languageList.size(); i++) {
                if (languageList.get(i).getId().equals(book.getLanguage().getId())) {
                    languageCmb.setSelectedIndex(i);
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading book data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
