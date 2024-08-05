package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class BoardDaoImpl implements IBoardDao {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		return sqlSession.insert("Board.insertBoard", boardVO);
	}

	@Override
	public void incrementByHit(int boNo) {
		sqlSession.update("Board.incrementByHit", boNo);
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		return sqlSession.selectOne("Board.selectBoard", boNo);
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return sqlSession.selectList("Board.selectBoardList_");
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return sqlSession.selectOne("Board.selectBoardCount", pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return sqlSession.selectList("Board.selectBoardList", pagingVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return sqlSession.update("Board.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(int boNo) {
		return sqlSession.delete("Board.deleteBoard", boNo);
	}
	
}
