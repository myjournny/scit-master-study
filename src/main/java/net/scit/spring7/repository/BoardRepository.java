package net.scit.spring7.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring7.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	// 검색 + 페이징
	Page<BoardEntity> findByBoardTitleContains(String searchWord, PageRequest of);
	Page<BoardEntity> findByBoardWriterContains(String searchWord, PageRequest of);
	Page<BoardEntity> findByBoardContentContains(String searchWord, PageRequest of);
	
	// 검색기능을 위한 쿼리 메소드 선언
	// List<BoardEntity> findByBoardTitleContains(String searchWord, Sort by);
	// List<BoardEntity> findByBoardWriterContains(String searchWord, Sort by);
	// List<BoardEntity> findByBoardContentContains(String searchWord, Sort by);

	

}
