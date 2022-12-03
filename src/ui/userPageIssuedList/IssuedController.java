package ui.userPageIssuedList;

import javafx.fxml.Initializable;

import ui.issuedList.issuedListController;
import database.dataHelper.showCurrentUser;

import java.net.URL;
import java.util.ResourceBundle;

public class IssuedController extends issuedListController implements Initializable {
    private String userId;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        setUserId();
        tableView.setItems(list.filtered(IssuedBook -> IssuedBook.getUserId().equals(userId)));
    }
    private void setUserId() {
        showCurrentUser showCurrentUser = new showCurrentUser();
        this.userId=showCurrentUser.getUserId();
    }
}
