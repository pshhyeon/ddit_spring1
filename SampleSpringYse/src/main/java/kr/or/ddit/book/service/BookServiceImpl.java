package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.book.dao.IBookDAO;
/*
 * 일반적으로 서비스 레이어는 인터페이스와 클래스를 함께 사용한다.
 * 스프링은 직접 클래스를 생성하는 것을 지양하고 인터페이스를 통해 접근하는 것을 권장하는 프레임워크이다.
 */
/**
 * @author PC-08
 *
 */
@Service
public class BookServiceImpl implements IBookService {
	/*
	 * Service 클래스는 비즈니스 클래스가 위치하는 곳이다.
	 * 스프링 MVC 구조에서 서비스 클래스는 컨트롤러와 DAO를 연결하는 역할을 한다.
	 * 
	 * @Service는 스프링에 서비스 클래스임을 알려준다.
	 * 
	 * 데이터베이스 접근을 위해 BookDAO 인스턴스를 주입받는다.
	 * 클래스 이름이Impl로 끝나는 클래스들이 있는데 implements의 약자로 관습에 따른다.
	 * Impl이 붙고 안붙고에 따라 클래스인지 인터페이스인지 구별하기 쉽다.
	 */
	@Inject
	private IBookDAO dao;
	
	/**
	 * <p>책 등록<p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 등록할 책 데이터
	 * @return 성공 1, 실패 0
	 */
	@Override
	public int insertBook(Map<String, Object> map) {
		return dao.insertBook(map);
	}

	
	/**
	 * <p>책 등록<p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 등록할 책 데이터
	 * @return 성공 책ID, 실패 null
	 */
	@Override
	public String insertBook2(Map<String, Object> map) {
		int status = dao.insertBook2(map);
		String key = null;
		if (status > 0) { // 등록 성공
			key = map.get("book_id").toString();
		}
		return key;
	}

	
	/**
	 * <p>책 상세보기</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 책 ID
	 * @return ID에 해당하는 책 정보
	 */
	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		return dao.selectBook(map);
	}


	/**
	 * <p>책 수정</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 수정 할 책 데이터
	 * @return 성공 1(true), 실패 0(false)
	 */
	@Override
	public boolean updateBook(Map<String, Object> map) {
		int status = dao.updateBook(map);
		return status == 1;
	}


	/**
	 * <p>책 삭제</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 책 ID
	 * @return 성공 1(true), 실패 0(false)
	 */
	@Override
	public boolean deleteBook(Map<String, Object> map) {
		int status = dao.deleteBook(map);
		return status == 1;
	}

	/**
	 * <p>책 목록</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 검색어
	 * @return 리스트(책)
	 */
	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return dao.selectBookList(map);
	}

}
