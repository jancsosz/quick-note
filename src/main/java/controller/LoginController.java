package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    public void login(ActionEvent actionEvent) throws IOException {
        usernameError.setText("");
        passwordError.setText("");

        if (password.getText().isEmpty() || username.getText().isEmpty()) {
            if (password.getText().isEmpty()) {
                passwordError.setText("Password is required.");
            }
            if (username.getText().isEmpty()) {
                usernameError.setText("Username is required.");
            }
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app-ui.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AppController>getController().initdata(username.getText(), password.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("QuickNotes");
            stage.show();
            log.info("Username is set to {}, loading game scene.", username.getText());
        }

        // TODO: user data query


    }
}
