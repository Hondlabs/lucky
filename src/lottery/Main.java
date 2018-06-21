package lottery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage ;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/lottery/views/MainWindowView.fxml")));
        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Çekiliş");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
