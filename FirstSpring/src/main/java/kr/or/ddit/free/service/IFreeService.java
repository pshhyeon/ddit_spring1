package kr.or.ddit.free.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeService {
	public ServiceResult insertFree(FreeVO freeVO);
	public FreeVO selectFree(int freeNo);
	public List<FreeVO> selectFreeList();
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);
	public ServiceResult updateFree(FreeVO freeVO);
	public ServiceResult deleteFree(int freeNo);
}
