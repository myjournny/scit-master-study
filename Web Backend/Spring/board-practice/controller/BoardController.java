package net.scit.spring7.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.dto.LoginUserDetails;
import net.scit.spring7.service.BoardService;
import net.scit.spring7.util.FileService;
import net.scit.spring7.util.PageNavigator;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Value("${user.board.pageLimit}")
	private int pageLimit;
	
	/**
	 * 1) 단순조회: 게시글 전체 조회
	 * 2) 검색조회: 게시글의 트정 조건에 맞춘 조회
	 * @return
	 */
	@GetMapping("/boardList")
	public String boardList(
			@AuthenticationPrincipal LoginUserDetails loginUser
			,@PageableDefault(page=1) Pageable pageable
			,@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem
			,@RequestParam(name="searchWord", defaultValue = "") String searchWord
			,Model model) {
		
		// 2)  검색 + 페이징 기능
		
		Page<BoardDTO> list = boardService.selectAll(pageable,searchItem, searchWord);
		int totalPages = list.getTotalPages();		// DB가 계산해준 총 페이지 수
		int page = pageable.getPageNumber();		// 현재 사용자가 요청한 페이지
		PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);
		
		// 1) 검색 기능 추가
		//    searchItem과 searchWord는 null인 상태로 service로 전달되면 안됨
		//	   selectAll을 수정
		//    List<BoardDTO> list = boardService.selectAll(searchItem, searchWord);
		model.addAttribute("list",list);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("navi",navi);
		
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		
		return "/board/boardList";
	}
	
	/**
	 * 게시글쓰기 화면 요청
	 * @param model
	 * @return
	 */
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "/board/boardWrite";
	}
	/**
	 * 글쓰기 처리 요청
	 * @return
	 */
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO, Model model) {
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/boardList";
	}
	
	 /**
	 * 조회수 증가
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardSeq") Long boardSeq
			,@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem
			,@RequestParam(name="searchWord", defaultValue = "") String searchWord
			, Model model) {
		// DB에 boardSeq에 해당하는 하나의 게시글을 조회
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		boardService.incrementHitcount(boardSeq);
		
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		
		return "/board/boardDetail";
	}
	
	/**
	 * 삭제를 위한 요청
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardSeq") Long boardSeq
			,@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem
			,@RequestParam(name="searchWord", defaultValue = "") String searchWord
			, RedirectAttributes rttr
			) {
		boardService.deleteOne(boardSeq);
		
		rttr.addAttribute("searchItem",searchItem);
		rttr.addAttribute("searchWord",searchWord);
		
		return "redirect:/board/boardList";		
	}
	
	/**
	 * 수정을 위한 화면 요청
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam(name="boardSeq") Long boardSeq
			,@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem
			,@RequestParam(name="searchWord", defaultValue = "") String searchWord
			, Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		
		model.addAttribute("board",boardDTO);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		
		return "/board/boardUpdate";
	}
	
	/**
	 * 게시글 수정 처리 요청
	 * @param model
	 * @return
	 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(
			@ModelAttribute BoardDTO boardDTO
			,@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem
			,@RequestParam(name="searchWord", defaultValue = "") String searchWord
			, RedirectAttributes rttr
			) {
		//log.info("==수정데이터: {}", boardDTO.toString());
		boardService.updateBoard(boardDTO);
		rttr.addAttribute("searchItem",searchItem);
		rttr.addAttribute("searchWord",searchWord);
		
		return "redirect:/board/boardList";
	}
	

	/**
	 * 쓰레기통 아이콘을 클릭하여 파일만 삭제하는 작업
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/deleteFile")
	public String deleteFile(
			@RequestParam(name="boardSeq") Long boardSeq,
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
		    @RequestParam(name="searchWord", defaultValue = "") String searchWord,
			RedirectAttributes rttr) {
		// 1) 물리적으로 존재하는 파일을 삭제
		// 2) DB도 수정 --> file컬럼 두 개의 값을 null로
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		
		String savedFileName = boardDTO.getSavedFileName();
		String fullPath = uploadPath + "/" + savedFileName;
		
		FileService.deleteFile(fullPath); 
		boardService.deleteFile(boardSeq);
		
		rttr.addAttribute("boardSeq", boardSeq);
		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		
		return "redirect:/board/boardDetail";
	}
	/**
	 * 파일 다운로드
	 * @param boardSeq
	 * @param response
	 * @return
	 */
	@GetMapping("/download")
	public String download(@RequestParam(name="boardSeq") Long boardSeq
			, HttpServletResponse response) {
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		
		String savedFileName = boardDTO.getSavedFileName();
		String originalFileName = boardDTO.getOriginalFileName();		
		
		try {
			String tempName = URLEncoder.encode(originalFileName
					, StandardCharsets.UTF_8.toString());
			
			response.setHeader("Content-Disposition", "attachment;filename="+tempName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath + "/" + savedFileName;
		
		FileInputStream fin = null;	// 로컬에서 input 
		ServletOutputStream fout = null;	// 네트워크로 output
		
		try {
			fin = new FileInputStream(fullPath);
			fout = response.getOutputStream();
			
			FileCopyUtils.copy(fin, fout);
			
			fout.close();
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
