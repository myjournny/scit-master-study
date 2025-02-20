package net.scit.spring6.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.dto.ReadingNoteDTO;
import net.scit.spring6.service.ReadingNoteService;

@Controller
@RequestMapping("/reading")
@RequiredArgsConstructor
public class ReadingController {
	private final ReadingNoteService readingService;
	
	/**
	 * 모든 독서 노트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/readingView")
	public String readingView(
			@RequestParam(name="bookSeq", required=false) Integer bookSeq
			, Model model) {
		List<ReadingNoteDTO> list = readingService.selectAll(bookSeq);
		
		model.addAttribute("list", list);
		return "/book/readingView";
	}
	
	/**
	 * 독서 노트 기록 화면 요청
	 * (이미 존재하는 도서 목록이 필요하므로 조회한 결과값이 있어야 한다.
	 * @param bookSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/readingWrite")
	public String readingWrite(
			@RequestParam(name="bookSeq", required=false) Integer bookSeq
			, Model model) {
		
		BookDTO book = readingService.selectBook(bookSeq);
	
		model.addAttribute("book", book);
		return "/book/readingWrite";
	}
	
	/**
	 * 독서 노트 기록
	 * @param readingDTO
	 * @return
	 */
	@PostMapping("/readingWrite")
	public String readingWrite(@ModelAttribute ReadingNoteDTO readingDTO) {
		readingService.insertReading(readingDTO);
		return "redirect:/";
	}
	
}
