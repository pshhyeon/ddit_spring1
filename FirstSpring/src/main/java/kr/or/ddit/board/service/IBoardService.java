package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {
	public ServiceResult insertBoard(BoardVO boardVO);
	public BoardVO selectBoard(int boNo);
	public List<BoardVO> selectBoardList();
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	public ServiceResult updateBoard(BoardVO boardVO);
	public ServiceResult deleteBoard(int boNo);
}
