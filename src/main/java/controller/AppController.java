package controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import notes.NoteDAO;
import notes.NoteService;
import notes.model.Note;
import notes.model.NoteToView;
import priority.Priority;
import user.model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The main controller class for application.
 */
@Slf4j
public class AppController implements Initializable {

    @FXML
    private TableView<NoteToView> allNotesTable;

    @FXML
    private TableView<NoteToView> myNotesTable;

    @FXML
    private Pane allNotesPane;

    @FXML
    private Pane myNotesPane;

    @FXML
    private TextField noteField;

    @FXML
    private Label titleLabel;

    @FXML
    private Label noteErrorLabel;

    @FXML
    private Label priorityErrorLabel;

    @FXML
    private ComboBox<String> selectPriority = new ComboBox<>();


    private NoteService noteService;

    private User loggedInUser;

    private NoteDAO noteManager;

    private ObservableList<NoteToView> allNotesToViewList = FXCollections.observableArrayList();

    private ObservableList<NoteToView> myNotesToViewList = FXCollections.observableArrayList();

    /**
     * Called from LoginController, inits data based on loggedInUser
     * @param loggedInUser User object, which successfully passed the authentication.
     */
    public void initdata(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        noteManager = NoteDAO.getInstance();
        this.noteService = new NoteService();

        List<Note> notes = noteManager.findAll();
        for (Note note: notes) {
            this.allNotesToViewList.add(noteService.createNoteToViewFromNote(note));

            if (note.getUserFk().equals(this.loggedInUser.getUsername())) {
                this.myNotesToViewList.add(noteService.createNoteToViewFromNote(note));
            }
        }

        this.selectPriority.getItems().addAll(
                noteService.getPriorityToDisplay(Priority.VERY_LOW),
                noteService.getPriorityToDisplay(Priority.LOW),
                noteService.getPriorityToDisplay(Priority.NORMAL),
                noteService.getPriorityToDisplay(Priority.HIGH),
                noteService.getPriorityToDisplay(Priority.VERY_HIGH)
        );
    }

    /**
     * Initializes the table with data from database.
     * @param tableToInit The FXML object of the table to init
     * @param initData Data to init the table with
     * @param myNotes A boolean value which indicates which table is being initialized.
     */
    private void initTable(TableView<NoteToView> tableToInit, ObservableList<NoteToView> initData, boolean myNotes) {
        TableColumn usernameCol = new TableColumn("Written by");
        usernameCol.setMinWidth(70);

        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("userFk"));



        TableColumn noteCol = new TableColumn("Note");

        noteCol.setPrefWidth(150);

        noteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        noteCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("comment"));

        if (myNotes) {
            noteCol.setOnEditCommit(
                    (EventHandler<TableColumn.CellEditEvent<NoteToView, String>>) cellEditEvent -> {
                        NoteToView changedNote = ((NoteToView) cellEditEvent.getTableView().getItems().get(
                                cellEditEvent.getTablePosition().getRow()
                        ));

                        if (!changedNote.getComment().equals(cellEditEvent.getNewValue())) {
                            changedNote.setComment(cellEditEvent.getNewValue());

                            this.noteManager = NoteDAO.getInstance();

                            List<Note> notes = this.noteManager.findAll();

                            for (Note note: notes) {
                                if (note.getId().equals(changedNote.getId())){
                                    note.setComment(changedNote.getComment());

                                    this.noteManager.update(note);
                                    log.info("Updated note's description: {}", note);
                                    break;
                                }
                            }
                        }
                    }
            );
        }


        TableColumn priorityCol = new TableColumn("Priority");
        priorityCol.setMinWidth(70);

        priorityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priorityCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("priority"));


        TableColumn createdCol = new TableColumn("At");
        createdCol.setMinWidth(70);

        createdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        createdCol.setCellValueFactory(new PropertyValueFactory<NoteToView, String>("created"));

        if (myNotes) {
            usernameCol.setEditable(false);
            createdCol.setEditable(false);
            priorityCol.setEditable(false);
        }

        tableToInit.getColumns().addAll(usernameCol, noteCol, priorityCol, createdCol);
        tableToInit.setItems(initData);
    }

    @Override
     public void initialize(URL url, ResourceBundle rb) {
         initTable(this.allNotesTable, this.allNotesToViewList, false);
         initTable(this.myNotesTable, this.myNotesToViewList, true);

         log.info("Initialized tables");
     }

    /**
     * Creates suitable Note object from user input (NoteToView) and
     * adds it to the table and persists to DB.
     */
    public void addNote() {
        if (!this.noteField.getText().equals("") && this.selectPriority.getValue() != null){
            NoteToView newNote = new NoteToView(null, this.loggedInUser.getUsername(), this.noteField.getText(), this.selectPriority.getValue(), LocalDate.now().toString());
            this.allNotesToViewList.add(newNote);

            this.noteService = new NoteService();
            this.noteManager = NoteDAO.getInstance();

            Note newNoteToDb = noteService.createNoteFromNoteToView(newNote);
            this.noteManager.persist(newNoteToDb);

            this.noteErrorLabel.setText("");
            this.priorityErrorLabel.setText("");

            this.noteField.clear();

            log.info("Saved new note: {}", newNoteToDb);
        } else {
            if (this.noteField.getText().equals("")) {
                this.noteErrorLabel.setText("Please add a note");
            } else {
                this.noteErrorLabel.setText("");
            }
            if (this.selectPriority.getValue() == null){
                this.priorityErrorLabel.setText("Please select your note's priority");
            } else {
                this.priorityErrorLabel.setText("");
            }
        }
    }

    public void myNotesAction() {
        this.allNotesPane.setVisible(false);
        this.myNotesPane.setVisible(true);
        this.titleLabel.setText("My notes");

        log.info("Switched to My Notes page");
    }

    public void allNotesAction() {
        this.allNotesPane.setVisible(true);
        this.myNotesPane.setVisible(false);
        this.titleLabel.setText("All notes");

        log.info("Switched to All Notes page");
    }

    public void onExit() {
        log.info("Quitting.");
        Platform.exit();
    }
}
