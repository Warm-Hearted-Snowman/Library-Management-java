package data.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Member {
    private final SimpleStringProperty stdNumber;
    private final SimpleStringProperty name;
    private final SimpleStringProperty level;
    private final SimpleStringProperty department;
    private SimpleStringProperty password;

    public Member(String stdNumber, String name, String level, String department, String password) {
        this.stdNumber = new SimpleStringProperty(stdNumber);
        this.name = new SimpleStringProperty(name);
        this.level = new SimpleStringProperty(level);
        this.department = new SimpleStringProperty(department);
        this.password = new SimpleStringProperty(password);
    }
    public Member(String stdNumber, String name, String level, String department) {
        this.stdNumber = new SimpleStringProperty(stdNumber);
        this.name = new SimpleStringProperty(name);
        this.level = new SimpleStringProperty(level);
        this.department = new SimpleStringProperty(department);
    }

    public String getStdNumber() {
        return stdNumber.get();
    }

    public String getName() {
        return name.get();
    }

    public String getLevel() {
        return level.get();
    }

    public String getDepartment() {
        return department.get();
    }

    public String getPassword() {
        return password.get();
    }


    public ObservableValue<String> userIdProperty() {
        return stdNumber;
    }

    public ObservableValue<String> nameProperty() {
        return name;
    }

    public ObservableValue<String> levelProperty() {
        return level;
    }

    public ObservableValue<String> departmentProperty() {
        return department;
    }

}
