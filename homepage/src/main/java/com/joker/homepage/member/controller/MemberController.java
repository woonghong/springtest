package com.joker.homepage.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joker.homepage.member.db.MemberDTO;
import com.joker.homepage.member.service.MemberServiceImpl;
import com.sun.media.jfxmedia.logging.Logger;

@Controller
@RequestMapping("/member/*")

public class MemberController {
	
	//ȸ�� ���� �� �̵�
	@RequestMapping(value = "/memberJoinForm.do")
	public String memberJoinForm() throws Exception{
		return "/member/memberJoinForm";
	}

	@Autowired
	private MemberServiceImpl service;

	// ���̵� �ߺ� �˻�(AJAX)
	@RequestMapping(value = "/check_id.do", method = RequestMethod.POST)
	public void check_id(@RequestParam("id") String id, HttpServletResponse response) throws Exception{
		service.check_id(id, response);
	}
	
	// �̸��� �ߺ� �˻�(AJAX)
	@RequestMapping(value = "/check_email.do", method = RequestMethod.POST)
	public void check_email(@RequestParam("email") String email, HttpServletResponse response) throws Exception{
		service.check_email(email, response);
	}
	
	// ȸ�� ����
	@RequestMapping(value = "/join_member.do", method = RequestMethod.POST)
	public String join_member(@ModelAttribute MemberDTO member, RedirectAttributes rttr, HttpServletResponse response) throws Exception{
		rttr.addFlashAttribute("result", service.join_member(member, response));
		return "redirect:./login_form.do";
	}
	
	// ȸ�� ����
	@RequestMapping(value = "/approval_member.do", method = RequestMethod.POST)
	public void approval_member(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception{
		service.approval_member(member, response);
	}
	
	// �α��� �� �̵�
		@RequestMapping(value = "/login_form.do", method = RequestMethod.GET)
		public String login_form() throws Exception{
			return "/member/loginForm";
		}
		
		// �α���
		@RequestMapping(value = "/login.do", method = RequestMethod.POST)
		public String login(@ModelAttribute MemberDTO member, HttpSession session, HttpServletResponse response) throws Exception{
			member = service.login(member, response);
			session.setAttribute("member", member);
			return "index";
		}
		
		// �α׾ƿ�
		@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
		public void logout(HttpSession session, HttpServletResponse response) throws Exception{
			session.invalidate();
//			session.removeAttribute("member");
			service.logout(response);
		}
		
		// ���̵� ã�� ��
		@RequestMapping(value = "/find_id_form.do")
		public String find_id_form() throws Exception{
			return "/member/find_id_form";
		}
		
		// ���̵� ã��
		@RequestMapping(value = "/find_id.do", method = RequestMethod.POST)
		public String find_id(HttpServletResponse response, @RequestParam("email") String email, Model md) throws Exception{
			md.addAttribute("id", service.find_id(response, email));
			return "/member/find_id";
		}
		
		// ��й�ȣ ã�� ��
		@RequestMapping(value = "/find_pw_form.do")
		public String find_pw_form() throws Exception{
			return "/member/find_pw_form";
		}
		
		// ��й�ȣ ã��
		@RequestMapping(value = "/find_pw.do", method = RequestMethod.POST)
		public void find_pw(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception{
			service.find_pw(response, member);
		}
		
		// ���������� �̵�
		@RequestMapping(value = "/mypage.do")
		public String mypage() throws Exception{
			return "/member/mypage";
		}
		
		// mypage ����
		@RequestMapping(value = "/update_mypage.do", method = RequestMethod.POST)
		public String update_mypage(@ModelAttribute MemberDTO member, HttpSession session, RedirectAttributes rttr) throws Exception{
			session.setAttribute("member", service.update_mypage(member));
			rttr.addFlashAttribute("msg", "ȸ������ ���� �Ϸ�");
			return "redirect:/member/mypage.do";
		}
		
		// ��й�ȣ ����
		@RequestMapping(value = "/update_pw.do", method = RequestMethod.POST)
		public String update_pw(@ModelAttribute MemberDTO member, @RequestParam("old_pw") String old_pw, HttpSession session, HttpServletResponse response, RedirectAttributes rttr) throws Exception{
			session.setAttribute("member", service.update_pw(member, old_pw, response));
			rttr.addFlashAttribute("msg", "��й�ȣ ���� �Ϸ�");
			return "redirect:/member/mypage.do";
		}
		
		// ȸ��Ż��
		@RequestMapping(value = "/withdrawal.do", method = RequestMethod.POST)
		public String withdrawal_form(@ModelAttribute MemberDTO member, HttpSession session, HttpServletResponse response) throws Exception{
			if(service.withdrawal(member, response)) {
				session.invalidate();
			}
			return "redirect:/";
		}
}
