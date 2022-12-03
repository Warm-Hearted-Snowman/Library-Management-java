package ui.addMember;

import data.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import alertMaker.alertMaker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import Logger.Logger;
import database.dataHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addMemberController implements Initializable {
    @FXML
    public TextField name;
    @FXML
    public TextField id;
    @FXML
    public Button saveButton;
    @FXML
    public Button backButton;
    @FXML
    public TextField password;
    @FXML
    public StackPane stackPane;
    @FXML
    private ChoiceBox<String> level;
    private final String[] myLevels = {"کارشناسی", "کارشناسی ارشد", "دکتری"};
    @FXML
    private ChoiceBox<String> department;
    private final String[] myDepartments = {"مهندسی کامپیوتر", "مهندسی برق", "علوم پایه"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        level.getItems().addAll(myLevels);
        department.getItems().addAll(myDepartments);
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        String mName = name.getText();
        String mID = id.getText();
        String mPassword = password.getText();
        String mLevel = level.getValue();
        String mDepartment = department.getValue();
        boolean flag = mName.isEmpty() || mID.isEmpty() || mPassword.isEmpty() || mLevel.isEmpty() || mDepartment.isEmpty();
        if (flag) {
            alertMaker.showErrorMessage("خطا", "لطفا تمام بخش ها را پر کنید");
            return;
        }
        if (dataHelper.isMemberExists(mID)) {
            alertMaker.showErrorMessage("خطا", "این کاربر قبلا ثبت شده است");
            return;
        }
        Member member = new Member(mID, mName, mLevel, mDepartment, mPassword);
        boolean result = dataHelper.insertNewMember(member);
        if (!result) {
            alertMaker.showMaterialDialog("کاربر جدید اضافه شد", "کاربر ( " + mName + " ) ذخیره شد");
            clearEntries();
            Logger.addmemberLogger(mID,mName);
        } else {
            alertMaker.showErrorMessage("خطا", "مقادیر ورودی را چک کرده و مجددا تلاش کنید");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        closeWindow();
    }

    private void closeWindow() {
        ((Stage) stackPane.getScene().getWindow()).close();
    }

    private void clearEntries() {
        name.clear();
        id.clear();
        password.clear();
        level.getSelectionModel().clearSelection();
        department.getSelectionModel().clearSelection();
    }
}
