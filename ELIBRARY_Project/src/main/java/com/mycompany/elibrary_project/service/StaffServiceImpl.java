/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.StaffDao;
import com.mycompany.elibrary_project.model.Staff;
import java.util.List;

/**
 *
 * @author user
 */
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    public StaffServiceImpl(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public List<Staff> getStaffList() throws Exception {

        return staffDao.getStaffList();
    }

    @Override
    public void addStaff(Staff staff) throws Exception {
        staffDao.addStaff(staff);
    }

    @Override
    public Staff getStaffById(long staffId) throws Exception {
        return staffDao.getStaffById(staffId);
    }

    @Override
    public void updateStaff(Staff staff) throws Exception {
        staffDao.updateStaff(staff);
    }

    @Override
    public void deleteStaff(long staffId) throws Exception {
        staffDao.deleteStaff(staffId);
    }

    @Override
    public List<Staff> searchStaffData(String keyword) throws Exception {
        return staffDao.searchStaffData(keyword);
    }

}
