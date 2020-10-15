package com.joker.homepage.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joker.homepage.board.db.BoardDTO;
import com.joker.homepage.board.service.BoardService;
import com.joker.homepage.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardServiceImpl service;
	
	// �Խ��� ��� ������ �̵�
	@RequestMapping(value="/board_list.do")
	public ModelAndView board_list() throws Exception{
		List<BoardDTO> list = service.board_list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board_list");
		mav.addObject("list",list);
		return mav;
	}
	
	// �Խ��� �� �ۼ� �� �̵�
	@RequestMapping(value="/board_write_form.do")
	public String board_write_form() throws Exception{
		return "/board/board_write_form";
	}
	
	// �Խ��� �� �ۼ�
	@RequestMapping(value="/board_write.do", method = RequestMethod.POST)
	public String board_write(@ModelAttribute BoardDTO board) throws Exception{
		service.board_write(board);
		return "redirect:/board/board_list.do";
	}
	
	// ���������� �̵�
	@RequestMapping(value = "/board_detail.do", method=RequestMethod.GET)
	public ModelAndView board_detail(@RequestParam int bod_no, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board_detail");
		mav.addObject("data", service.board_detail(bod_no));
		return mav;
	}
}