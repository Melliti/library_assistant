package library.ui.addbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField title;

    @FXML
    private TextField id;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();
    }

    @FXML
    public void addBook(ActionEvent actionEvent) {
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
    }
}
