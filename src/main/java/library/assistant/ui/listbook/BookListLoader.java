package library.assistant.ui.listbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class BookListLoader extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/java/library/assistant/ui/listBook/book_list.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
