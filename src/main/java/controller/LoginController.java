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
import user.UserDAO;
import user.model.User;

import java.io.IOException;

@Slf4j
public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    private User user;
    private UserDAO userManager;

    public void login(ActionEvent actionEvent) throws IOException {
        usernameError.setText("");
        passwordError.setText("");

        if (passwordTextField.getText().isEmpty() || usernameTextField.getText().isEmpty()) {
            if (passwordTextField.getText().isEmpty()) {
                passwordError.setText("Password is required.");
            }
            if (usernameTextField.getText().isEmpty()) {
                usernameError.setText("Username is required.");
            }
        } else {
            // TODO: user data query
            /*user = User.builder()
                    .username(usernameTextField.getText())
                    .password(passwordTextField.getText())
                    .build();

            userManager.find(user);*/

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app-ui.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AppController>getController().initdata(user);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("QuickNotes");
            stage.show();
            log.info("Username is set to {}, loading game scene.", usernameTextField.getText());
        }
    }
}
