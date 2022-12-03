package ui.addBook;

import data.model.Book;
import database.dataHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import alertMaker.alertMaker;
import Logger.Logger;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addBookController implements Initializable {
    @FXML
    public TextField bookId;
    @FXML
    public TextField bookName;
    @FXML
    public ChoiceBox<String> bookCategory;
    private final String[] myCategories = {"کامپیوتر", "ریاضی", "فیزیک", "شیمی", "برق", "عمومی"};
    @FXML
    public StackPane stackpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookCategory.getItems().addAll(myCategories);
    }

    @FXML
    private void handleAddBook(ActionEvent event) {
        String mBookId = bookId.getText();
        String mBookName = bookName.getText();
        String mBookCategory = bookCategory.getValue();
        boolean flag = mBookId.isEmpty() || mBookName.isEmpty() || mBookCategory.isEmpty();
        if (flag) {
            alertMaker.showErrorMessage("خطا", "لطفا تمام بحش ها را پر کنید");
            return;
        }
        if (dataHelper.isBookExists(mBookId)) {
            alertMaker.showErrorMessage("خطا", "این کتاب قبلا ثبت شده است");
            return;
        }
        Book book = new Book(mBookId, mBookName, mBookCategory, "موجود");
        boolean result = dataHelper.insertNewBook(book);
        if (!result) {
            alertMaker.showMaterialDialog("کتاب جدید اضافه شد", "کتاب ( " + mBookName + " ) ذخیره شد");
            clearEntries();
            Logger.addBookLogger(mBookId, mBookName, mBookCategory);
        } else {
            alertMaker.showErrorMessage("خطا", "مقادیر ورودی را چک کرده و مجددا تلاش کنید");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        ((Stage) stackpane.getScene().getWindow()).close();
    }

    private void clearEntries() {
        bookId.clear();
        bookName.clear();
        bookCategory.getSelectionModel().clearSelection();
    }
}
