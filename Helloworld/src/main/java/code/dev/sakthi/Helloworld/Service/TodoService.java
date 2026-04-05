package code.dev.sakthi.Helloworld.Service;


import code.dev.sakthi.Helloworld.models.Todo;
import code.dev.sakthi.Helloworld.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    // create
    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }


    public List<Todo> addBulkTasks(List<Todo> tasks) {
        return todoRepository.saveAll(tasks);
    }


    // get particular record
    public Todo getTodoById(Long id) {
       return todoRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not exists"));
    }

    // get all records
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Page<Todo> getAllTodoByPages(Pageable pageable){
        return todoRepository.findAll(pageable);
    }



    public Todo updateTodo(Long id, Todo todo){

        Todo existing = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(todo.getTitle());
        existing.setCompleted(todo.isCompleted());
        existing.setDescription(todo.getDescription());

        return todoRepository.save(existing);
    }



    public void deleteTodoById(Long id){
        todoRepository.deleteById(id);
    }

    public void deleteTodo(Todo todo){
        todoRepository.delete(todo);
    }

}
