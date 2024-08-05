package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDao;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private INoticeDao dao;

	@Override
	public ServiceResult insertNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = dao.insertNotice(noticeVO);
		if (status > 0) { // 등록 성공
			result = ServiceResult.OK;
		} else { // 등록 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public NoticeVO selectNotice(int NoticeNo) {
		dao.incrementByHit(NoticeNo); // 조회수 증가
		return dao.selectNotice(NoticeNo);
	}

	/**
	 * <p>
	 * 일반 목록 조회
	 * </p>
	 */
	@Override
	public List<NoticeVO> selectNoticeList() {
		return dao.selectNoticeList();
	}

	// 페이징 및 검색을 활용한 목록 조회 --------------
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectNoticeList(pagingVO);
	}
	// 페이징 및 검색을 활용한 목록 조회 끝 --------------

	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = dao.updateNotice(noticeVO);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(int NoticeNo) {
		ServiceResult result = null;
		int status = dao.deleteNotice(NoticeNo);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
