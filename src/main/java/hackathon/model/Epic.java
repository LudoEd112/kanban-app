package hackathon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "epics")
@Data
@NoArgsConstructor
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subtask> subtasks;

    @ManyToOne
    @JoinColumn(name = "kanban_board_id", nullable = false)
    private KanbanBoard kanbanBoard;
}

