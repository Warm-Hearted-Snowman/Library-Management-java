package ui.admin;

import Logger.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.LibraryManagmentUtil;
import util.DateConverter;

import data.model.MenuIssuedBook;
import database.dataHelper.showIssuedBookData;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    public void handleBookList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/bookSearchList/bookSearchList.fxml"), "لیست کتاب ها", null);
    }

    public void handleIssuedList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/issuedList/issuedList.fxml"), "لیست امانتی ها", null);
    }
//    public void handleLoginPage(ActionEvent e) throws IOException {
//    }

    public void handleAddBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addBook/addBook.fxml"), "افزودن کتاب", null);
    }

    public void handleAddMember(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/addMember/addMember.fxml"), "افزودن دانشجو", null);
    }

    public void handleIssueBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/issueBook/issueBook.fxml"), "امانت کتاب", null);
    }

    public void handleReturnBook(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/returnBook/returnBook.fxml"), "برگرداندن کتاب", null);
    }

    public void handleMemberList(ActionEvent e) throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/memberList/memberList.fxml"), "لیست اعضا", null);
    }

    ObservableList<MenuIssuedBook> list = FXCollections.observableArrayList();
    @FXML
    public StackPane stackPane;
    @FXML
    public TableView<MenuIssuedBook> tableView;
    @FXML
    public TableColumn<MenuIssuedBook, String> issueDate;
    @FXML
    public TableColumn<MenuIssuedBook, String> userName;
    @FXML
    public TableColumn<MenuIssuedBook, String> userId;
    @FXML
    public TableColumn<MenuIssuedBook, String> bookName;
    @FXML
    public TableColumn<MenuIssuedBook, String> bookId;
    @FXML
    public Label date;
    @FXML
    public Label dayOfDate;

    public void handleExit(ActionEvent e) throws IOException {
        Logger.adminExitLogger();
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/login/login.fxml"), "صفحه ورود", null);
        ((Stage) stackPane.getScene().getWindow()).close();
    }
    private void initColumn() {
        issueDate.setCellValueFactory(cellData -> cellData.getValue().issueDateProperty());
        userName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        userId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
        bookName.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());
        bookId.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
        tableView.setPlaceholder(new Label("                                           اطلاعاتی برای نمایش وجود ندارد"));
    }

    private void showData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/issuedBook.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                showIssuedBookData sibd = new showIssuedBookData(arr[0]);
                list.add(new MenuIssuedBook(sibd.getBookId(), sibd.getBookName(), sibd.getIssueDate(), sibd.getUserId(), sibd.getUserName()));
            }
            tableView.setItems(list);
            tableView.getSelectionModel().select(null);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearTable() {
        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); //1 minute = 60000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    tableView.getItems().clear();
                    showData();
                });
            }
        });
        timerThread.start();
    }

    public void showTime() {
        DateTimeFormatter dtf_Date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf_Hour = DateTimeFormatter.ofPattern("HH:mm");
//        LocalDateTime now = LocalDateTime.now();
        String Date=DateConverter.GregorianToJalali(dtf_Date.format(LocalDateTime.now()).split("/")[0],dtf_Date.format(LocalDateTime.now()).split("/")[1],dtf_Date.format(LocalDateTime.now()).split("/")[2]);
        String Hour=dtf_Hour.format(LocalDateTime.now());
        date.setText(Date+" "+Hour);
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat_time = new SimpleDateFormat("HH:mm");
            while (true) {
                try {
                    Thread.sleep(5000); //1 minute = 60000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String Date1=DateConverter.GregorianToJalali(simpleDateFormat_date.format(new Date()).split("/")[0], simpleDateFormat_date.format(new Date()).split("/")[1], simpleDateFormat_date.format(new Date()).split("/")[2]);
                final String time =Date1+" "+simpleDateFormat_time.format(new Date());
                Platform.runLater(() -> {
                    date.setText(time);
                });
            }
        });
        timerThread.start();
    }
    private void showDay() {
        String mDay = "";
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                mDay = "دوشنبه";
                break;
            case TUESDAY:
                mDay = "سه شنبه";
                break;
            case WEDNESDAY:
                mDay = "چهارشنبه";
                break;
            case THURSDAY:
                mDay = "پنج شنبه";
                break;
            case FRIDAY:
                mDay = "جمعه";
                break;
            case SATURDAY:
                mDay = "شنبه";
                break;
            case SUNDAY:
                mDay = "یکشنبه";
                break;
        }
        dayOfDate.setText(mDay);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        showData();
        showTime();
        showDay();
        clearTable();
    }
}
