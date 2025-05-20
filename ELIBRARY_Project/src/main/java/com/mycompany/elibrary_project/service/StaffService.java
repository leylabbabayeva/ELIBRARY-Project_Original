/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.model.Staff;
import java.util.List;

/**
 *
 * @author user
 */
public interface StaffService {
    
    List<Staff> getStaffList() throws Exception;
    
    // add new staff
    void addStaff(Staff staff) throws Exception;
    
    // update staff data
    Staff getStaffById(long staffId) throws Exception;
    
    void updateStaff(Staff staff) throws Exception;
    
    // delete staff
    void deleteStaff(long staffId) throws Exception;
    
    // search staff
    List<Staff>  searchStaffData(String keyword) throws Exception;
}
