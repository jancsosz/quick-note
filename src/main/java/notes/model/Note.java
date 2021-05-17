package notes.model;

import jpa.PriorityConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import priority.Priority;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Entity class to represent Note objects.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * User's username who created the note.
     */
    @Column(nullable = false)
    private String userFk;

    /**
     * Description of note.
     */
    @Column(nullable = false)
    private String comment;

    /**
     * Priority of note.
     */
    @Column(nullable = false)
    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    /**
     * Timestamp when note was created.
     */
    @Column(nullable = false)
    private LocalDate created;
}
