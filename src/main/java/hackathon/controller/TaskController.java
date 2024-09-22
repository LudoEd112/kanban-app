package hackathon.controller;

import hackathon.model.Task;
import org.springframework.web.bind.annotation.*;
import hackathon.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/hacaton.kanban-boards/{boardId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasksForBoard(@PathVariable Long boardId) {
        return taskService.getTasksForBoard(boardId);
    }

    @PostMapping
    public Task createTask(@PathVariable Long boardId, @RequestBody Task task) {
        return taskService.createTask(boardId, task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long boardId, @PathVariable Long id) {
        return taskService.getTaskById(boardId, id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long boardId, @PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(boardId, id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long boardId, @PathVariable Long id) {
        taskService.deleteTask(boardId, id);
    }
}
