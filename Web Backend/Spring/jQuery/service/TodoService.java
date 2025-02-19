package net.scit.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.scit.todolist.dto.TodoDTO;
import net.scit.todolist.entity.TodoEntity;
import net.scit.todolist.repository.TodoRepository;


@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository repository;

	public List<TodoDTO> selectAll() {
		List<TodoEntity> temp = repository.findAll(Sort.by(Sort.Direction.DESC, "regdate")); 
		List<TodoDTO> list = new ArrayList<>();

		temp.forEach((entity) -> list.add(TodoDTO.toDTO(entity)));

		return list;
	}

	public void insert(TodoDTO todo) {
		TodoEntity entity = TodoEntity.toEntity(todo);
		repository.save(entity);
	}

	public void delete(Integer seqno) {
		repository.deleteById(seqno);
	}

	@Transactional
	public List<TodoDTO> selectChoice(String importance, String categories) {
		List<TodoEntity> temp = repository.findByImportanceAndCategories(importance, categories, Sort.by(Sort.Direction.DESC, "regdate")); 
		List<TodoDTO> list = new ArrayList<>();

		temp.forEach((entity) -> list.add(TodoDTO.toDTO(entity)));

		return list;		
	}
}
