package code.dev.sakthi.Helloworld.controller;

import code.dev.sakthi.Helloworld.Service.TodoService;
import code.dev.sakthi.Helloworld.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todo")
public class TodoController  {

    @Autowired
    TodoService todoService;

    @GetMapping("/secure")
    public String secure() {
        return "You are logged in ✅";
    }

    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @PostMapping("/tasks/bulk")
    public ResponseEntity<List<Todo>> addBulkTasks(@RequestBody List<Todo> tasks) {
        return new ResponseEntity<>(todoService.addBulkTasks(tasks),HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200",description = "Todo retrieved"),
                @ApiResponse(responseCode = "404", description = "Todo not found")
            }
    )
    @GetMapping("/getTodo/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        try{
            Todo todo = todoService.getTodoById(id);
            return ResponseEntity.ok(todo);
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTodos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(todoService.getAllTodos(),HttpStatus.OK);

    }

    @GetMapping("/getPages")
    public ResponseEntity<Page<Todo>> getAllTodoByPages(Pageable pageable){
        return new ResponseEntity<>(todoService.getAllTodoByPages(pageable),HttpStatus.OK);
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.updateTodo(id, todo), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

//    @GetMapping("/get")
//    String getToDo(){
//       // return todoService.getToDo();
//        return "todolist";
//    }
//
//
//    @GetMapping("/get/{id}")
//    String getTodo(@PathVariable int id){
//        return "To do with id :"+id;
//    }
//
//
//    @GetMapping("/getById") /getById?id=sakthi&password=123
//    String getTodoById(@RequestParam("id") int userid,@RequestParam String password){
//        return "User Id : "+userid +" Password : "+password;
//    }
//
//    /*
//    @PostMapping("/createuser")
//    String createUser(@RequestBody String body){
//        return body;
//    }
//
//    @PutMapping("/{id}")
//    String updateUser(@PathVariable int id,@RequestBody String body){
//        return body;
//    }
//
//    @DeleteMapping("/{id}")
//    String deleteUser(@PathVariable int id){
//        return "User id : "+id+"deleted";
//    }
//     */

}
