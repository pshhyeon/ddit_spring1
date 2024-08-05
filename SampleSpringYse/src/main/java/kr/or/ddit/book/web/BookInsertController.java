package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.IBookService;

/*
 * @Controller 어노테이션이 있는 클래스는 스프링이 브라우저의 요청(request)을 받아들이는
 * 컨트롤러라고 인지해서 자바 빈(Java Bean)으로 등록해서 관리한다.
 */
@Controller
@RequestMapping("/book")
public class BookInsertController {
	
	private static final Logger log = LoggerFactory.getLogger(BookInsertController.class);
	
	@Inject
	private IBookService service;
	/*
	 * @RequestMapping
	 * - 요청 URL은 어떤 메소드가 처리할 지 여부를 결정
	 * 
	 * method 속성은 http 요청 메소드를 의미합니다
	 * 일반정인 웹 페이지 개발에서 GET 메소드는 데이터를 변경하지 않는 경우에,
	 * POST 메소드는 데이터가 변경될 경우 사용합니다.
	 * 
	 * ModelAndView는 컨트롤러가 봔한할 데이터를 담당하는 모델(Model)과 화면을 담당하는 뷰(View)의
	 * 경로를 합쳐놓은 객체다.
	 * ModelAndView의 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주합니다.
	 * 
	 * 뷰의 경로를'book/form'과 같은 형태로 전달하는 이유는 요청(request)에 해당하는 url의
	 * mapping되는 화면의 경로 값을 viewresolver라는 녀석이 제일 먼저 받는다.
	 * 받아서 suffix, prefix 속성에 의해서 앞에는 '/WEB-INF/Views/'를 붙이고
	 * 뒤에는 '.jsp'를 붙여 최종 위치에 해당하는 jsp파일을 찾아줍니다. >>  src/webapp/WEB-INF/spring/appServlet/servlet-context.xml로 적용
	 */
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public ModelAndView boardForm() {
		return new ModelAndView("book/form");
	}
	
	/*
	 * 데이터 변경이 일어나므로 http 메소드는 POST방식으로 철
	 * 어노테이션(@) RequestParam은 HTTP 파라미터를 map 변수에 자동으로 바인딩한다
	 * Map 타입의 경우는 어노테이션(@)RequestParam을 붙여야함 HTTP 파라미터의 값을 map안에 바인딩해준다.
	 */
	@RequestMapping(value = "/form.do", method = RequestMethod.POST)
	public ModelAndView insertBook(@RequestParam Map<String, Object> map) { 
		log.info("insertBook() 실행...!");
		log.info("# map : " + map.get("title"));
		log.info("# map : " + map.get("category"));
		log.info("# map : " + map.get("price"));
		
		ModelAndView mav = new ModelAndView("book/form");
		
		// 서비스를 통해서 등록 기능을 요청한다.
		// 서비스 메소드 insertBook을 호출한다.
		// [방법 1] 등록 후 성공 여부로 결과 받아오기
//		int status = service.insertBook(map);
//		log.info("# status : " + status);
		
		// [방법 2] 등록 후 최신 책 ID 결과 받아오기
		// 이동 페이지는 id에 해당하는 상세보기 페이지
		String bookId = service.insertBook2(map);
		log.info("bookId : " + bookId);
		if (bookId == null) {
			mav.setViewName("redirect:/book.form.do");
		}else {
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		
		return mav;
	}
	
}
