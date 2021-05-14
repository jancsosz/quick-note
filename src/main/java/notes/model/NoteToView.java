package notes.model;

import javafx.beans.property.SimpleStringProperty;


public class NoteToView {
    private SimpleStringProperty userFk;
    private SimpleStringProperty comment;
    private SimpleStringProperty priority;
    private SimpleStringProperty created;

    public NoteToView(String userFk, String comment, String priority, String created) {
        this.userFk = new SimpleStringProperty(userFk);
        this.comment = new SimpleStringProperty(comment);
        this.priority = new SimpleStringProperty(priority);
        this.created = new SimpleStringProperty(created);
    }

    public String getUserFk() {
        return userFk.get();
    }

    public SimpleStringProperty userFkProperty() {
        return userFk;
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public String getPriority() {
        return priority.get();
    }

    public SimpleStringProperty priorityProperty() {
        return priority;
    }

    public String getCreated() {
        return created.get();
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }
}
