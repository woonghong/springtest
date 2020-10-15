package com.joker.homepage.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joker.homepage.board.db.BoardDAO;
import com.joker.homepage.board.db.BoardDTO;
import com.joker.homepage.member.db.MemberDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO manager;
	
	// �Խ��� �� �ۼ�
	@Override
	public int board_write(BoardDTO board) throws Exception {
		return manager.board_write(board);
	}
	// �Խ��� �� ���
		@Override
	public List<BoardDTO> board_list() throws Exception{
			return manager.board_list();
		}
		
		// �Խ��� �� ����
		@Override
		public BoardDTO board_detail(int bod_no) throws Exception {
			return manager.board_detail(bod_no);
		}
		
}