package library.assistant.ui.listmember;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.assistant.database.DatabaseHandler;
import library.assistant.ui.addbook.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberList implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    public TableView<Member> tableView;

    @FXML
    public TableColumn<Member, String> nameCol;

    @FXML
    public TableColumn<Member, String> IDCol;

    @FXML
    public TableColumn<Member, String> mobileCol;

    @FXML
    public TableColumn<Member, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void loadData() {
        DatabaseHandler handler  = new DatabaseHandler();
        String query = "SELECT * from MEMBER";
        ResultSet rs = handler.execQuery(query);
        try {
            while (rs.next()) {
                String namex = rs.getString("name");
                String idx = rs.getString("id");
                String mobilex = rs.getString("mobile");
                String emailx = rs.getString("email");
                list.add(new MemberList.Member(namex, idx, mobilex, emailx));
            }
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        tableView.getItems().setAll(list);
    }

    private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public static class Member {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final  SimpleStringProperty mobile;
        private final SimpleStringProperty email;

        Member(String name, String id, String mobile, String email) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public String getId() {
            return id.get();
        }
        public String getMobile() {
            return mobile.get();
        }
        public String getEmail() {
            return email.get();
        }
    }
}
