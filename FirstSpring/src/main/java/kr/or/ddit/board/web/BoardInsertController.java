package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardInsertController {
	
	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm() {
		return "board/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insertBoard(BoardVO boardVO, Model model) {
		// 넘겨받은 데이터가 혹시나 모를 에러가 있을 수 있기 때문에
		// 입력값 검증을 진행하고 이상이 없다면 등록을 진행할 수 있도록 한다.
		
		// 유효성 검사 시, 데이터에 에러가 발생했을때 에러 정보를 담을 공간
		Map<String, String> errors = new HashMap<String, String>();
		String goPage = ""; // 페이지 정보를 담을 공간
		
		if (StringUtils.isBlank(boardVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요!");
		}

		if (StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요!");
		}
		
		if (errors.size() > 0) { // 입력된 데이터의 에러가 발생
			// 데이터를 전달
			model.addAttribute("errors", errors);
			model.addAttribute("board", boardVO);
			// 페이지 이동 경로를 설정
			goPage = "board/form";
		} else { // 정상적인 데이터가 입력됨
			boardVO.setBoWriter("a001"); // 작성자 설정(하드코딩)
			ServiceResult result = service.insertBoard(boardVO);
			if(result.equals(ServiceResult.OK)) { // 등록 성공
				goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
			} else {
				model.addAttribute("board", boardVO);
				goPage = "board/form";
			}
		}
		
		return goPage;
	}
	
	
	
	
}
