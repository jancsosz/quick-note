package notes;

import lombok.extern.slf4j.Slf4j;
import notes.model.Note;
import notes.model.NoteToView;
import priority.Priority;

import java.time.LocalDate;

/**
 * Model/service class for Note data transfer between view format and persisted format.
 */
@Slf4j
public class NoteService {

    /**
     * Creates a displayable NoteToView object from a Note object.
     * @param note Note object to convert.
     * @return Created NoteToView object.
     */
    public NoteToView createNoteToViewFromNote(Note note) {
        log.info("Created NoteToView from Note {}", note);
        return new NoteToView(note.getId(), note.getUserFk(), note.getComment(), getPriorityToDisplay(note.getPriority()), note.getCreated().toString());
    }

    /**
     * Creates a Note object from a displayed NoteToView object for data transfer.
     * @param noteToView displayed NoteToView object.
     * @return Entity Note object.
     */
    public Note createNoteFromNoteToView(NoteToView noteToView) {
        log.info("Created Note from NoteToView {}", noteToView);
        return new Note(noteToView.getId(), noteToView.getUserFk(), noteToView.getComment(), getPriorityToPersist(noteToView.getPriority()), LocalDate.now());
    }

    /**
     * Creates displayable priorities based on the Enum.
     * @param priority Priority Enum object. (from DB)
     * @return a String based on the priority.
     */
    public String getPriorityToDisplay(Priority priority) {
        switch(priority) {
            case VERY_LOW:
                return "Very low priority";
            case LOW:
                return "Low priority";
            case NORMAL:
                return "Normal priority";
            case HIGH:
                return "High priority";
            case VERY_HIGH:
                return "Very high priority";
            default:
                throw new IllegalArgumentException("Bad priority");
        }
    }

    /**
     * Converts displayed String value of priority into Priority enum.
     * @param displayedPriority String to convert.
     * @return Priority Enum.
     */
    public Priority getPriorityToPersist(String displayedPriority) {
        switch (displayedPriority) {
            case "Very low priority":
                return Priority.VERY_LOW;
            case "Low priority":
                return Priority.LOW;
            case "Normal priority":
                return Priority.NORMAL;
            case "High priority":
                return Priority.HIGH;
            case "Very high priority":
                return Priority.VERY_HIGH;
            default:
                throw new IllegalArgumentException("Unrecognized priority string");
        }
    }
}
