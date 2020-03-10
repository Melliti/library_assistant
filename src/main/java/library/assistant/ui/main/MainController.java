package library.assistant.ui.main;

import com.jfoenix.effects.JFXDepthManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.database.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    @FXML
    public HBox book_info;
    @FXML
    public HBox member_info;
    @FXML
    public TextField bookIDInput;
    @FXML
    public Text book_name;
    @FXML
    public Text book_author;
    @FXML
    public Text book_status;
    @FXML
    public TextField memberIDInput;
    @FXML
    public Text member_name;
    @FXML
    public Text member_contact;
    @FXML
    public Button loadIssueOperation;

    DatabaseHandler handler;

    //    /library/assistant/ui
    @FXML
    public void loadAddMember(ActionEvent actionEvent) {
        loadWindow("/addmember/member_add.fxml", "Add new member");
    }

    @FXML
    public void loadAddBook(ActionEvent actionEvent) {
        loadWindow("/addbook/addbook.fxml", "Add new book");
    }

    @FXML
    public void loadMemberTable(ActionEvent actionEvent) {
        loadWindow("/listmember/member_list.fxml", "List members");
    }

    @FXML
    public void loadBookTable(ActionEvent actionEvent) {
        loadWindow("/listbook/book_list.fxml", "List books");
    }

    void loadWindow(String loc, String title) {
        try {
            System.out.println(getClass().getResource("/library/assistant/ui/addmember/"));
            URL res = getClass().getResource(loc);
            Parent parent = FXMLLoader.load(res);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void loadBookInfo (ActionEvent event) {
        clearBookCache();
        String id = bookIDInput.getText();
        System.out.println(id);
        String qu = "SELECT * FROM BOOK WHERE id = '" + id + "'";
        System.out.println(qu);
        ResultSet rs = handler.execQuery(qu);
        System.out.println(rs);
        Boolean flag = false;

        try {
            if (rs.next()) {
                String bName = rs.getString("title");
                String bAuthor = rs.getString("author");
                Boolean bStatus = rs.getBoolean("isAvail");
                book_name.setText(bName);
                book_author.setText(bAuthor);
                String status = (bStatus) ? "Available" : "Not available";
                book_status.setText(status);
                flag = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        }
        if (!flag) {
            book_name.setText("No such book available");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.setDepth(book_info, 1);
        JFXDepthManager.setDepth(member_info, 1);
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    public void loadMemberInfo(ActionEvent actionEvent) {
        clearMemberCache();
        String id = memberIDInput.getText();
        String qu = "SELECT * FROM MEMBER WHERE id = '" + id + "'";
        ResultSet rs = handler.execQuery(qu);
        Boolean flag = false;

        try {
            if (rs.next()) {
                String mName = rs.getString("name");
                String mContact = rs.getString("mobile");
                member_name.setText(mName);
                member_contact.setText(mContact);

                flag = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        }
        if (!flag) {
            member_name.setText("No such member available");
        }
    }

    @FXML
    private void loadIssueOperation(ActionEvent event) {
        String memberID = memberIDInput.getText();
        String bookID = bookIDInput.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Issue confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to issue the book " + book_name.getText() + " to \n"
        + member_name.getText() + " ?");

        Optional<ButtonType> response = alert.showAndWait();
        if (ButtonType.OK == response.get()) {
            String insertQu = "INSERT INTO ISSUE(memberID, bookID) VALUES ("
                    + "'" + memberID +  "',"
                    + "'" + bookID +  "')";
            String updateQu = "UPDATE BOOK SET isAvail = false WHERE id = '" + bookID + "'";

            if (handler.execAction(insertQu) && handler.execAction(updateQu)) {
                Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
                alertConfirm.setTitle("Success");
                alertConfirm.setHeaderText(null);
                alertConfirm.setContentText("Book issue complete");
                alertConfirm.showAndWait();
            } else {
                Alert alertConfirm = new Alert(Alert.AlertType.ERROR);
                alertConfirm.setTitle("Failed");
                alertConfirm.setHeaderText(null);
                alertConfirm.setContentText("Book issue failed");
                alertConfirm.showAndWait();
            }
        } else {
            Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
            alertConfirm.setTitle("Cancelled");
            alertConfirm.setHeaderText(null);
            alertConfirm.setContentText("Book issue cancelled");
            alertConfirm.showAndWait();
        }
    }

    void clearBookCache() {
        book_name.setText("");
        book_author.setText("");
        book_status.setText("");
    }

    void clearMemberCache() {
        member_contact.setText("");
        member_name.setText("");
    }
}
 