package library.assistant.ui.listmember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MemberListLoader extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/library/assistant/ui/listmember/member_list.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void Main (String args[]) {launch(args);}
}


