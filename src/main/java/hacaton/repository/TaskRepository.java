package hacaton.repository;

import hacaton.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIdAndKanbanBoardId(Long taskId, Long kanbanBoardId);
}


