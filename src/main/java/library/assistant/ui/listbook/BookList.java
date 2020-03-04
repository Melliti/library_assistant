package library.assistant.ui.listbook;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import library.assistant.database.DatabaseHandler;
import library.assistant.ui.addbook.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookList implements Initializable {
    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Book> tableView;

    @FXML
    public TableColumn<Book, String> titleCol;

    @FXML
    public TableColumn<Book, String> IDCol;

    @FXML
    public TableColumn<Book, String> authorCol;

    @FXML
    public TableColumn<Book, String> publisherCol;

    @FXML
    public TableColumn<Book, Boolean> availabilityCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void loadData() {
        DatabaseHandler handler  = new DatabaseHandler();
        String query = "SELECT * from BOOK";
        ResultSet rs = handler.execQuery(query);
        try {
            while (rs.next()) {
                String titlex = rs.getString("title");
                String idx = rs.getString("id");
                String authorx = rs.getString("author");
                String publisherx = rs.getString("publisher");
                Boolean availx = rs.getBoolean("isAvail");

                list.add(new Book(titlex, idx, authorx, publisherx, availx));
                System.out.println(titlex);
            }
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        tableView.getItems().setAll(list);
    }

    private void initCol() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    public static class Book {
        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final  SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleBooleanProperty availability;

        Book(String title, String id, String author, String publisher, Boolean avail) {
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleStringProperty(id);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.availability = new SimpleBooleanProperty(avail);
        }

        public String getTitle() {
            return title.get();
        }

        public String getId() {
            return id.get();
        }
        public String getAuthor() {
            return author.get();
        }
        public String getPublisher() {
            return publisher.get();
        }

        public boolean isAvailability() {
            return availability.get();
        }
    }
}
