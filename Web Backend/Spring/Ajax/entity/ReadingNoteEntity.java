package net.scit.spring6.entity;

import java.time.LocalDate;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.dto.ReadingNoteDTO;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Builder
@Entity
@Table(name="reading_note")
public class ReadingNoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reading_seq")
    private Integer readingSeq;

    @Column(name = "read_status")
    private String readStatus;

    @Column(name = "read_date")
    private LocalDate readDate;

    @Column(name = "book_review")
    private String bookReview;
    
    //@OneToOne
    //@JoinColumn(name = "book_seq", referencedColumnName = "book_seq")
    //private BookEntity bookEntity;
    
    // ManyToOne
 	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name= "book_seq")
 	private BookEntity bookEntity;

	public static ReadingNoteEntity toEntity(ReadingNoteDTO readingDTO, BookEntity bookentity) {
		ReadingNoteEntity entity = ReadingNoteEntity.builder()
				.readingSeq(readingDTO.getReadingSeq())
				.readStatus(readingDTO.getReadStatus())
				.readDate(readingDTO.getReadDate())
				.bookReview(readingDTO.getBookReview())
				.bookEntity(bookentity)
				.build();
			return entity;
	}
	
}







