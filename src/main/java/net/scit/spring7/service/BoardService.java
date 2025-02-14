package net.scit.spring7.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.repository.BoardRepository;
import net.scit.spring7.util.FileService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Value("${user.board.pageLimit}")
	private int pageLimit;
	
	/**
	 * 1) 단순조회: 게시글 전체 목록 조회
	 * 2) 검색조회: 쿼리 메소드 사용
	 * 3) 페이징 추가
	 * @param pageable 
	 * 
	 * @param searchWord :boardTitle findByBoardTitleContains("쥐")
	 * @param searchItem :나리
	 */
	public Page<BoardDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
		// -1을 한 이유 : DB의 Page는 0부터 시작함. 사용자는 1을 요청하기 때문!!
		// 사용자가 요청한 페이지 번호
		int pageNumber = pageable.getPageNumber()-1;
		
		Page<BoardEntity> temp = null;
		
		switch (searchItem) {
			case "boardTitle":
				temp = boardRepository.findByBoardTitleContains(
						searchWord
						, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
				break;
			case "boardWriter":
				temp = boardRepository.findByBoardWriterContains(
						searchWord
						, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
			break;
			case "boardContent":
				temp = boardRepository.findByBoardContentContains(
						searchWord
						, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
				break;
		}
		
		// 1) 단순조회
		// List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		Page<BoardDTO> list = null;
		
		// 2) Lamba 객체, Stream : List, Set, Map / 중간연산, 최종연산
		
		list = temp.map((entity) -> BoardDTO.toDTO(entity));
		/*
		log.info("getSize: {}", list.getSize());	//한 페이지당 글 개수 (내가 설정한 것) 10
		log.info("getTotalElements: {}", list.getSize());	//전체 글 개수
		log.info("getTotalPages: {}", list.getTotalPages()); //총 페이지 수
		log.info("getNumber: {}", list.getNumber()); // 요청한 페이지
		log.info("getNumberOfElements: {}", list.getNumberOfElements()); // 현재페이지의 총 글개수
		log.info("isFirst: {}", list.isFirst()); // 첫번째 페이지냐
		log.info("isLast: {}", list.isLast()); // 마지막 페이지냐
		log.info("getContent().get(0): {}", list.getContent().get(0)); //현 페이지 첫번째 게시글 정보
		*/
		
		return list;
	}
//	public List<BoardDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
//		// 2) 검색조회
//		List<BoardEntity> temp = null;
//		
//		switch (searchItem) {
//			case "boardTitle":
//				temp = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//			case "boardWriter":
//				temp = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//			case "boardContent":
//				temp = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//		}
//		
//		// 1) 단순조회
//		// List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
//		List<BoardDTO> list = new ArrayList<>();
//		
//		temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
//		
//		return list;
//	}
	
	/**
	 * 전달받은 BoardDTO를 DB에 저장
	 * 첨부파일 여부에 따라 DB의 두 컬럼값을 수정
	 * @param boardDTO
	 */
	public void insertBoard(BoardDTO boardDTO) {
		MultipartFile uploadFile = boardDTO.getUploadFile();
		String savedFileName = null;
		String originalFileName = null;
		
		if(!uploadFile.isEmpty() ) {
		
			savedFileName = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
			originalFileName = boardDTO.getUploadFile().getOriginalFilename();
		
		}
		boardDTO.setSavedFileName(savedFileName);
		boardDTO.setOriginalFileName(originalFileName);
		
		BoardEntity entity = BoardEntity.toEntity(boardDTO);
		
		boardRepository.save(entity);
	}
	
	/**
	 * BoardSeq에 해당하는 글을 조회
	 * @param boardSeq
	 * @return
	 */
	public BoardDTO selectOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(!temp.isPresent()) return null;
		
		BoardEntity entity = temp.get();
		return BoardDTO.toDTO(entity);

	}
	
	/**
	 * BoardSeq레코드에 대한 조회수 증가
	 * 
	 * 1) 조회: findById(boardSEQ)
	 * 2) hitcount를 get한 후 +1 ==> set
	 * 
	 * 
	 * @param boardSeq
	 */
	
	@Transactional
	public void incrementHitcount(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);

		if(!temp.isPresent()) return ;
		BoardEntity entity = temp.get();
		
		int hitcount = entity.getHitCount() + 1;
		entity.setHitCount(hitcount);
		
	}
	
	/**
	 * DB에 게시글을 삭제
	 * @param boardSeq
	 */
	@Transactional
	public void deleteOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(!temp.isPresent()) return;
		
		// temp를 뒤져서 savedfilename이 존재하면 물리적으로 삭제
		String savedFileName = temp.get().getSavedFileName();
		if(savedFileName != null) {
			String fullPath = uploadPath +"/" + savedFileName;
			FileService.deleteFile(fullPath);
		}
		
	    boardRepository.deleteById(boardSeq);
		
	}
	
	/**
	 * DB에 수정처리
	 * @param boardSeq
	 */
	@Transactional
	public void updateBoard(BoardDTO boardDTO) {
		// 1) 첨부파일이 있는지 확인
		MultipartFile file = boardDTO.getUploadFile();
		
		String newFile = !file.isEmpty() ? file.getOriginalFilename() : null;
		
		// 1) 수정하려는 데이터가 있는지 확인
		Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getBoardSeq());

		if(!temp.isPresent()) return;
		
		// 2) 있으면 dto -> entity로 변환
		BoardEntity entity = temp.get();
		
		
		// 기존파일이 DB에 저장되어 있는지 확인
		String oldFile = entity.getSavedFileName();	
		
		// (1) 기존 파일(old)이 있고 업로드한 파일(new)도 있다면 
		// 		-- 하드 디스크에서는 기존 파일을 삭제, 업로드한 파일을 저장
		//		-- DB에는 업로드한 파일로 두 개의 컬럼을 업데이트
		// (2) 기존 파일이 없고 업로드한 파일(new)이 있다면 
		// 		-- 하드 디스크에서는 업로드한 파일을 저장
		//		-- DB에는 업로드한 파일로 두 개의 컬럼을 업데이트
		
		String savedFileName = null;
		// 업로드한 파일이 있다면
		if (newFile != null) {
			savedFileName = FileService.saveFile(file, uploadPath);
			if(oldFile != null) {
				String fullPath = uploadPath +"/" + oldFile;
				FileService.deleteFile(fullPath);	
			}
			
		}
		entity.setSavedFileName(savedFileName);
		entity.setOriginalFileName(newFile);
		entity.setBoardTitle(boardDTO.getBoardTitle());
		entity.setBoardContent(boardDTO.getBoardContent());
		//entity.setUpdateDate(LocateDateTime.now());
	
		//boardRepository.save(entity); // 없어도 됨
		
	}

	/**
	 * file명이 들어있는 2개의 컬럼의 값을 null로!
	 * @param boardSeq
	 */
	@Transactional
	public void deleteFile(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(temp.isPresent()) {
			BoardEntity entity = temp.get();
			
			entity.setOriginalFileName(null);
			entity.setSavedFileName(null);
		}
		
	}
	
}
