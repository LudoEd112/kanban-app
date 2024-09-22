package hacaton.repository;

import hacaton.model.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpicRepository extends JpaRepository<Epic, Long> {
    List<Epic> findByKanbanBoardId(Long kanbanBoardId);
}

