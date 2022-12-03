package data.model;

import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import util.LibraryManagmentUtil;

import java.io.IOException;

public class MenuBarController {
    public void handleIssuedList(ActionEvent e,StackPane rootPane) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/issuedList/issuedList.fxml"), "لیست امانتی ها", null);
        closeStage(rootPane);
    }

    public void handleAddBook(ActionEvent e,StackPane rootPane) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addBook/addBook.fxml"), "افزودن کتاب", null);
        closeStage(rootPane);
    }

    public void handleAddMember(ActionEvent e,StackPane rootPane) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addMember/addMember.fxml"), "افزودن دانشجو", null);
        closeStage(rootPane);
    }

    public void handleAdminPage(ActionEvent e,StackPane rootPane) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/admin/admin.fxml"), "Admin Page", null);
        closeStage(rootPane);
    }

    public void handleMemberList(ActionEvent e,StackPane rootPane) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/memberList/memberList.fxml"), "Member List", null);
        closeStage(rootPane);
    }
    private void closeStage(StackPane root) {
        root.getScene().getWindow().hide();
    }
}
