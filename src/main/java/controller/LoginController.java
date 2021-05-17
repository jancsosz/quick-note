package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import user.UserService;

import java.io.IOException;

/**
 * Controller class for user authentication stage.
 */
@Slf4j
public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    @FXML
    private Label loginErrorLabel;

    /**
     * Method, controlling login process.
     * @param actionEvent ActionEvent object to get the window for AppController.
     * @throws IOException
     */
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
            UserService userService = new UserService();
            if (userService.auth(usernameTextField.getText(), passwordTextField.getText())) {
                log.info("Found user {}, loading app scene.", usernameTextField.getText());

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app-ui.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<AppController>getController().initdata(userService.getUser());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("QuickNotes");
                stage.show();
            } else {
                this.loginErrorLabel.setText("No user with this username password combination");
            }


        }
    }
}
