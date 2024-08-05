package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject	
	private IBoardDao dao;
	
	@Override
	public ServiceResult insertBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = dao.insertBoard(boardVO);
		if (status > 0) { // 등록 성공
			result = ServiceResult.OK;
		} else { // 등록 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		dao.incrementByHit(boNo); // 조회수 증가
		return dao.selectBoard(boNo);
	}
	/**
	 * <p>일반 목록 조회</p>
	 */
	@Override
	public List<BoardVO> selectBoardList() {
		return dao.selectBoardList();
	}

	// 페이징 및 검색을 활용한 목록 조회 --------------
	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}
	// 페이징 및 검색을 활용한 목록 조회 끝 --------------

	@Override
	public ServiceResult updateBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = dao.updateBoard(boardVO);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = dao.deleteBoard(boNo);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
