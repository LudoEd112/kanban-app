package hacaton.controller;

import hacaton.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hacaton.repository.EpicRepository;
import hacaton.repository.KanbanBoardRepository;
import hacaton.repository.SubtaskRepository;
import hacaton.repository.TaskRepository;
import hacaton.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/hacaton.kanban")
public class KanbanController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EpicRepository epicRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private KanbanBoardRepository kanbanBoardRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public List<Task> getTasks(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskRepository.findAll(); // Можно расширить, например, фильтровать по доске или статусу
    }

    @PostMapping("/boards/{boardId}/tasks")
    public Task createTask(@PathVariable Long boardId, @RequestParam String title, @RequestParam String description) {
        KanbanBoard board = kanbanBoardRepository.findById(boardId).orElseThrow();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setKanbanBoard(board);
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }

    @PostMapping("/boards/{boardId}/epics")
    public Epic createEpic(@PathVariable Long boardId, @RequestParam String title, @RequestParam String description) {
        KanbanBoard board = kanbanBoardRepository.findById(boardId).orElseThrow();
        Epic epic = new Epic();
        epic.setTitle(title);
        epic.setDescription(description);
        epic.setKanbanBoard(board);
        return epicRepository.save(epic);
    }

    @PostMapping("/epics/{epicId}/subtasks")
    public Subtask createSubtask(@PathVariable Long epicId, @RequestParam String title, @RequestParam String description) {
        Epic epic = epicRepository.findById(epicId).orElseThrow();
        Subtask subtask = new Subtask();
        subtask.setTitle(title);
        subtask.setDescription(description);
        subtask.setEpic(epic);
        subtask.setKanbanBoard(epic.getKanbanBoard());
        subtask.setStatus(TaskStatus.TODO);
        return subtaskRepository.save(subtask);
    }
}

