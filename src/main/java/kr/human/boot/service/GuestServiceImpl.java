package kr.human.boot.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.boot.dao.GuestDAO;
import kr.human.boot.vo.GuestVO;
import kr.human.boot.vo.PagingVO;

@Service("guestService")
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDAO guestDAO;

	@Override
	public PagingVO<GuestVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<GuestVO> pv = null;
		try {
			int totalCount = guestDAO.selectCount();
			pv = new PagingVO<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
			if (pv.getTotalCount() > 0) {
				HashMap<String, Integer> map = new HashMap<>();
				map.put("startNo", pv.getStartNo());
				map.put("endNo", pv.getEndNo());
				pv.setList(guestDAO.selectList(map));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}

	@Override
	public GuestVO selectByIdx(int idx) {
		GuestVO guestVO = null;
		try {
			guestVO = guestDAO.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestVO;
	}

	@Override
	public void insert(GuestVO guestVO) {
		try {
			if(guestVO!=null)
			guestDAO.insert(guestVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GuestVO guestVO) {
		try {
			if (guestVO != null) {
				GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx());
				if (dbVO.getPassword().equals(guestVO.getPassword())) {
					guestDAO.update(guestVO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(GuestVO guestVO) {
		if (guestVO != null) {
			try {
				GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx());
				if (dbVO.getPassword().equals(guestVO.getPassword())) {
					guestDAO.delete(guestVO.getIdx());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
