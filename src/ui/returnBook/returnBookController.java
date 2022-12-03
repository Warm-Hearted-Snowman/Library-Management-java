package ui.returnBook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import alertMaker.alertMaker;
import database.dataHelper;
import database.dataHelper.showIssuedBookData;
import Logger.Logger;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class returnBookController {
    @FXML
    public Label userName;
    @FXML
    public Label userId;
    @FXML
    public Label userLevel;
    @FXML
    public Label userDepartment;
    @FXML
    public Label issueDate;
    @FXML
    public Label bookName;
    @FXML
    public Label bookId;
    @FXML
    public Label bookAvailability;
    @FXML
    public Label bookCategory;
    @FXML
    public Label returnDate;
    @FXML
    public TextField bookIdChecking;
    @FXML
    public StackPane stackpane;


    @FXML
    private void handleCheckInfo(ActionEvent actionEvent) {
        clearFields();
        String mBookId = bookIdChecking.getText();
        if (mBookId.isEmpty()) {
            alertMaker.showErrorMessage("خطا", "لطفا شماره کتاب را وارد کنید");
            return;
        }
        if (!dataHelper.isBookExists(mBookId)) {
            alertMaker.showErrorMessage("خطا", "شماره کتاب وارد شده موجود نیست");
            return;
        }
        if (!dataHelper.isBookIssued(mBookId)) {
            alertMaker.showErrorMessage("خطا", "کتاب در حال حاضر در امانت نیست");
            return;
        }
        showIssuedBookData bookData = new showIssuedBookData(mBookId);
        userName.setText(bookData.getUserName());
        userId.setText(bookData.getUserId());
        userLevel.setText(bookData.getUserLevel());
        userDepartment.setText(bookData.getUserDepartment());
        issueDate.setText(bookData.getIssueDate());
        bookName.setText(bookData.getBookName());
        bookId.setText(bookData.getBookId());
        bookAvailability.setText(bookData.getBookAvailability());
        bookCategory.setText(bookData.getBookCategory());
        returnDate.setText(bookData.getReturnDate());
    }

    public void handleReturnBook(ActionEvent actionEvent) {
        String mBookId = bookIdChecking.getText();
        if (mBookId.isEmpty()) {
            alertMaker.showErrorMessage("خطا", "لطفا شماره کتاب را وارد کنید");
            return;
        }
        if (!dataHelper.isBookExists(mBookId)) {
            alertMaker.showErrorMessage("خطا", "شماره کتاب وارد شده موجود نیست");
            return;
        }
        if (!dataHelper.isBookIssued(mBookId)) {
            alertMaker.showErrorMessage("خطا", "کتاب در حال حاضر در امانت نیست");
            return;
        }
        if (userName.getText().isEmpty()) {
            showIssuedBookData bookData = new showIssuedBookData(mBookId);
            userName.setText(bookData.getUserName());
            userId.setText(bookData.getUserId());
            userLevel.setText(bookData.getUserLevel());
            userDepartment.setText(bookData.getUserDepartment());
            issueDate.setText(bookData.getIssueDate());
            bookName.setText(bookData.getBookName());
            bookId.setText(bookData.getBookId());
            bookAvailability.setText(bookData.getBookAvailability());
            bookCategory.setText(bookData.getBookCategory());
            returnDate.setText(bookData.getReturnDate());
            return;
        }
        if (!dataHelper.returnBook(mBookId)) {
            alertMaker.showSuccessMessage("انجام شد", "کتاب ( "+bookName.getText()+" ) تحویل گرفته شد");
            Logger.returnBookLogger(mBookId, bookName.getText(),userId.getText(),userName.getText());
            clearFields();
        } else {
            alertMaker.showErrorMessage("خطا", "برگشت کتاب با شکست مواجه شد");
        }
    }
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        closeWindow();
    }
    private void closeWindow() {
        ((Stage) stackpane.getScene().getWindow()).close();
    }

    private void clearFields() {
        userName.setText("");
        userId.setText("");
        userLevel.setText("");
        userDepartment.setText("");
        issueDate.setText("");
        bookName.setText("");
        bookId.setText("");
        bookAvailability.setText("");
        bookCategory.setText("");
        returnDate.setText("");
    }

}
