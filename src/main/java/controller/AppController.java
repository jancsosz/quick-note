package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import notes.model.NoteToView;
import priority.Priority;
import user.model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private User loggedInUser;

    @FXML
    private Label title;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private AnchorPane notes;

    @FXML
    private TableView<NoteToView> noteTable;

    @FXML
    private Pane usersNotes;

    @FXML
    private Pane ownNotes;

    @FXML
    private TextField noteField;

    @FXML
    private Button addNoteButton;

    @FXML
    private Label noteErrorLabel;

    private ObservableList<NoteToView> data =
            FXCollections.observableArrayList(
                    new NoteToView("jancsosz", "első note", getPriorityToDisplay(Priority.NORMAL), LocalDate.now().toString()),
                    new NoteToView("admin", "második note", getPriorityToDisplay(Priority.LOW), LocalDate.now().toString())
            );


    public void initdata(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private String getPriorityToDisplay(Priority priority) {
        switch(priority) {
            case LOW:
                return "Low priority";
            case NORMAL:
                return "Normal priority";
            case HIGH:
                return "High priority";
            case VERY_HIGH:
                return "Very high priority";
            case VERY_LOW:
                return "Very low priority";
            default:
                throw new IllegalArgumentException("Hibás priority");
        }
    }

    @Override
     public void initialize(URL url, ResourceBundle rb) {
         TableColumn usernameCol = new TableColumn("Username");
         usernameCol.setMinWidth(70);

         usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
         usernameCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("userFk"));

         TableColumn noteCol = new TableColumn("Note");
         // noteCol.setMinWidth(70);
         noteCol.setPrefWidth(150);

         noteCol.setCellFactory(TextFieldTableCell.forTableColumn());
         noteCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("comment"));

         TableColumn priorityCol = new TableColumn("Priority");
         priorityCol.setMinWidth(70);

         priorityCol.setCellFactory(TextFieldTableCell.forTableColumn());
         priorityCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("priority"));

        TableColumn createdCol = new TableColumn("Created");
        createdCol.setMinWidth(70);

        createdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        createdCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("created"));

         noteTable.getColumns().addAll(usernameCol, noteCol, priorityCol, createdCol);
         noteTable.setItems(data);
     }

     public void addNote() {
        if (!this.noteField.getText().equals("")){
            NoteToView newNote = new NoteToView("this.loggedInUser.getUsername()", this.noteField.getText(), getPriorityToDisplay(Priority.NORMAL), LocalDate.now().toString());
            this.data.add(newNote);

            this.noteErrorLabel.setText("");
        } else {
            this.noteErrorLabel.setText("Please add a note");
        }
        this.noteField.setText("");
    }
}
