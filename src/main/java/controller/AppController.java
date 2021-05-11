package controller;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import notes.model.Note;
import user.model.User;

import java.time.LocalDate;

public class AppController {
    private String loggedInUsername;
    private String loggedInPassword;

    private ObservableList<Note> data =
            FXCollections.observableArrayList(
                    new Note(null, "jancsosz", "első note", Note.Priority.NORMAL, LocalDate.now())
            );

    @FXML
    private Label title;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private AnchorPane notes;

    @FXML
    private TableView noteTable;

    @FXML
    private Pane usersNotes;

    @FXML
    private Pane ownNotes;

    @FXML
    private Button addNote;

    public void initdata(String username, String password) {
        this.loggedInUsername = username;
        this.loggedInPassword = password;
    }

     private void initialize() {
         TableColumn usernameCol = new TableColumn("Username");
         usernameCol.setMinWidth(70);

         usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
         usernameCol.setCellValueFactory(new PropertyValueFactory<Note, String>("user_fk"));

         TableColumn noteCol = new TableColumn("Note");
         noteCol.setMinWidth(70);

         noteCol.setCellFactory(TextFieldTableCell.forTableColumn());
         noteCol.setCellValueFactory(new PropertyValueFactory<Note, String>("comment"));

         TableColumn priorityCol = new TableColumn("Priority");
         priorityCol.setMinWidth(70);

         priorityCol.setCellFactory(TextFieldTableCell.forTableColumn());
         priorityCol.setCellValueFactory(new PropertyValueFactory<Note, Note.Priority>("priority"));

         noteTable.getColumns().addAll(usernameCol, noteCol, priorityCol);
     }
}
