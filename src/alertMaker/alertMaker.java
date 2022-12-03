package alertMaker;

import javafx.scene.control.Alert;

public class alertMaker {
    public static void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showMaterialDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    public static void showSuccessMessage(String success, String book_returned_successfully) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(success);
        alert.setHeaderText(null);
        alert.setContentText(book_returned_successfully);
        alert.showAndWait();
    }
}
