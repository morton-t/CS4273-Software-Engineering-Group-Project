import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class RatingMenu extends Application {

    public int speedRating = -1;
    public int friendlinessRating = -1;

    public static void main(String[] args) {
        launch(args);
    }

    //Zach Paden
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rating Menu");

        ToggleGroup speedToggleGroup = new ToggleGroup();
        ToggleGroup friendlinessToggleGroup = new ToggleGroup();

        VBox speedVBox = createRatingVBox("Speed", speedToggleGroup);
        VBox friendlinessVBox = createRatingVBox("Friendliness", friendlinessToggleGroup);

        //Zach Paden
        Button declineButton = new Button("Decline");
        declineButton.setOnAction(e -> {
            showThanksForRating();
            primaryStage.close();
        });
        //Zach Paden
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (speedToggleGroup.getSelectedToggle() != null && friendlinessToggleGroup.getSelectedToggle() != null) 
            {
                showThanksForRating();
                primaryStage.close();
            } 
            else 
            {
                showAlert("Please rate both speed and friendliness.");
            }
        });

        //Zach Paden
        VBox mainVBox = new VBox(20);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.getChildren().addAll(speedVBox, friendlinessVBox, declineButton, submitButton);
        //Zach Paden
        Scene scene = new Scene(mainVBox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//Thomas Morton
    public void showThanksForRating() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thanks for Rating");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for providing your ratings!");
        alert.showAndWait();
    }
    
    //Micah Trent
    public VBox createRatingVBox(String label, ToggleGroup toggleGroup) {
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(label + " Rating:");
        HBox ratingBox = new HBox(10);
        ratingBox.setAlignment(Pos.CENTER);

        for (int i = 1; i <= 5; i++) {
            RadioButton radioButton = new RadioButton(Integer.toString(i));
            radioButton.setToggleGroup(toggleGroup);
            ratingBox.getChildren().add(radioButton);
        }

        vBox.getChildren().addAll(titleLabel, ratingBox);

        return vBox;
    }

    

    //Thomas Morton
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}