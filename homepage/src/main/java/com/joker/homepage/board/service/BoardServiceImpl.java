package com.joker.homepage.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joker.homepage.board.db.BoardDAO;
import com.joker.homepage.board.db.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO manager;
	
	// 게시판 글 작성
	@Override
	public int board_write(BoardDTO board) throws Exception {
		return manager.board_write(board);
	}
	// 게시판 글 목록
		@Override
	public List<BoardDTO> board_list() throws Exception{
			return manager.board_list();
		}
}