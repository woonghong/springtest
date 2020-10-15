package com.joker.homepage.board.service;

import java.util.List;

import com.joker.homepage.board.db.BoardDTO;

public interface BoardService {
	public int board_write(BoardDTO board) throws Exception;
	
	public List<BoardDTO> board_list() throws Exception;
	
	public BoardDTO board_detail(int bod_no) throws Exception;
}
