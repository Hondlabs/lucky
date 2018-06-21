package lottery.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultWindowController {
    @FXML public ImageView closeBtn;
    @FXML public TextArea winners;
    @FXML public Text lotteryName;
    public ImageView minBtn;

    public void initialize(){

        lotteryName.setText(MainWindowController.lotteryNameString);

        winners.appendText("Asıl Talihliler\n");
        winners.appendText("-------------------------------------------------\n");
        winners.appendText(String.valueOf(MainWindowController.result)+"\n\n");
        winners.appendText("Yedek Talihliler\n");
        winners.appendText("-------------------------------------------------\n");
        winners.appendText(String.valueOf(MainWindowController.backupResult));
        MainWindowController.result.setLength(0);
        MainWindowController.backupResult.setLength(0);
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

    public void copy(ActionEvent actionEvent) {
        ClipboardContent content = new ClipboardContent();
        content.putString(winners.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }
}
