package kr.or.ddit.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.dao.IFreeDao;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class FreeServiceImpl implements IFreeService {
	
	@Inject	
	private IFreeDao dao;
	
	@Override
	public ServiceResult insertFree(FreeVO freeVO) {
		ServiceResult result = null;
		int status = dao.insertFree(freeVO);
		if (status > 0) { // 등록 성공
			result = ServiceResult.OK;
		} else { // 등록 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public FreeVO selectFree(int freeNo) {
		dao.incrementByHit(freeNo); // 조회수 증가
		return dao.selectFree(freeNo);
	}
	/**
	 * <p>일반 목록 조회</p>
	 */
	@Override
	public List<FreeVO> selectFreeList() {
		return dao.selectFreeList();
	}

	// 페이징 및 검색을 활용한 목록 조회 --------------
	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO) {
		return dao.selectFreeCount(pagingVO);
	}

	@Override
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO) {
		return dao.selectFreeList(pagingVO);
	}
	// 페이징 및 검색을 활용한 목록 조회 끝 --------------

	@Override
	public ServiceResult updateFree(FreeVO freeVO) {
		ServiceResult result = null;
		int status = dao.updateFree(freeVO);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteFree(int freeNo) {
		ServiceResult result = null;
		int status = dao.deleteFree(freeNo);
		if (status > 0) { // 수정 성공
			result = ServiceResult.OK;
		} else { // 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
