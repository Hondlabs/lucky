package lottery.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class ResultWindowController {
    @FXML public ImageView closeBtn;
    @FXML public TextArea winners;

    public void initialize(){
        winners.setText(String.valueOf(MainWindowController.result));
    }

    @FXML
    protected void handleClose() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void copy(ActionEvent actionEvent) {
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(MainWindowController.result));
        Clipboard.getSystemClipboard().setContent(content);
    }
}
