package lottery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class Controller {


    @FXML public ImageView closeBtn;
    @FXML public TextField newUser;

    @FXML private TextArea userList;

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
        StringBuilder result = new StringBuilder();

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


            Dialog<ButtonType> dialog = new Dialog<>();
            final DialogPane resultPane = dialog.getDialogPane();
            resultPane.setContentText("Kazananlar");
            resultPane.getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.initModality(Modality.APPLICATION_MODAL);


            TextArea textArea = new TextArea(result.toString());
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            Button copy = new Button("Kopyala");
            copy.setTooltip(new Tooltip("Kopyala"));
            String finalResult = result.toString();
            copy.setOnAction(event -> {
                ClipboardContent content = new ClipboardContent();
                content.putString(finalResult);
                Clipboard.getSystemClipboard().setContent(content);

            });

            copy.setPadding(new Insets(10));

            GridPane root = new GridPane();
            root.setVisible(false);
            root.add(textArea, 0, 0);
            root.add(copy, 0, 2);
            resultPane.setExpandableContent(root);
            dialog.showAndWait();
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
         System.out.printf("Stage closed");
    }


    public void addUser(ActionEvent actionEvent) {
        userList.appendText(newUser.getText().trim()+"\n");
        newUser.setText("");
    }
}

