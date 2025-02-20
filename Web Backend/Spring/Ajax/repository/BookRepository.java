package net.scit.spring6.repository;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring6.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer>{

	void deleteById(Integer bookSeq);

	List<BookEntity> findByTitleContains(String searchWord);

	List<BookEntity> findByWriterContains(String searchWord);

	List<BookEntity> findByPublisherContains(String searchWord);

}
