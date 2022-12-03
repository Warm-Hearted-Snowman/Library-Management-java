package data.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class IssuedBook {
    private final SimpleStringProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty level;
    private final SimpleStringProperty department;
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty bookName;
    private final SimpleStringProperty category;
    private final SimpleStringProperty issueDate;
    private final SimpleStringProperty returnDate;

    public IssuedBook(String bookId, String bookName, String category, String issueDate, String userId, String userName, String level, String department, String returnDate) {
        this.userId = new SimpleStringProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.level = new SimpleStringProperty(level);
        this.department = new SimpleStringProperty(department);
        this.bookId = new SimpleStringProperty(bookId);
        this.category = new SimpleStringProperty(category);
        this.bookName = new SimpleStringProperty(bookName);
        this.issueDate = new SimpleStringProperty(issueDate);
        this.returnDate = new SimpleStringProperty(returnDate);
    }

    public String getBookId() {
        return bookId.get();
    }

    public String getBookName() {
        return bookName.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getIssueDate() {
        return issueDate.get();
    }

    public String getUserId() {
        return userId.get();
    }

    public String getUserName() {
        return userName.get();
    }

    public String getLevel() {
        return level.get();
    }

    public String getDepartment() {
        return department.get();
    }

    public ObservableValue<String> userIdProperty() {
        return userId;
    }

    public ObservableValue<String> userNameProperty() {
        return userName;
    }

    public ObservableValue<String> departmentProperty() {
        return department;
    }

    public ObservableValue<String> levelProperty() {
        return level;
    }

    public ObservableValue<String> bookIdProperty() {
        return bookId;
    }

    public ObservableValue<String> bookNameProperty() {
        return bookName;
    }

    public ObservableValue<String> categoryProperty() {
        return category;
    }

    public ObservableValue<String> issuedateProperty() {
        return issueDate;
    }

    public ObservableValue<String> returnDateProperty() {
        return returnDate;
    }
}
