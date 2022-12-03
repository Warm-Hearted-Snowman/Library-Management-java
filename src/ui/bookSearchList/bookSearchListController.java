package ui.bookSearchList;

import data.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class bookSearchListController implements Initializable {
    @FXML
    public TextField bookIdChecking;
    @FXML
    public ChoiceBox<String> bookAvailability;
    @FXML
    public ChoiceBox<String> bookCategory;

    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    public StackPane rootPane;
    @FXML
    public AnchorPane contentPane;
    @FXML
    public TableView<Book> tableView;
    @FXML
    public TableColumn<Book, String> availabilityCol;
    @FXML
    public TableColumn<Book, String> categoryCol;
    @FXML
    public TableColumn<Book, String> titleCol;
    @FXML
    public TableColumn<Book, String> idCol;

    private final String[] myCategories = {"کامپیوتر", "ریاضی", "فیزیک", "شیمی", "برق", "عمومی", "همه"};
    private final String[] myAvailability = {"موجود", "ناموجود", "همه"};


    private void initColumn() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        availabilityCol.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
        tableView.setPlaceholder(new Label("                                                                                    اطلاعاتی برای نمایش وجود ندارد"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        showPreData();
        bookCategory.getItems().addAll(myCategories);
        bookCategory.getSelectionModel().select(6);
        bookAvailability.getItems().addAll(myAvailability);
        bookAvailability.getSelectionModel().select(2);
    }

    private void showPreData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/books.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Book(data[0], data[1], data[2], data[3]));
            }
            tableView.setItems(list);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumeric(String string) {
        if (string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    private void clearData() {
        list.clear();
        tableView.setItems(list);
    }

    public void showData() {
        bookIdChecking.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().equals("")) {
                clearData();
                showPreData();
            }
            if (newValue.length() > 0) {
                if (isNumeric(newValue)) {
                    tableView.setItems(list.filtered(book -> book.getId().contains(newValue)));
                } else {
                    tableView.setItems(list.filtered(book -> book.getName().contains(newValue)));
                }
            }
        });
    }

    public void showAvailabilitySelectedData(MouseEvent mouseEvent) {
        bookAvailability.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (bookCategory.getValue().equals(myCategories[6])) {
                if ((int) newValue == 2) {
                    tableView.setItems(list.filtered(book -> book.getName().contains(bookIdChecking.getText())));
                } else {
                    tableView.setItems(list.filtered(book -> book.getAvailability().equals(myAvailability[(int) newValue]) && book.getName().contains(bookIdChecking.getText())));
                }
            } else {
                if ((int) newValue == 2) {
                    tableView.setItems(list.filtered(book -> book.getCategory().equals(bookCategory.getValue()) && book.getName().contains(bookIdChecking.getText())));
                } else {
                    tableView.setItems(list.filtered(book -> book.getCategory().equals(bookCategory.getValue()) && book.getAvailability().equals(myAvailability[(int) newValue]) && book.getName().contains(bookIdChecking.getText())));
                }
            }
        });
    }

    public void showCategorySelectedData(MouseEvent mouseEvent) {
        bookCategory.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (bookAvailability.getValue().equals(myAvailability[2])) {
                if ((int) newValue == 6) {
                    tableView.setItems(list.filtered(book -> book.getName().contains(bookIdChecking.getText())));
                } else {
                    tableView.setItems(list.filtered(book -> book.getCategory().equals(myCategories[(int) newValue]) && book.getName().contains(bookIdChecking.getText())));
                }
            } else {
                if ((int) newValue == 6) {
                    tableView.setItems(list.filtered(book -> book.getAvailability().equals(bookAvailability.getValue()) && book.getName().contains(bookIdChecking.getText())));
                } else {
                    tableView.setItems(list.filtered(book -> book.getCategory().equals(myCategories[(int) newValue]) && book.getAvailability().equals(bookAvailability.getValue()) && book.getName().contains(bookIdChecking.getText())));
                }
            }
        });
    }
}

