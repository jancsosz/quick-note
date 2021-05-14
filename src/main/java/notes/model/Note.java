package notes.model;

import javafx.beans.property.SimpleStringProperty;
import jpa.PriorityConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import priority.Priority;

import javax.persistence.*;
import java.time.LocalDate;

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
     * The actual value of the note.
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
     * Timestamp when note was saved.
     */
    @Column(nullable = false)
    private LocalDate created;
}
