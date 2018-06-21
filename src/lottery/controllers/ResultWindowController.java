package lottery.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ResultWindowController {
    @FXML public ImageView closeBtn;

    @FXML
    protected void handleClose() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        System.out.printf("Stage closed");
    }
}
