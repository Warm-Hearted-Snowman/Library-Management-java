package data.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Book {
    private final SimpleStringProperty id;
    private final SimpleStringProperty bookName;
    private final SimpleStringProperty category;
    private final SimpleStringProperty availability;

    public Book(String id, String bookName, String category, String availability) {
        this.id = new SimpleStringProperty(id);
        this.category = new SimpleStringProperty(category);
        this.bookName = new SimpleStringProperty(bookName);
        this.availability = new SimpleStringProperty(availability);
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return bookName.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getAvailability() {
        return availability.get();
    }

    public ObservableValue<String> idProperty() {
        return id;
    }

    public ObservableValue<String> titleProperty() {
        return bookName;
    }

    public ObservableValue<String> categoryProperty() {
        return category;
    }

    public ObservableValue<String> availabilityProperty() {
        return availability;
    }
}
