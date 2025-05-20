/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.dao;

import com.mycompany.elibrary_project.model.Member;
import java.util.List;

/**
 *
 * @author user
 */
public interface MemberDao {
    
    List<Member> getMemberList() throws Exception;
    
    // add new member
    void addMember(Member member) throws Exception;
    
    // update member data
    Member getMemberById(long memberId) throws Exception;
    
    void updateMember(Member member) throws Exception;
    
    // delete member
    void deleteMember(long memberId) throws Exception;
    
    // search member
    List<Member>  searchMemberData(String keyword) throws Exception;
}
