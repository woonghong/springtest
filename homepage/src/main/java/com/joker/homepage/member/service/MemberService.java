package com.joker.homepage.member.service;

import javax.servlet.http.HttpServletResponse;
import com.joker.homepage.member.db.MemberDTO;

public interface MemberService {
	public void check_id(String id, HttpServletResponse response) throws Exception;

	public void check_email(String email, HttpServletResponse response) throws Exception;

	public int join_member(MemberDTO member, HttpServletResponse response) throws Exception;
	
	public String create_key() throws Exception;
	
	public void send_mail(MemberDTO member, String div) throws Exception;
	
	public void approval_member(MemberDTO member, HttpServletResponse response) throws Exception;
	
	public MemberDTO login(MemberDTO member, HttpServletResponse response) throws Exception;
	
	public void logout(HttpServletResponse response) throws Exception;

	public String find_id(HttpServletResponse response, String email) throws Exception;
	
	public void find_pw(HttpServletResponse response, MemberDTO member) throws Exception;
	
	public MemberDTO update_mypage(MemberDTO member) throws Exception;
	
	public MemberDTO update_pw(MemberDTO member, String old_pw, HttpServletResponse response) throws Exception;
	
	public boolean withdrawal(MemberDTO member, HttpServletResponse response) throws Exception;
}