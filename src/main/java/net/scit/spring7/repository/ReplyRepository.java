package net.scit.spring7.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
	// 댓글 조회
	List<ReplyEntity> findAllByBoardEntity(Optional<BoardEntity> temp, Sort by);

}
