package hackathon.service;

import hackathon.model.TaskHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hackathon.repository.TaskHistoryRepository;

import java.time.LocalDateTime;

@Service
public class HistoryService {

    @Autowired
    private TaskHistoryRepository historyRepository;

    public void recordTaskAction(Long taskId, String action) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskId);
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());
        historyRepository.save(history);
    }
}

