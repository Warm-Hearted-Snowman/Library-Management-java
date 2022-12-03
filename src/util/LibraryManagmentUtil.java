package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class LibraryManagmentUtil {
    public static Object loadWindow(URL loc, String title, Stage parentStage) throws IOException {
        Object controller = null;
        FXMLLoader loader = new FXMLLoader(loc);
        Parent parent = loader.load();
        controller = loader.getController();
        Stage stage = null;
        if (parentStage != null) {
            stage = parentStage;
        } else {
            stage = new Stage(StageStyle.DECORATED);
        }
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.getIcons().add(new Image("/resources/icons/library-management.png"));
        stage.show();
        return controller;
    }
}
