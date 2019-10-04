package com.haopn.test.service;

import com.haopn.test.model.Todo;
import com.haopn.test.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    /*public List<Todo> getAll() {
        return todoRepository.findAll()
                .stream()
                .filter(t -> t.getTitle().startsWith("xxx"))
                .collect(Collectors.toList());
    }*/

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

}
