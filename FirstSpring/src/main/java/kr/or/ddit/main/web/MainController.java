package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Controller
public class MainController {
	
	@Inject
	private IBoardService boardService;
	
	@Inject
	private IFreeService freeService;
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value = {"/", "main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		// 'localhost'라고 주소창에 입력했을 때, main.jsp가 응답 화면으로 나갈 수 있도록 완성해주세요.
		
		List<BoardVO> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);

		List<FreeVO> freeList = freeService.selectFreeList();
		model.addAttribute("freeList", freeList);

		List<NoticeVO> noticeList = noticeService.selectNoticeList();
		model.addAttribute("noticeList", noticeList);
		
		return "main";
	}
	
}
