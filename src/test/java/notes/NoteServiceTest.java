package notes;

import notes.model.Note;
import notes.model.NoteToView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import priority.Priority;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NoteServiceTest {

    NoteService underTest;

    @BeforeEach
    void setUp() {
        underTest = new NoteService();
    }

    @Test
    void testConversionBetweenNoteAndViewToNote() {
        NoteToView noteToView = new NoteToView(null, "admin", "some comment", "Normal priority", LocalDate.now().toString());

        Note note = underTest.createNoteFromNoteToView(noteToView);

        assertEquals(note.getId(), noteToView.getId());
        assertEquals(note.getUserFk(), noteToView.getUserFk());
        assertEquals(note.getPriority(), underTest.getPriorityToPersist(noteToView.getPriority()));
        assertEquals(note.getComment(), noteToView.getComment());
        assertEquals(note.getCreated().toString(), noteToView.getCreated());
    }

    @Test
    void testGetPriorityToDisplayWithVeryLowPriority() {
        assertEquals("Very low priority", underTest.getPriorityToDisplay(Priority.VERY_LOW));
    }

    @Test
    void testGetPriorityToDisplayWithLowPriority() {
        assertEquals("Low priority", underTest.getPriorityToDisplay(Priority.LOW));
    }

    @Test
    void testGetPriorityToDisplayWithNormalPriority() {
        assertEquals("Normal priority", underTest.getPriorityToDisplay(Priority.NORMAL));
    }

    @Test
    void testGetPriorityToDisplayWithHighPriority() {
        assertEquals("High priority", underTest.getPriorityToDisplay(Priority.HIGH));
    }

    @Test
    void testGetPriorityToDisplayWithVeryHighPriority() {
        assertEquals("Very high priority", underTest.getPriorityToDisplay(Priority.VERY_HIGH));
    }

    @Test
    void testGetPriorityToPersistWithVeryLowPriorityString() {
        assertEquals(Priority.VERY_LOW, underTest.getPriorityToPersist("Very low priority"));
    }

    @Test
    void testGetPriorityToPersistWithLowPriorityString() {
        assertEquals(Priority.LOW, underTest.getPriorityToPersist("Low priority"));
    }

    @Test
    void testGetPriorityToPersistWithNormalPriorityString() {
        assertEquals(Priority.NORMAL, underTest.getPriorityToPersist("Normal priority"));
    }

    @Test
    void testGetPriorityToPersistWithHighPriorityString() {
        assertEquals(Priority.HIGH, underTest.getPriorityToPersist("High priority"));
    }

    @Test
    void testGetPriorityToPersistWithVeryHighPriorityString() {
        assertEquals(Priority.VERY_HIGH, underTest.getPriorityToPersist("Very high priority"));
    }

    @Test
    void testGetPriorityToPersistWhenExceptionIllegalArgumentIsPassed() {
        assertThrows(IllegalArgumentException.class,() -> underTest.getPriorityToPersist("bad data"));
    }
}
