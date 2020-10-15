package com.joker.homepage.board.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAO {
	
	@Autowired
	SqlSession sqlsession = null;
	
	// �Խ��� �� �ۼ�
	@Transactional
	public int board_write(BoardDTO board) throws Exception{
		return sqlsession.insert("board.board_write", board);
	}
	
	//�Խù� ����Ʈ
	public List<BoardDTO> board_list() throws Exception{
		return sqlsession.selectList("board.board_list");
	}
	
	// �Խù� �ڼ��� ����
			public BoardDTO board_detail(int bod_no) throws Exception{
				return sqlsession.selectOne("board.board_detail", bod_no);
			}
	
}