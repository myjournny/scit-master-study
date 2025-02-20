package net.scit.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	
	public void insert(BookDTO bookDTO) {
		BookEntity entity = BookEntity.toEntity(bookDTO);
		
		bookRepository.save(entity);
	}

	public List<BookDTO> selectAll() {
		java.util.List<BookEntity> temp = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "purchaseDate"));
		
		java.util.List<BookDTO> list = new ArrayList<>();
		
		temp.forEach((entity) -> list.add(BookDTO.toDTO(entity)));
		
		return list;
	}
	
	
	@Transactional
	public void bookDelete(BookDTO bookDTO) {
		 bookRepository.deleteById(bookDTO.getBookSeq());
	}

	public List<BookDTO> searchBooks(String searchItem, String searchWord) {
		List<BookEntity> temp;
		
		System.out.println("Search parameters - Item: " + searchItem + ", Word: " + searchWord);
	    
	    switch (searchItem) {
	        case "title":
	            temp = bookRepository.findByTitleContains(searchWord);
	            break;
	        case "writer":
	            temp = bookRepository.findByWriterContains(searchWord);
	            break;
	        case "publisher":
	            temp = bookRepository.findByPublisherContains(searchWord);
	            break;
	        default:
	            throw new IllegalArgumentException("잘못된 검색 기준입니다.");
	    }
	    
	    // Add logging
	    System.out.println("Search results count: " + temp.size());

	    List<BookDTO> list = new ArrayList<>();
	    temp.forEach((entity) -> list.add(BookDTO.toDTO(entity)));

	    return list;
	}
}