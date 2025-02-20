package net.scit.spring7.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring7.entity.ReplyEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ReplyDTO {
	private Long replySeq;
	private Long boardSeq;
	private String replyWriter;
	private String replyContent;
	private LocalDateTime createDate;
	
	// Entity --> DTO
	public static ReplyDTO toDTO(ReplyEntity entity, Long bseq) {
		return ReplyDTO.builder()
				.replySeq(entity.getReplySeq())
				.boardSeq(bseq)
				.replyWriter(entity.getReplyWriter())
				.replyContent(entity.getReplyContent())
				.createDate(entity.getCreateDate())
				.build();
				
	}
}

