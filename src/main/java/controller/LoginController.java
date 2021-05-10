package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    public void login() {
        usernameError.setText("");
        passwordError.setText("");

        if (password.getText().isEmpty() || username.getText().isEmpty()) {
            if (password.getText().isEmpty()) {
                passwordError.setText("Password is required.");
            }
            if (username.getText().isEmpty()) {
                usernameError.setText("Username is required.");
            }
        }

        // TODO: user data query


    }
}
