package hacaton.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subtasks")
@Data
@NoArgsConstructor
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "epic_id", nullable = false)
    private Epic epic;

    @ManyToOne
    @JoinColumn(name = "kanban_board_id", nullable = false)
    private KanbanBoard kanbanBoard;
}

