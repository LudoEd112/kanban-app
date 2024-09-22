package hackathon.service;

import hackathon.model.KanbanBoard;
import hackathon.model.User;
import org.springframework.stereotype.Service;
import hackathon.repository.KanbanBoardRepository;

import java.util.List;

@Service
public class KanbanBoardService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final UserService userService;

    public KanbanBoardService(KanbanBoardRepository kanbanBoardRepository, UserService userService) {
        this.kanbanBoardRepository = kanbanBoardRepository;
        this.userService = userService;
    }

    public List<KanbanBoard> getAllBoardsForUser() {
        User currentUser = userService.getCurrentUser();
        return kanbanBoardRepository.findByUserId(currentUser.getId());
    }

    public KanbanBoard createBoard(KanbanBoard kanbanBoard) {
        User currentUser = userService.getCurrentUser();
        kanbanBoard.setUser(currentUser);
        return kanbanBoardRepository.save(kanbanBoard);
    }

    public void deleteBoard(Long boardId) {
        kanbanBoardRepository.deleteById(boardId);
    }

    public KanbanBoard getBoardById(Long boardId) {
        return kanbanBoardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Kanban board not found"));
    }
}

