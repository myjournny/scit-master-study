package net.scit.spring6.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.service.BookService;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	/**
	 * 도서 등록 화면 요청 
	 * @return
	 */
	@GetMapping("/bookRegist")
	public String readingView() {
		return "/book/bookRegist";
	}

	/**
	 * 도서 등록 처리 요청 
	 * @param bookDTO
	 * @return ajax로 결과 반환
	 */
	@PostMapping("/bookRegist")
	@ResponseBody
	public String readingView(@ModelAttribute BookDTO bookDTO) {
		bookService.insert(bookDTO);
		return "OK";
	}

	/**
	 * 책 구매 목록 전체를 요청
	 * @return 구매 목록을 비동기 처리로 반환
	 */
	@GetMapping("/bookList")
	@ResponseBody   
	public List<BookDTO> bookList() {
		List<BookDTO> list = bookService.selectAll();
		return list;
	}
	
	@GetMapping("/bookDelete")
	@ResponseBody
    public String bookDelete(@ModelAttribute BookDTO bookDTO) {
        bookService.bookDelete(bookDTO);
        return "삭제 성공";
    }
	
	@GetMapping("/search")
	@ResponseBody
	public List<BookDTO> searchBooks(@RequestParam(name = "searchItem") String searchItem, @RequestParam(name = "searchWord") String searchWord) {
	    return bookService.searchBooks(searchItem, searchWord);
	}

}
