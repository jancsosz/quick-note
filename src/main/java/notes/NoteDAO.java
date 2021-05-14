package notes;

import jpa.GenericJpaDao;
import notes.model.Note;

import javax.persistence.Persistence;

public class NoteDAO extends GenericJpaDao<Note> {

    private static NoteDAO instance;

    private NoteDAO(Class<Note> entityClass) {
        super(entityClass);
    }

    public static NoteDAO getInstance() {
        if (instance == null) {
            instance = new NoteDAO(Note.class);
            instance.setEntityManager(Persistence.createEntityManagerFactory("oracle-unit").createEntityManager());
        }
        return instance;
    }
}
