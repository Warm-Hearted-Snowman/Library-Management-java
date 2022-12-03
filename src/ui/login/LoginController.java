package ui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ui.preferences.Preferences;

import database.dataHelper;
import util.LibraryManagmentUtil;
import alertMaker.alertMaker;
import Logger.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField myUserId;

    @FXML
    private PasswordField myPassword;
    Preferences preferences = new Preferences();
    @FXML
    private void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
        String uId = myUserId.getText();
        String pWord = myPassword.getText();
        if (uId.equals(preferences.getUsername()) && pWord.equals(preferences.getPassword())) {
            closeStage();
            loadAdminPage();
            Logger.loginPageAdminLogger();
        } else {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/dataBase/members.csv"));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(",");
                if (uId.equals(datas[0]) && pWord.equals(datas[4])) {
                    dataHelper.currentUser(datas[0], datas[1]);
                    closeStage();
                    loadUserPage();
                    Logger.loginPageUserLogger(datas[0], datas[1]);
                    br.close();
                    return;
                }
            }
            alertMaker.showErrorMessage("خطا", "نام کاربری یا پسورد وارد شده درست نمی باشد");
            br.close();
        }
    }

    private void closeStage() {
        ((Stage) myUserId.getScene().getWindow()).close();
    }

    private void loadAdminPage() throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/admin/admin.fxml"), "صفحه اصلی", null);
    }

    private void loadUserPage() throws IOException {
        LibraryManagmentUtil.loadWindow(getClass().getResource("/ui/userPage/userPage.fxml"), "صفحه کاربر", null);
    }
    public void exitLogin(ActionEvent event) {
        Logger.programExitLogger();
        System.exit(0);
    }
}