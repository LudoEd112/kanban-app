package hackathon.repository;

import hackathon.model.KanbanBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Long> {
    List<KanbanBoard> findByUserId(Long userId);
}

