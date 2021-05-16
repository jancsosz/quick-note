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
import user.UserDAO;
import user.model.User;

import java.io.IOException;
import java.util.List;

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

    private UserDAO userManager = UserDAO.getInstance();

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
            User user = User.builder()
                    .username(usernameTextField.getText())
                    .password(passwordTextField.getText())
                    .build();

            log.info("Built user: {}", user);
            List<User> registeredUsers;
            registeredUsers = userManager.findAll();
            System.out.println(registeredUsers.get(0));

            boolean found = false;
            for (User u: registeredUsers) {
                if (u.getUsername().equals(usernameTextField.getText()) && u.getPassword().equals(passwordTextField.getText())) {
                    found = true;
                }
            }

            if (found) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app-ui.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<AppController>getController().initdata(user);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("QuickNotes");
                stage.show();
                log.info("Found user {}, loading app scene.", usernameTextField.getText());
            } else {
                this.loginErrorLabel.setText("No user with this username password combination");
            }


        }
    }
}
