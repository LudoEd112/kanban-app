package hacaton.controller;

import hacaton.model.KanbanBoard;
import org.springframework.web.bind.annotation.*;
import hacaton.service.KanbanBoardService;

import java.util.List;

@RestController
@RequestMapping("/api/hacaton.kanban-boards")
public class KanbanBoardController {

    private final KanbanBoardService kanbanBoardService;

    public KanbanBoardController(KanbanBoardService kanbanBoardService) {
        this.kanbanBoardService = kanbanBoardService;
    }

    @GetMapping
    public List<KanbanBoard> getAllBoardsForUser() {
        return kanbanBoardService.getAllBoardsForUser();
    }

    @PostMapping
    public KanbanBoard createBoard(@RequestBody KanbanBoard kanbanBoard) {
        return kanbanBoardService.createBoard(kanbanBoard);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        kanbanBoardService.deleteBoard(id);
    }

    @GetMapping("/{id}")
    public KanbanBoard getBoardById(@PathVariable Long id) {
        return kanbanBoardService.getBoardById(id);
    }
}

