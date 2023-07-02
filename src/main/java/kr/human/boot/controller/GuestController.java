package kr.human.boot.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.human.boot.service.GuestService;
import kr.human.boot.vo.CommVO;
import kr.human.boot.vo.GuestVO;
import kr.human.boot.vo.PagingVO;

@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@RequestMapping("/")
	public String index(@ModelAttribute CommVO commVO, Model model) throws SQLException {
		PagingVO<GuestVO> pagingVO;
		try {
			pagingVO = guestService.selectList(commVO.getCurrentPage(), commVO.getSizeOfPage(), commVO.getSizeOfBlock());
			model.addAttribute("cv" ,commVO);
			model.addAttribute("pv", pagingVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@PostMapping(value = "/insertOk")
	public String insertOk(@ModelAttribute CommVO commVO,@ModelAttribute GuestVO guestVO, HttpServletRequest request, Model model) throws SQLException {
		switch (commVO.getMode()) {
		case "insert":
			if(guestVO!=null) {
				guestVO.setIp(request.getRemoteAddr());
				guestService.insert(guestVO);
			}
			break;
		case "update":
			if(guestVO!=null) guestService.update(guestVO);
			break;
		case "delete":
			if(guestVO!=null) guestService.delete(guestVO);
			break;
			
		default:
			break;
		}
		return "redirect:/";
	}
}
