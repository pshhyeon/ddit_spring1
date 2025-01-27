package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeDaoImpl implements INoticeDao {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertNotice(NoticeVO noticeVO) {
		return sqlSession.insert("Notice.insertNotice", noticeVO);
	}

	@Override
	public void incrementByHit(int boNo) {
		sqlSession.update("Notice.incrementByHit", boNo);
	}

	@Override
	public NoticeVO selectNotice(int noticeNo) {
		return sqlSession.selectOne("Notice.selectNotice", noticeNo);
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		return sqlSession.selectList("Notice.selectNoticeList_");
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectOne("Notice.selectNoticeCount", pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectNoticeList", pagingVO);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("Notice.updateNotice", noticeVO);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return sqlSession.delete("Notice.deleteNotice", noticeNo);
	}
	
}
