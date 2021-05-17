package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Class for application launch initialization.
 */
public class MyApp extends Application {

    @Override
    public void start(Stage loginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        loginStage.setTitle("Login");
        loginStage.getIcons().add(new Image(MyApp.class.getResourceAsStream("/images/icon.png")));
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(root, 300, 350));
        loginStage.show();
    }

}
