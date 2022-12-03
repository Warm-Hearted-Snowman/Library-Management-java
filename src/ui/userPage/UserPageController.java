package ui.userPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import data.model.Book;
import Logger.Logger;

import database.dataHelper.showCurrentUser;
import util.LibraryManagmentUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {

    public void handleBookSearch(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/bookSearchList/bookSearchList.fxml"), "جستجوی کتاب", null);
    }

    public void handleIssuedList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/userPageIssuedList/IssuedList.fxml"), "کتاب های امانتی", null);
    }

    public void handleExit(ActionEvent e) {
        Logger.adminExitLogger();
        System.exit(0);
    }

    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    public TableColumn<Book, String> showBookAvailability;
    @FXML
    public TableColumn<Book, String> showBookCategory;
    @FXML
    public TableColumn<Book, String> showBookName;
    @FXML
    public TableColumn<Book, String> showBookId;

    @FXML
    public Label showUserId;
    @FXML
    public Label showUserName;
    @FXML
    public TableView<Book> booksList;

    private void setDatas() {
        new showCurrentUser(showUserId, showUserName);
    }

    private void showData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/books.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Book(data[0], data[1], data[2], data[3]));
            }
            booksList.setItems(list);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initColumn() {
        showBookId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        showBookName.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        showBookCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        showBookAvailability.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
        booksList.setPlaceholder(new Label("                                           اطلاعاتی برای نمایش وجود ندارد"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDatas();
        initColumn();
        showData();
    }
}