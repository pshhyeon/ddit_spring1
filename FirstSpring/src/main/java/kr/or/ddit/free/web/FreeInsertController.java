package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeInsertController {

	@Inject
	private IFreeService service;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm() {
		return "free/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insertBoard(FreeVO freeVO, Model model) {

		// 유효성 검사
		Map<String, String> errors = new HashMap<String, String>();
		String goPage = ""; // 페이지 정보를 담을 공간
		
		if (StringUtils.isBlank(freeVO.getFreeTitle())) {
			errors.put("freeTitle", "제목을 입력해주세요!");
		}

		if (StringUtils.isBlank(freeVO.getFreeContent())) {
			errors.put("freeContent", "내용을 입력해주세요!");
		}
		
		if (errors.size() > 0) { // 입력된 데이터의 에러가 발생
			// 데이터를 전달
			model.addAttribute("errors", errors);
			model.addAttribute("free", freeVO);
			// 페이지 이동 경로를 설정
			goPage = "free/form";
		} else { // 정상적인 데이터가 입력됨
			freeVO.setFreeWriter("a001"); // 작성자 설정(하드코딩)
			ServiceResult result = service.insertFree(freeVO);
			if(result.equals(ServiceResult.OK)) { // 등록 성공
				goPage = "redirect:/free/detail.do?freeNo=" + freeVO.getFreeNo();
			} else {
				model.addAttribute("free", freeVO);
				goPage = "free/form";
			}
		}
		
		return goPage;
	}
}
