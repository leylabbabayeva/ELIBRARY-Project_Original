/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elibrary_project.service;

import com.mycompany.elibrary_project.dao.MemberDao;
import com.mycompany.elibrary_project.model.Member;
import java.util.List;

/**
 *
 * @author user
 */
public class MemberServiceImpl implements MemberService {
    
    private MemberDao memberDao;
    
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @Override
    public List<Member> getMemberList() throws Exception {
        
        return memberDao.getMemberList();
    }
    
    @Override
    public void addMember(Member member) throws Exception {
        memberDao.addMember(member);
    }
    
    @Override
    public Member getMemberById(long memberId) throws Exception {
        return memberDao.getMemberById(memberId);
    }
    
    @Override
    public void updateMember(Member member) throws Exception {
        memberDao.updateMember(member);
    }
    
    @Override
    public void deleteMember(long memberId) throws Exception {
        memberDao.deleteMember(memberId);
    }

    @Override
    public List<Member> searchMemberData(String keyword) throws Exception {
        return memberDao.searchMemberData(keyword);
    }
    
}
