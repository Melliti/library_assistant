package library.assistant.ui.addbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddBook implements Initializable {
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

    @FXML
    private AnchorPane rootPane;

    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = DatabaseHandler.getInstance();

        checkData();
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
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void checkData() {
        String query = "SELECT * FROM BOOK";
        ResultSet rs = databaseHandler.execQuery(query);
            try {
                while (rs.next()) {
                    String titlex = rs.getString("title");
                    System.out.println(titlex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
