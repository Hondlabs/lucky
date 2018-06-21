package lottery.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class MainWindowController {


    @FXML public ImageView closeBtn;
    @FXML public TextField newUser;

    @FXML private TextArea userList;
    public static StringBuilder result = new StringBuilder();

    public void draw() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] line = userList.getText().split("\n");
        ArrayList<String> winners = new ArrayList<>();
        Random random = new Random();
        int ranNumber;

        int winnerSize = 3;

        if (winnerSize < line.length) {
            for (int i = 0; i < winnerSize; i++) {
                ranNumber = random.nextInt(line.length);
                if (!winners.contains(line[ranNumber])) {
                    winners.add(line[ranNumber]);

                    result.append(winners.get(i)).append("\n");
                } else {
                    i--;
                }
            }

            resultWindow();

        } else if (winnerSize == line.length) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Az Kişi!");
            alert.setTitle("Hata");
            alert.setContentText("Seçilen kazanan sayısı girilen kişi sayısına eşit!");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Az Kişi!");
            alert.setTitle("Hata");
            alert.setContentText("Seçilen kazanan sayısı girilen kişi sayısından fazla!");
            alert.showAndWait();
        }

    }

    @FXML
    protected void handleClose() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }


    public void addUser(ActionEvent actionEvent) {
        userList.appendText(newUser.getText().trim()+"\n");
        newUser.setText("");
    }

    public void resultWindow(){

        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/lottery/views/ResultWindowView.fxml")));
            AnchorPane pane = loader.load();

            Scene scene = new Scene(pane);

            Stage resultStage = new Stage();
            resultStage.setScene(scene);

            resultStage.setTitle("Sonuçlar");
            resultStage.initStyle(StageStyle.UNDECORATED);
            resultStage.initModality(Modality.APPLICATION_MODAL);
            resultStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

