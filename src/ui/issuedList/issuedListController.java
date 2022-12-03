package ui.issuedList;

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

import data.model.IssuedBook;

import database.dataHelper.showIssuedBookData;
import util.LibraryManagmentUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class issuedListController implements Initializable {

    public void handleBookList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/bookSearchList/bookSearchList.fxml"), "لیست کتاب", null);
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
        closeStage();
    }

    public void handleReturnBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/returnBook/returnBook.fxml"), "برگرداندن کتاب", null);
        closeStage();
    }

    public void handleMemberList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/memberList/memberList.fxml"), "لیست اعضا", null);
        closeStage();
    }

    protected ObservableList<IssuedBook> list = FXCollections.observableArrayList();

    @FXML
    public StackPane rootPane;
    @FXML
    public AnchorPane contentPane;
    @FXML
    public TableView<IssuedBook> tableView;
    @FXML
    public TableColumn<IssuedBook,String> departmentCol;
    @FXML
    public TableColumn<IssuedBook,String> levelCol;
    @FXML
    public TableColumn<IssuedBook,String> userNameCol;
    @FXML
    public TableColumn<IssuedBook,String> userIdCol;
    @FXML
    public TableColumn<IssuedBook,String> categoryCol;
    @FXML
    public TableColumn<IssuedBook,String> bookNameCol;
    @FXML
    public TableColumn<IssuedBook,String> bookIDCol;
    @FXML
    public TableColumn<IssuedBook,String> returnCol;
    @FXML
    public TableColumn<IssuedBook,String> issuedateCol;

    private void showData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/issuedBook.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                showIssuedBookData sibd = new showIssuedBookData(arr[0]);
                list.add(new IssuedBook(sibd.getBookId(),sibd.getBookName(),sibd.getBookCategory(),sibd.getIssueDate(),sibd.getUserId(),sibd.getUserName(),sibd.getUserLevel(),sibd.getUserDepartment(),sibd.getReturnDateIssuedList()));
            }
            tableView.setItems(list);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void initColumn() {
        userIdCol.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
        userNameCol.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        departmentCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        levelCol.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
        bookIDCol.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
        bookNameCol.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        issuedateCol.setCellValueFactory(cellData -> cellData.getValue().issuedateProperty());
        returnCol.setCellValueFactory(cellData -> cellData.getValue().returnDateProperty());
        tableView.setPlaceholder(new Label("                                                                                                               اطلاعاتی برای نمایش وجود ندارد"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        showData();
    }
    private void closeStage() {
        rootPane.getScene().getWindow().hide();
    }
}
