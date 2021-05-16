package notes.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoteToView {
    private Long id;
    private SimpleStringProperty userFk;
    private SimpleStringProperty comment;
    private SimpleStringProperty priority;
    private SimpleStringProperty created;

    public NoteToView(Long id, String userFk, String comment, String priority, String created) {
        this.id = id;
        this.userFk = new SimpleStringProperty(userFk);
        this.comment = new SimpleStringProperty(comment);
        this.priority = new SimpleStringProperty(priority);
        this.created = new SimpleStringProperty(created);
    }

    public void setUserFk(String userFk) {
        this.userFk.set(userFk);
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public String getUserFk() {
        return userFk.get();
    }

    public String getComment() {
        return comment.get();
    }

    public String getPriority() {
        return priority.get();
    }

    public String getCreated() {
        return created.get();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NoteToView{" +
                "id=" + id +
                ", userFk=" + userFk +
                ", comment=" + comment +
                ", priority=" + priority +
                ", created=" + created +
                '}';
    }
}
