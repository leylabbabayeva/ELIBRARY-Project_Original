package com.mycompany.elibrary_project.main;

import com.mycompany.elibrary_project.dao.AuthorDao;
import com.mycompany.elibrary_project.dao.AuthorDaoImpl;
import com.mycompany.elibrary_project.dao.BookDao;
import com.mycompany.elibrary_project.dao.BookDaoImpl;
import com.mycompany.elibrary_project.dao.BorrowDao;
import com.mycompany.elibrary_project.dao.BorrowDaoImpl;
import com.mycompany.elibrary_project.dao.GenreDao;
import com.mycompany.elibrary_project.dao.GenreDaoImpl;
import com.mycompany.elibrary_project.dao.LanguageDao;
import com.mycompany.elibrary_project.dao.LanguageDaoImpl;
import com.mycompany.elibrary_project.dao.MemberDao;
import com.mycompany.elibrary_project.dao.MemberDaoImpl;
import com.mycompany.elibrary_project.dao.StaffDao;
import com.mycompany.elibrary_project.dao.StaffDaoImpl;
import com.mycompany.elibrary_project.gui.MainFrame;
import com.mycompany.elibrary_project.service.AuthorService;
import com.mycompany.elibrary_project.service.AuthorServiceImpl;
import com.mycompany.elibrary_project.service.BookService;
import com.mycompany.elibrary_project.service.BookServiceImpl;
import com.mycompany.elibrary_project.service.BorrowService;
import com.mycompany.elibrary_project.service.BorrowServiceImpl;
import com.mycompany.elibrary_project.service.GenreService;
import com.mycompany.elibrary_project.service.GenreServiceImpl;
import com.mycompany.elibrary_project.service.LanguageService;
import com.mycompany.elibrary_project.service.LanguageServiceImpl;
import com.mycompany.elibrary_project.service.MemberService;
import com.mycompany.elibrary_project.service.MemberServiceImpl;
import com.mycompany.elibrary_project.service.StaffService;
import com.mycompany.elibrary_project.service.StaffServiceImpl;

public class ELIBRARY_Project {

    public static void main(String[] args) {
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            AuthorDao authorDao = new AuthorDaoImpl();
            AuthorService authorService = new AuthorServiceImpl(authorDao);
            GenreDao genreDao = new GenreDaoImpl();
            GenreService genreService = new GenreServiceImpl (genreDao);
            LanguageDao languageDao = new LanguageDaoImpl();
            LanguageService languageService = new LanguageServiceImpl (languageDao);
            BookDao bookDao = new BookDaoImpl();
            BookService bookService = new BookServiceImpl(bookDao);
            MemberDao memberDao = new MemberDaoImpl();
            MemberService memberService = new MemberServiceImpl(memberDao);
            StaffDao staffDao = new StaffDaoImpl();
            StaffService staffService = new StaffServiceImpl(staffDao);
            BorrowDao borrowDao = new BorrowDaoImpl();
            BorrowService borrowService = new BorrowServiceImpl(borrowDao);
            MainFrame mainFrame = new MainFrame(authorService, genreService, languageService, bookService, memberService, staffService, borrowService);
            mainFrame.setVisible(true);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
