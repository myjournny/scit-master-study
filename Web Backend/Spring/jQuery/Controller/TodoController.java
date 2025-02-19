package net.scit.todolist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.todolist.dto.TodoDTO;
import net.scit.todolist.service.TodoService;

@Controller
@RequestMapping("/todo")
@Slf4j
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping("/selectAll")
    public String selectAll(Model model) {
        List<TodoDTO> list = service.selectAll();
        model.addAttribute("list", list);
        
        return "todolist";
    }
    
    @PostMapping("/selectChoice")
    public String selectChoice(
    		@RequestParam(name="importance", defaultValue="높음") String importance
    		, @RequestParam(name="categories", defaultValue="회사") String categories
    		, Model model) {
        List<TodoDTO> list = service.selectChoice(importance, categories);
        model.addAttribute("list", list);
        
        return "todolist";
    }    

    @PostMapping("/insert")
    public String insert(TodoDTO todo) {
        
        service.insert(todo);

        return "redirect:/todo/selectAll";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name="seqno") Integer seqno) {

        service.delete(seqno);

        return "redirect:/todo/selectAll";
    }
}
