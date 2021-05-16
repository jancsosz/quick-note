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
import lombok.extern.slf4j.Slf4j;
import notes.NoteDAO;
import notes.model.Note;
import notes.model.NoteToView;
import priority.Priority;
import user.model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
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

    private NoteDAO noteManager = NoteDAO.getInstance();

    private ObservableList<NoteToView> noteToViewList = FXCollections.observableArrayList();


    public void initdata(User loggedInUser) {
        this.loggedInUser = loggedInUser;

//        noteManager.persist(new Note(null, "jancsosz", "első note", Priority.NORMAL, LocalDate.now()));
//        noteManager.persist(new Note(null, "admin", "második note", Priority.LOW, LocalDate.now()));

        List<Note> notes = noteManager.findAll();
        for (Note note: notes) {
            this.noteToViewList.add(createNewNoteFromNote(note));
        }
    }

    private NoteToView createNewNoteFromNote(Note note) {
        return new NoteToView(note.getId(), note.getUserFk(), note.getComment(), getPriorityToDisplay(note.getPriority()), note.getCreated().toString());
    }

    private Note createNoteFromNewNote(NoteToView noteToView) {
        return new Note(noteToView.getId(), this.loggedInUser.getUsername(), this.noteField.getText(), Priority.NORMAL, LocalDate.now());

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
         noteTable.setItems(noteToViewList);
     }

     public void addNote() {
        if (!this.noteField.getText().equals("")){
            NoteToView newNote = new NoteToView(null, this.loggedInUser.getUsername(), this.noteField.getText(), getPriorityToDisplay(Priority.NORMAL), LocalDate.now().toString());
            this.noteToViewList.add(newNote);

            Note newNoteToDb = createNoteFromNewNote(newNote);
            this.noteManager.persist(newNoteToDb);

            log.info("Saved new note: {}", newNoteToDb);

            this.noteErrorLabel.setText("");
        } else {
            this.noteErrorLabel.setText("Please add a note");
        }
        this.noteField.setText("");
    }
}
