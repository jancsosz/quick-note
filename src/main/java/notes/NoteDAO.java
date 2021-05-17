package notes;

import jpa.GenericJpaDao;
import notes.model.Note;

import javax.persistence.Persistence;

/**
 * Data Access Object for Note Entities.
 */
public class NoteDAO extends GenericJpaDao<Note> {

    private static NoteDAO instance;

    private NoteDAO(Class<Note> entityClass) {
        super(entityClass);
    }

    public static NoteDAO getInstance() {
        if (instance == null) {
            instance = new NoteDAO(Note.class);
            instance.setEntityManager(Persistence.createEntityManagerFactory("mysql-unit").createEntityManager());
        }
        return instance;
    }
}
