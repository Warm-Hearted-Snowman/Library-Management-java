package ui.issueBook;

import alertMaker.alertMaker;
import database.dataHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import util.DateConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Logger.Logger;
import javafx.util.StringConverter;

public class issueBookController implements Initializable {

    @FXML
    public TextField bookId;
    @FXML
    public TextField memberId;
    @FXML
    public DatePicker issueDate;
    @FXML
    public Label showUserId;
    @FXML
    public Label showMemberName;
    @FXML
    public Label showUserLevel;
    @FXML
    public Label showUserDepartment;
    @FXML
    public Label showBookName;
    @FXML
    public Label showBookId;
    @FXML
    public Label showBookCategory;
    @FXML
    public Label showBookAvailability;
    @FXML
    public StackPane stackpane;

    @FXML
    private void handleShowData(ActionEvent event) {
        String mBookId = bookId.getText();
        String mMemberId = memberId.getText();
        boolean flag = mBookId.isEmpty() || mMemberId.isEmpty();
        if (flag) {
            alertMaker.showErrorMessage("خطا", "لطفا تمام بخش ها را پر کنید");
            return;
        }
        dataHelper.showUserData Udata = new dataHelper.showUserData(mMemberId);
        showUserId.setText(Udata.getUserId());
        showMemberName.setText(Udata.getUserName());
        showUserLevel.setText(Udata.getUserLevel());
        showUserDepartment.setText(Udata.getUserDepartment());
        dataHelper.showBookdata Bdata = new dataHelper.showBookdata(mBookId);
        showBookName.setText(Bdata.getBookName());
        showBookId.setText(Bdata.getBookId());
        showBookCategory.setText(Bdata.getBookCategory());
        showBookAvailability.setText(Bdata.getBookAvailability());
        if (!dataHelper.isBookExists(mBookId)) {
            alertMaker.showErrorMessage("خطا", "کتاب مورد نظر یافت نشد");
            return;
        }
        if (!dataHelper.isMemberExists(mMemberId)) {
            alertMaker.showErrorMessage("خطا", "کاربر مورد نظر یافت نشد");
            return;
        }
        if (dataHelper.isBookIssued(mBookId)) {
            alertMaker.showErrorMessage("خطا", "این کتاب قبلا اخذ شده است");
        }
    }

    @FXML
    private void handleIssueBook(ActionEvent event) {
        String mBookId = bookId.getText();
        String mMemberId = memberId.getText();
        String mBookName = showBookName.getText();
        String mMemberName = showMemberName.getText();
        String mMemeberLevel = showUserLevel.getText();
        LocalDate localDate = issueDate.getValue();

        boolean flag = mBookId.isEmpty() || mMemberId.isEmpty() || localDate == null;
        if (flag) {
            alertMaker.showErrorMessage("خطا", "لطفا تمام بخش ها را پر کنید");
            return;
        }
        try {
            if (mMemeberLevel.isEmpty()) {
                dataHelper.showUserData Udata = new dataHelper.showUserData(mMemberId);
                showUserId.setText(Udata.getUserId());
                showMemberName.setText(Udata.getUserName());
                showUserLevel.setText(Udata.getUserLevel());
                showUserDepartment.setText(Udata.getUserDepartment());
                dataHelper.showBookdata Bdata = new dataHelper.showBookdata(mBookId);
                showBookName.setText(Bdata.getBookName());
                showBookId.setText(Bdata.getBookId());
                showBookCategory.setText(Bdata.getBookCategory());
                showBookAvailability.setText(Bdata.getBookAvailability());
                if (!dataHelper.isMemberExists(mMemberId)) {
                    alertMaker.showErrorMessage("خطا", "کاربر مورد نظر یافت نشد");
                    return;
                }
                if (!dataHelper.isBookExists(mBookId)) {
                    alertMaker.showErrorMessage("خطا", "کتاب مورد نظر یافت نشد");
                    return;
                }
                return;
            }
        } catch (NullPointerException e) {
            Logger.issueBookErrorLogger(mBookId, mMemberId);
            alertMaker.showErrorMessage("خطا", "اطلاعات وارد شده صحیح نمی باشد");
            return;
        }
        if (dataHelper.isBookIssued(mBookId)) {
            alertMaker.showErrorMessage("خطا", "این کتاب قبلا اخذ شده است");
            return;
        }
        String date = localDate.toString();
        String returnDate = "";
        switch (mMemeberLevel) {
            case "کارشناسی":
                returnDate = "14";
                break;
            case "کارشناسی ارشد":
                returnDate = "21";
                break;
            case "دکتری":
                returnDate = "28";
                break;
        }
        boolean rs = dataHelper.issueBook(mBookId, mMemberId, date, returnDate);
        if (!rs) {
            alertMaker.showMaterialDialog("کتاب اخذ شد", "کتاب ( " + mBookName + " ) با موفقیت برای کاربر ( " + mMemberName + " )اخذ شد");
            Logger.issueBookLogger(mBookId, mBookName, mMemberId, mMemberName);
            clearFields();
        } else {
            alertMaker.showErrorMessage("خطا", "کتاب اخذ نشد");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        closeWindow();
    }

    private void clearFields() {
        bookId.clear();
        memberId.clear();
        showUserId.setText("");
        showMemberName.setText("");
        showUserLevel.setText("");
        showUserDepartment.setText("");
        showBookName.setText("");
        showBookId.setText("");
        showBookCategory.setText("");
        showBookAvailability.setText("");
    }

    private void closeWindow() {
        ((Stage) stackpane.getScene().getWindow()).close();
    }

    //convert gregorian datepicker to persian datepicker
    private void convertDatePicker() {
        DateTimeFormatter gregorianFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter persianFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        issueDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return DateConverter.GregorianToJalali(date.format(gregorianFormatter).split("-")[0], date.format(gregorianFormatter).split("-")[1], date.format(gregorianFormatter).split("-")[2]);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, persianFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    private void setTodayDate() {
        LocalDate today = LocalDate.now();
        issueDate.setValue(today);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        convertDatePicker();
        setTodayDate();
    }

}