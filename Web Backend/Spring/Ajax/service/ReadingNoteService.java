package net.scit.spring6.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.dto.ReadingNoteDTO;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.entity.ReadingNoteEntity;
import net.scit.spring6.repository.BookRepository;
import net.scit.spring6.repository.ReadingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadingNoteService {
	private final BookRepository bookRepository;
	private final ReadingRepository readingRepository;
	
	/** BookService로 이동할 것
	 * bookSeq값에 해당하는 책 정보 조회
	 * @param bookSeq
	 * @return
	 */
	public BookDTO selectBook(Integer bookSeq) {
		Optional<BookEntity> entity = bookRepository.findById(bookSeq);
		BookDTO bookDTO = null;
		if(entity.isPresent()) {
			bookDTO = BookDTO.toDTO(entity.get());
		}
		return bookDTO;
	}

	/** (중요!!!)
	 * 독서노트 등록 (먼저, 책의 대한 정보를 조회한 후에!
	 * @param readingDTO
	 */
	@Transactional
	public void insertReading(ReadingNoteDTO readingDTO) {
		Optional<BookEntity> temp = bookRepository.findById(readingDTO.getBookSeq());

		if(temp.isPresent()) {
			BookEntity bookEntity = temp.get();
			ReadingNoteEntity entity = ReadingNoteEntity.toEntity(readingDTO, bookEntity);	
			readingRepository.save(entity);
		}
	}

	/**
	 * 현재까지 기록된 모든 독서노트 조회
	 * @return
	 */
	public List<ReadingNoteDTO> selectAll(Integer bookSeq) {
		List<ReadingNoteEntity> temp = readingRepository.findAll();
		List<ReadingNoteDTO> list = new ArrayList<>(); 
		
		temp.forEach((entity) -> list.add(ReadingNoteDTO.toDTO(entity, bookSeq)));
		return list;
	}
}

