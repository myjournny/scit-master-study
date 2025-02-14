package net.scit.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.UserDTO;
import net.scit.spring7.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	// 회원가입 화면 요청
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}
	
	// 아이디 중복확인 체크
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="userId") String userId) {
		boolean result = userService.existId(userId);
				
		return result;
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@ModelAttribute UserDTO userDTO) {
		log.info("회원 정보: {} ", userDTO.toString());
		boolean result = userService.joinProc(userDTO);
		
		return "redirect:/";
	}
	/**
	 * 1) 로그인 화면 요청
	 * 2) 에러 발생 시 이 곳으로 리다이렉트
	 */
	@GetMapping("/login")
	public String login(@RequestParam(name="error", required = false) String error
			, @RequestParam(name="errMessage", required = false) String errMessage
			, Model model) {
	
		model.addAttribute("error",error);
		model.addAttribute("errMessage", errMessage);	// 핸들러 처리에 의해 가져온 메시지
		// model.addAttribute("errMessage","아이디나 비밀번호가 틀렸습니다.");
		return "/user/login";
	}
	
	/**
	 * 회원정보를 위한 페이지 요청
	 * @return
	 */
	@GetMapping("/mypage")
	public String mypage() {
		return "/user/mypage";
	}
	
	/**
	 * 개인정보 수정을 위한 아이디 비번 체크
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	@PostMapping("/pwdCheck")
	public String pwdCheck(
			@RequestParam(name="userId") String userId
			, @RequestParam(name="userPwd") String userPwd
			, Model model) {
		
		// DB에 가서 아이디와 비밀번호가 DB에 저장된 데이터와 맞는지 확인
		UserDTO userDTO = userService.pwdCheck(userId, userPwd);
		
		if(userDTO != null) {
			model.addAttribute("userDTO", userDTO);
			return "/user/myInfoUpdate";
		}
		
		return "redirect:/";
	}
	
	/**
	 * 데이터 수정 작업 요청
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/updateProc")
	public String updateProc(@ModelAttribute UserDTO userDTO) {
		log.info("-- {}", userDTO.toString());

		// DB에서 수정처리
		userService.updateProc(userDTO);
		
		// 수정을 완료하면 로그아웃
		return "redirect:/user/logout";
	}
}
