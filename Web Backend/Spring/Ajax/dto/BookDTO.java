package net.scit.spring6.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.entity.BookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookDTO {
	private Integer bookSeq;
	private String title;
	private String writer;
	private String publisher;
	private LocalDate purchaseDate;
	private Integer price;
	
	public static BookDTO toDTO(BookEntity bookEntity) {
		return BookDTO.builder()
				.bookSeq(bookEntity.getBookSeq())
				.title(bookEntity.getTitle())
				.writer(bookEntity.getWriter())
				.publisher(bookEntity.getPublisher())
				.purchaseDate(bookEntity.getPurchaseDate())
				.price(bookEntity.getPrice())
				.build();
				
	}
}