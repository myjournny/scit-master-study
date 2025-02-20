package net.scit.spring6.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.dto.BookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_seq")
	private Integer bookSeq;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="writer", nullable = false)
	private String writer;
	
	@Column(name="publisher", nullable = false)
	private String publisher;
	
	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	
	@Column(name="price")
	private Integer price;
	
	// OneToMany: BookEntity는 여러 ReadingNoteEntity를 가짐
 	@OneToMany(mappedBy = "bookEntity", cascade = CascadeType.REMOVE)
 	private List<ReadingNoteEntity> readingNotes = new ArrayList<>();
	
	public static BookEntity toEntity(BookDTO bookDTO) {
		return BookEntity.builder()
				.bookSeq(bookDTO.getBookSeq())
				.title(bookDTO.getTitle())
				.writer(bookDTO.getWriter())
				.publisher(bookDTO.getPublisher())
				.purchaseDate(bookDTO.getPurchaseDate())
				.price(bookDTO.getPrice())
				.build();
				
	}

}