package library.ui.addbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        String bookID = id.getText();
        String bookAuthor = author.getText();
        String bookName = title.getText();
        String bookPublisher = publisher.getText();

        if (bookID.isEmpty() || bookAuthor.isEmpty() ||bookName.isEmpty() || bookPublisher.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO BOOK VALUES (" +
                "'" + bookID + "', " +
                "'" + bookName + "', " +
                "'" + bookAuthor + "', " +
                "'" + bookPublisher + "', " +
                "true" +
                ")";
        System.out.println(query);

        if (databaseHandler.execAction(query)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
    }
}
