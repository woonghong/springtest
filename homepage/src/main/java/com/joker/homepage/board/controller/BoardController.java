package com.joker.homepage.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joker.homepage.board.db.BoardDTO;
import com.joker.homepage.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardServiceImpl service;
	
	// 게시판 목록 페이지 이동
	@RequestMapping(value="/board_list.do")
	public ModelAndView board_list() throws Exception{
		List<BoardDTO> list = service.board_list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board_list");
		mav.addObject("list",list);
		return mav;
	}
	
	// 게시판 글 작성 폼 이동
	@RequestMapping(value="/board_write_form.do")
	public String board_write_form() throws Exception{
		return "/board/board_write_form";
	}
	
	// 게시판 글 작성
	@RequestMapping(value="/board_write.do", method = RequestMethod.POST)
	public String board_write(@ModelAttribute BoardDTO board) throws Exception{
		service.board_write(board);
		return "redirect:/board/board_list.do";
	}
}