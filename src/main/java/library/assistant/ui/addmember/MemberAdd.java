package library.assistant.ui.addmember;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.assistant.database.DatabaseHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberAdd implements Initializable {
    DatabaseHandler databaseHandler;

    @FXML
    public TextField name;

    @FXML
    public TextField id;

    @FXML
    public TextField mobile;

    @FXML
    public TextField email;

    @FXML
    public Button saveButton;

    @FXML
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = new DatabaseHandler();
    }

    @FXML
    private void cancel (ActionEvent event) {

    }

    @FXML
    private void addMember (ActionEvent event) {
        String memberName = name.getText();
        String memberID = id.getText();
        String memberMobile = mobile.getText();
        String memberEmail = email.getText();

        Boolean flag = memberEmail.isEmpty() || memberID.isEmpty() || memberMobile.isEmpty() || memberName.isEmpty();

        if (flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }
        String st = "INSERT INTO MEMBER VALUES (" +
                "'" + memberID + "'," +
                "'" + memberName + "'," +
                "'" + memberMobile + "'," +
                "'" + memberEmail + "'" +
                ")";
        System.out.println(st);
        if (databaseHandler.execAction(st)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("An error occured");
            alert.showAndWait();
        }
    }
}
