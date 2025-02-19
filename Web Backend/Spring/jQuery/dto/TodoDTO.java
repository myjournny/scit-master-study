package net.scit.todolist.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todolist.entity.TodoEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@ToString

@Builder
public class TodoDTO {
    private Long seqno;
    private LocalDate regdate;
    private String status;
    private String importance;
    private String categories;
    private String todo;
    
    public static TodoDTO toDTO(TodoEntity entity) {
        return TodoDTO.builder()
                .seqno(entity.getSeqno())
                .regdate(entity.getRegdate())
                .status(entity.getStatus())
                .importance(entity.getImportance())
                .categories(entity.getCategories())
                .todo(entity.getTodo())
                .build();
    }
}
