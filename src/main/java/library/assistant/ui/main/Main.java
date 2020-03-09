package library.assistant.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements Initializable {
//    /library/assistant/ui
    @FXML
    public void loadAddMember(ActionEvent actionEvent) {
        loadWindow("/addmember/member_add.fxml", "Add new member");
    }

    @FXML
    public void loadAddBook(ActionEvent actionEvent) {
        loadWindow("/addbook/addbook.fxml", "Add new member");
    }

    @FXML
    public void loadMemberTable(ActionEvent actionEvent) {
        loadWindow("/listmember/member_list.fxml", "Add new member");
    }

    @FXML
    public void loadBookTable(ActionEvent actionEvent) {
        loadWindow("/listbook/book_list.fxml", "Add new member");
    }

    void loadWindow(String loc, String title) {
        try {
            System.out.println(getClass().getResource("/library/assistant/ui/addmember/"));
//            URL res = getClass().getResource("/addmember/member_add.fxml");
            URL res = getClass().getResource(loc);
            Parent parent = FXMLLoader.load(res);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
