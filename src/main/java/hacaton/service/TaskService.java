package hacaton.service;

import hacaton.model.KanbanBoard;
import hacaton.model.Task;
import org.springframework.stereotype.Service;
import hacaton.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final KanbanBoardService kanbanBoardService;

    public TaskService(TaskRepository taskRepository, KanbanBoardService kanbanBoardService) {
        this.taskRepository = taskRepository;
        this.kanbanBoardService = kanbanBoardService;
    }

    public List<Task> getTasksForBoard(Long boardId) {
        KanbanBoard board = kanbanBoardService.getBoardById(boardId);
        return board.getTasks();
    }

    public Task createTask(Long boardId, Task task) {
        KanbanBoard board = kanbanBoardService.getBoardById(boardId);
        task.setKanbanBoard(board);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long boardId, Long taskId) {
        try {
            return (Task) taskRepository.findByIdAndKanbanBoardId(taskId, boardId);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Task not found");
        }
    }

    public Task updateTask(Long boardId, Long taskId, Task updatedTask) {
        Task task = getTaskById(boardId, taskId);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        return taskRepository.save(task);
    }

    public void deleteTask(Long boardId, Long taskId) {
        Task task = getTaskById(boardId, taskId);
        taskRepository.delete(task);
    }
}
