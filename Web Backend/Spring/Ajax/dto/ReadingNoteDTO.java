package net.scit.spring6.dto;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.entity.ReadingNoteEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Builder
public class ReadingNoteDTO {
	private Integer readingSeq;
	private String readStatus;
	private LocalDate readDate;
	private String bookReview;
	private Integer bookSeq;
	
	private BookDTO bookDTO;
	
	public static ReadingNoteDTO toDTO(ReadingNoteEntity entity, Integer bSeq) {
		ReadingNoteDTO dto = ReadingNoteDTO.builder()
				.readingSeq(entity.getReadingSeq())
				.readStatus(entity.getReadStatus())
				.readDate(entity.getReadDate())
				.bookReview(entity.getBookReview())
				.bookDTO(BookDTO.toDTO(entity.getBookEntity()))
				.bookSeq(bSeq)
				.build();
			return dto;
	}
}







