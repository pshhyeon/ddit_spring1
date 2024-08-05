package kr.or.ddit.book.web;

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
public class BookModifyController {
	
	@Inject
	private IBookService service;
	
	// bookId로 들어옴
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public ModelAndView updateBookForm(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> detailMap = service.selectBook(map);
		mav.addObject("book", detailMap);
		mav.setViewName("book/update");
		return mav;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public ModelAndView updateBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = service.updateBook(map);
		if (isUpdateSuccess) { // 성공
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}else {
			// 갱신이 되지 않았을 경우, GET 메소드로 redirect 하는 방법도 있지만,
			// 상세보기 화면을 바로 보여줄 수도 있습니다
			mav = this.updateBookForm(map);
		}
		
		return mav;
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = service.deleteBook(map);
		if (isDeleteSuccess) { // 삭제 완료
			mav.setViewName("redirect:/book/list.do");
		} else { // 삭제 실패
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		return mav;
	}
}
