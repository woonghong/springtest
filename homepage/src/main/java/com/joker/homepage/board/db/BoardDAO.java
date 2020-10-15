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
	
	// 게시판 글 작성
	@Transactional
	public int board_write(BoardDTO board) throws Exception{
		return sqlsession.insert("board.board_write", board);
	}
	
	//게시물 리스트
	public List<BoardDTO> board_list() throws Exception{
		return sqlsession.selectList("board.board_list");
	}
	
	// 게시물 자세히 보기
			public BoardDTO board_detail(int bod_no) throws Exception{
				return sqlsession.selectOne("board.board_detail", bod_no);
			}
	
}