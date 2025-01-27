package kr.or.ddit.free.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeModifyController {

	@Inject
	private IFreeService service;
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String freeUpdateForm(int freeNo, Model model) {
		FreeVO freeVO = service.selectFree(freeNo);
		model.addAttribute("free", freeVO);
		model.addAttribute("status", "u");
		return "free/form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO freeVO, Model model) {
		String goPage = "";
		ServiceResult result = service.updateFree(freeVO);
		if (result.equals(ServiceResult.OK)) { // 수정 성공
			goPage = "redirect:/free/detail.do?freeNo=" + freeVO.getFreeNo();
		} else {
			model.addAttribute("status", "u");
			model.addAttribute("free", freeVO);
			goPage = "free/form";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String deleteFree(int freeNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteFree(freeNo);
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		} else {
			goPage = "redirect:/free/detail.do?freeNo=" + freeNo;
		}
		return goPage;
	}
	
}
