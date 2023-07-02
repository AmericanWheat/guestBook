package kr.human.boot.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.boot.vo.GuestVO;

@Mapper
public interface GuestDAO {

	int selectCount() throws SQLException;

	GuestVO selectByIdx(int idx) throws SQLException;

	List<GuestVO> selectList(HashMap<String, Integer> map) throws SQLException;

	void insert(GuestVO guestVO) throws SQLException;

	void update(GuestVO guestVO) throws SQLException;

	void delete(int idx) throws SQLException;
}
