package data.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class MenuIssuedBook {
    private final SimpleStringProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty bookName;
    private final SimpleStringProperty issueDate;

    public MenuIssuedBook(String bookId, String bookName, String issueDate, String userId, String userName) {
        this.userId = new SimpleStringProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.bookId = new SimpleStringProperty(bookId);
        this.bookName = new SimpleStringProperty(bookName);
        this.issueDate = new SimpleStringProperty(issueDate);

    }
    public ObservableValue<String> userIdProperty() {
        return userId;
    }
    public ObservableValue<String> userNameProperty() {
        return userName;
    }
    public ObservableValue<String> bookIdProperty() {
        return bookId;
    }
    public ObservableValue<String> bookNameProperty() {
        return bookName;
    }
    public ObservableValue<String> issueDateProperty() {
        return issueDate;
    }

}
