package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import Logger.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ui/login/login.fxml")); // login page
        Logger.programStartedLogger();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/css/dark-theme.css")).toExternalForm());
        stage.setTitle("نرم افزار مدیریت کتابخانه");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/resources/icons/library-management.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}