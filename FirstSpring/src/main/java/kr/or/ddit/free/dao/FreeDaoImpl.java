package kr.or.ddit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class FreeDaoImpl implements IFreeDao {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertFree(FreeVO freeVO) {
		return sqlSession.insert("Free.insertFree", freeVO);
	}

	@Override
	public void incrementByHit(int freeNo) {
		sqlSession.update("Free.incrementByHit", freeNo);
	}

	@Override
	public FreeVO selectFree(int freeNo) {
		return sqlSession.selectOne("Free.selectFree", freeNo);
	}

	@Override
	public List<FreeVO> selectFreeList() {
		return sqlSession.selectList("Free.selectFreeList_");
	}

	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectOne("Free.selectFreeCount", pagingVO);
	}

	@Override
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectList("Free.selectFreeList", pagingVO);
	}

	@Override
	public int updateFree(FreeVO freeVO) {
		return sqlSession.update("Free.updateFree", freeVO);
	}

	@Override
	public int deleteFree(int freeNo) {
		return sqlSession.delete("Free.deleteFree", freeNo);
	}
	
}
