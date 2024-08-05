package kr.or.ddit.board.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardModifyController {
	
	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO boardVO = service.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		// 수정 이벤트 처리시, flag 값에 해당하는 'update'의 약자인 'u'를 flag로 설정한다.
		model.addAttribute("status", "u");
		return "board/form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO, Model model) {
		String goPage = "";
		ServiceResult result = service.updateBoard(boardVO);
		if (result.equals(ServiceResult.OK)) { // 수정 성공
			goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
		} else {
			model.addAttribute("status", "u");
			model.addAttribute("board", boardVO);
			goPage = "board/form";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String deleteBoard(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteBoard(boNo);
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/list.do";
		} else {
			goPage = "redirect:/board/detail.do?boNo=" + boNo;
		}
		return goPage;
	}
}
