package kr.human.boot.service;

import java.sql.SQLException;

import kr.human.boot.vo.GuestVO;
import kr.human.boot.vo.PagingVO;

public interface GuestService {
	
	//목록보기
	PagingVO<GuestVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) throws SQLException;

	//내용보기
	GuestVO selectByIdx(int idx) throws SQLException;
	
	//저장하기
	void insert(GuestVO guestVO) throws SQLException;
	
	//수정하기
	void update(GuestVO guestVO) throws SQLException;

	//삭제하기
	void delete(GuestVO guestVO) throws SQLException;
}
