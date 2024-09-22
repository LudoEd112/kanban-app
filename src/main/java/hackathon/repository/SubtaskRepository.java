package hackathon.repository;

import hackathon.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    // Метод для получения всех подзадач, связанных с конкретным эпиком
    List<Subtask> findByEpicId(Long epicId);

    // Метод для получения всех подзадач, привязанных к конкретной доске
    List<Subtask> findByKanbanBoardId(Long kanbanBoardId);
}

