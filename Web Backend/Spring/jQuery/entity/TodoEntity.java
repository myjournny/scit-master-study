package net.scit.todolist.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todolist.dto.TodoDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@ToString

@Builder
@Entity
@Table(name="todolist")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqno;
	private LocalDate regdate;
	private String status;
	private String importance;
	private String categories;
	private String todo;
	
	public static TodoEntity toEntity(TodoDTO dto) {
	    return TodoEntity.builder()
	            .seqno(dto.getSeqno())
	            .regdate(dto.getRegdate())
	            .status(dto.getStatus())
	            .importance(dto.getImportance())
	            .categories(dto.getCategories())
	            .todo(dto.getTodo())
	            .build();
	}
	
}
