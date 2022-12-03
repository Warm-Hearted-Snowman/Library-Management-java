package ui.memberList;

import data.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import util.LibraryManagmentUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class memberListController implements Initializable {

    public void handleBookList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/bookSearchList/bookSearchList.fxml"), "لیست کتاب ها", null);
        closeStage();
    }

    public void handleIssuedList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/issuedList/issuedList.fxml"), "لیست امانتی ها", null);
        closeStage();
    }

    public void handleAddBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addBook/addBook.fxml"), "افزودن کتاب", null);
        closeStage();
    }

    public void handleAddMember(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addMember/addMember.fxml"), "افزودن دانشجو", null);
        closeStage();
    }

    public void handleAdminPage(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/admin/admin.fxml"), "صفحه اصلی", null);
        closeStage();
    }

    public void handleReturnBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/returnBook/returnBook.fxml"), "بازگرداندن کتاب", null);
        closeStage();
    }


    ObservableList<Member> list = FXCollections.observableArrayList();
    @FXML
    public StackPane rootPane;
    @FXML
    public AnchorPane contentPane;
    @FXML
    public TableView<Member> tableView;
    @FXML
    public TableColumn<Member, String> departmentCol;
    @FXML
    public TableColumn<Member, String> levelCol;
    @FXML
    public TableColumn<Member, String> userNameCol;
    @FXML
    public TableColumn<Member, String> userIdCol;

    private void initColumn() {
        userIdCol.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
        userNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        levelCol.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
        departmentCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        tableView.setPlaceholder(new Label("                                                                                    اطلاعاتی برای نمایش وجود ندارد"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        showData();
    }

    private void showData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/members.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Member(data[0], data[1], data[2], data[3]));
            }
            tableView.setItems(list);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStage() {
        rootPane.getScene().getWindow().hide();
    }

}
