/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.model.Borrow;
import java.util.List;

/**
 *
 * @author user
 */
public interface BorrowService {
    
    List<Borrow> getBorrowList() throws Exception;
    
    // add new borrow
    void addBorrow(Borrow borrow) throws Exception;
    
    // update borrow data
    Borrow getBorrowById(long borrowId) throws Exception;
    
    void updateBorrow(Borrow borrow) throws Exception;
    
    // delete Borrow. We should "delete" borrow data(make active 0) to indicate the book returned
    void deleteBorrow(long borrowId) throws Exception;
    
    // search borrow
    List<Borrow>  searchBorrowData(String keyword) throws Exception;
}
