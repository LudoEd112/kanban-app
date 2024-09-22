package hackathon.repository;

import hackathon.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    // Метод для получения истории по идентификатору задачи
    List<TaskHistory> findByTaskId(Long taskId);
}

