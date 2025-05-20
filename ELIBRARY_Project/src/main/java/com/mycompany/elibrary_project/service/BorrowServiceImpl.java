/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.BorrowDao;
import com.mycompany.elibrary_project.model.Borrow;
import java.util.List;

/**
 *
 * @author user
 */
public class BorrowServiceImpl implements BorrowService {

    private BorrowDao borrowDao;

    public BorrowServiceImpl(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    @Override
    public List<Borrow> getBorrowList() throws Exception {

        return borrowDao.getBorrowList();
    }

    @Override
    public void addBorrow(Borrow borrow) throws Exception {
        borrowDao.addBorrow(borrow);
    }

    @Override
    public Borrow getBorrowById(long borrowId) throws Exception {
        return borrowDao.getBorrowById(borrowId);
    }

    @Override
    public void updateBorrow(Borrow borrow) throws Exception {
        borrowDao.updateBorrow(borrow);
    }

    @Override
    public void deleteBorrow(long borrowId) throws Exception {
        borrowDao.deleteBorrow(borrowId);
    }

    @Override
    public List<Borrow> searchBorrowData(String keyword) throws Exception {
        return borrowDao.searchBorrowData(keyword);
    }

}
