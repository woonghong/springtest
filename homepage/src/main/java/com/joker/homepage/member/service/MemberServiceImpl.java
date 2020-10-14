package com.joker.homepage.member.service;

import java.io.PrintWriter;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import com.joker.homepage.member.db.MemberDAO;
import com.joker.homepage.member.db.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO manager;

	// ���̵� �ߺ� �˻�(AJAX)
	@Override
	public void check_id(String id, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(manager.check_id(id));
		out.close();
	}

	// �̸��� �ߺ� �˻�(AJAX)
	@Override
	public void check_email(String email, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(manager.check_email(email));
		out.close();
	}

	// ȸ������
	@Override
	public int join_member(MemberDTO member, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (manager.check_id(member.getId()) == 1) {
			out.println("<script>");
			out.println("alert('������ ���̵� �ֽ��ϴ�.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else if (manager.check_email(member.getEmail()) == 1) {
			out.println("<script>");
			out.println("alert('������ �̸����� �ֽ��ϴ�.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else {
			//����Ű set
			member.setApproval_key(create_key());
			manager.join_member(member);
			
			// ���� ���� �߼�
			send_mail(member,"join");
			return 1;
		}
	}
	
	// ����Ű ����
		@Override
		public String create_key() throws Exception {
			String key = "";
			Random rd = new Random();

			for (int i = 0; i < 8; i++) {
				key += rd.nextInt(10);
			}
			return key;
		}
		
		// �̸��� �߼�
		@Override
		public void send_mail(MemberDTO member, String div) throws Exception {
			// Mail Server ����
			String charSet = "utf-8";
			String hostSMTP = "smtp.naver.com";
			String hostSMTPid = "whwin_@naver.com";
			String hostSMTPpwd = "dldngh1123";

			// ������ ��� EMail, ����, ����
			String fromEmail = "whwin_@naver.com";
			String fromName = "Spring Homepage";
			String subject = "����test";
			String msg = "����test";

			if(div.equals("join")) {
				// ȸ������ ���� ����
				subject = "Spring Homepage ȸ������ ���� �����Դϴ�.";
				msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
				msg += "<h3 style='color: blue;'>";
				msg += member.getId() + "�� ȸ�������� ȯ���մϴ�.</h3>";
				msg += "<div style='font-size: 130%'>";
				msg += "�ϴ��� ���� ��ư Ŭ�� �� ���������� ȸ�������� �Ϸ�˴ϴ�.</div><br/>";
				msg += "<form method='post' action='http://localhost:8090/homepage/member/approval_member.do'>";
				msg += "<input type='hidden' name='email' value='" + member.getEmail() + "'>";
				msg += "<input type='hidden' name='approval_key' value='" + member.getApproval_key() + "'>";
				msg += "<input type='submit' value='����'></form><br/></div>";
			}else if(div.equals("find_pw")) {
				subject = "Spring Homepage �ӽ� ��й�ȣ �Դϴ�.";
				msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
				msg += "<h3 style='color: blue;'>";
				msg += member.getId() + "���� �ӽ� ��й�ȣ �Դϴ�. ��й�ȣ�� �����Ͽ� ����ϼ���.</h3>";
				msg += "<p>�ӽ� ��й�ȣ : ";
				msg += member.getPw() + "</p></div>";
			}
			
			// �޴� ��� E-Mail �ּ�
			String mail = member.getEmail();
			try {
				HtmlEmail email = new HtmlEmail();
				email.setDebug(true);
				email.setCharset(charSet);
				email.setSSL(true);
				email.setHostName(hostSMTP);
				email.setSmtpPort(587);

				email.setAuthentication(hostSMTPid, hostSMTPpwd);
				email.setTLS(true);
				email.addTo(mail, charSet);
				email.setFrom(fromEmail, fromName, charSet);
				email.setSubject(subject);
				email.setHtmlMsg(msg);
				email.send();
			} catch (Exception e) {
				System.out.println("���Ϲ߼� ���� : " + e);
			}
		}
		
		// ȸ�� ����
		@Override
		public void approval_member(MemberDTO member, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (manager.approval_member(member) == 0) { // �̸��� ������ �����Ͽ��� ���
				out.println("<script>");
				out.println("alert('�߸��� �����Դϴ�.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			} else { // �̸��� ������ �����Ͽ��� ���
				out.println("<script>");
				out.println("alert('������ �Ϸ�Ǿ����ϴ�. �α��� �� �̿��ϼ���.');");
				out.println("location.href='../index.jsp';");
				out.println("</script>");
				out.close();
			}
		}
		
		// �α���
		@Override
		public MemberDTO login(MemberDTO member, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			// ��ϵ� ���̵� ������
			if(manager.check_id(member.getId()) == 0) {
				out.println("<script>");
				out.println("alert('��ϵ� ���̵� �����ϴ�.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			} else {
				String pw = member.getPw();
				member = manager.login(member.getId());
				// ��й�ȣ�� �ٸ� ���
				if(!member.getPw().equals(pw)) {
					out.println("<script>");
					out.println("alert('��й�ȣ�� �ٸ��ϴ�.');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.close();
					return null;
				// �̸��� ������ ���� ���� ���
				}else if(!member.getApproval_status().equals("true")) {
					out.println("<script>");
					out.println("alert('�̸��� ���� �� �α��� �ϼ���.');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.close();
					return null;
	            // �α��� ���� ������Ʈ �� ȸ������ ����			
				}else {
					manager.update_log(member.getId());
					return member;
				}
			}
		}
		
		// �α׾ƿ�
		@Override
		public void logout(HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='../index.jsp';");
			out.println("</script>");
			out.close();
		}
		
		// ���̵� ã��
		@Override
		public String find_id(HttpServletResponse response, String email) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String id = manager.find_id(email);
			
			if (id == null) {
				out.println("<script>");
				out.println("alert('���Ե� ���̵� �����ϴ�.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			} else {
				return id;
			}
		}
		
		// ��й�ȣ ã��
		@Override
		public void find_pw(HttpServletResponse response, MemberDTO member) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			// ���̵� ������
			if(manager.check_id(member.getId()) == 0) {
				out.print("���̵� �����ϴ�.");
				out.close();
			}
			// ���Կ� ����� �̸����� �ƴϸ�
			else if(!member.getEmail().equals(manager.login(member.getId()).getEmail())) {
				out.print("�߸��� �̸��� �Դϴ�.");
				out.close();
			}else {
				// �ӽ� ��й�ȣ ����
				String pw = "";
				for (int i = 0; i < 12; i++) {
					pw += (char) ((Math.random() * 26) + 97);
				}
				member.setPw(pw);
				// ��й�ȣ ����
				manager.update_pw(member);
				// ��й�ȣ ���� ���� �߼�
				send_mail(member, "find_pw");
				
				out.print("�̸��Ϸ� �ӽ� ��й�ȣ�� �߼��Ͽ����ϴ�.");
				out.close();
			}
		}
		
		// ȸ������ ����
		@Override
		public MemberDTO update_mypage(MemberDTO member) throws Exception {
			manager.update_mypage(member);
			return manager.login(member.getId());
		}
		
		// ��й�ȣ ����
		@Override
		public MemberDTO update_pw(MemberDTO member, String old_pw, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(!old_pw.equals(manager.login(member.getId()).getPw())) {
				out.println("<script>");
				out.println("alert('���� ��й�ȣ�� �ٸ��ϴ�.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			}else {
				manager.update_pw(member);
				return manager.login(member.getId());
			}
		}
		
		// ȸ��Ż��
		@Override
		public boolean withdrawal(MemberDTO member, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(manager.withdrawal(member) != 1) {
				out.println("<script>");
				out.println("alert('ȸ��Ż�� ����');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return false;
			}else {
				return true;
			}
		}
}