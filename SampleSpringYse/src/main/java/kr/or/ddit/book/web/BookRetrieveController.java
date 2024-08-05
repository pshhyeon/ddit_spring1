package kr.or.ddit.book.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookRetrieveController {
	
	@Inject
	private IBookService service;
	
	// @RequestPara, 어노테이션에 의해 쿼리 스트링 파라미터를 읽을 수 있다.
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public ModelAndView detailBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// 데이터 베이스에서 조회한 결과를 detailMap 변수에 담는다.
		Map<String, Object> detailMap = service.selectBook(map);
		String bookId = detailMap.get("BOOK_ID").toString();
		// ModelAndView 객체를 활용한 데이터 전달
		mav.addObject("bookId", bookId);
		mav.addObject("book", detailMap);
		mav.setViewName("book/detail");
		return mav;
	}
	
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public ModelAndView listBook(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = service.selectBookList(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bookList", list);
		
		if (map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		mav.setViewName("book/list");
		
		return mav;
	}
}
