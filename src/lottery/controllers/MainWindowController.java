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


    @FXML private ImageView closeBtn;
    @FXML private TextField newUser;
    @FXML private TextField name;
    @FXML private TextField winnerNumber;
    @FXML private TextField backupNumber;
    @FXML private ImageView minBtn;
    @FXML private TextArea userList;

    public static StringBuilder result = new StringBuilder();
    public static StringBuilder backupResult = new StringBuilder();
    public static String lotteryNameString;

    public void draw() {
        lotteryNameString = name.getText();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] line = userList.getText().split("\n");

        ArrayList<String> winners = new ArrayList<>();
        ArrayList<String> backupWinners = new  ArrayList<>();

        Random random = new Random();
        int ranNumber;

        int winnerSize = Integer.parseInt(winnerNumber.getText());
        int backupSize = Integer.parseInt(backupNumber.getText());

        if (winnerSize < line.length && winnerSize+backupSize < line.length && !winnerNumber.getText().equals("") && !backupNumber.getText().equals("")) {

            for (int i = 0; i < winnerSize; i++) {
                ranNumber = random.nextInt(line.length);
                if (!winners.contains(line[ranNumber])) {
                    winners.add(line[ranNumber]);

                    result.append(winners.get(i)).append("\n");
                } else {
                    i--;
                }
            }

            for (int i = 0; i < backupSize; i++) {
                ranNumber = random.nextInt(line.length);
                if (!winners.contains(line[ranNumber]) && !backupWinners.contains(line[ranNumber])) {
                    backupWinners.add(line[ranNumber]);

                    backupResult.append(backupWinners.get(i)).append("\n");
                } else {
                    i--;
                }
            }

            resultWindow();

        }else if (winnerNumber.getText().equals("") || backupNumber.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Boş Giriş!");
            alert.setTitle("Hata");
            alert.setContentText("Talihli sayısı ve yedek talihli sayısı boş bırakılamaz!");
            alert.showAndWait();
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
            alert.setContentText("Girilen kişi sayısı az!");
            alert.showAndWait();
        }

    }

    @FXML
    protected void handleClose() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void handleMinified(){

        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
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
            scene.getStylesheets().add("lottery/style.css");

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

