import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//  Micah Trent
public class RatingsMenu extends Application {

    //  Micah Trent
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ratings Menu");

        // Create a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels for speed and satisfaction
        Label speedLabel = new Label("Speed of Delivery:");
        Label satisfactionLabel = new Label("Satisfaction with Service:");

        // Labels to display current ratings
        Label speedRatingLabel = new Label("Rating: ");
        Label satisfactionRatingLabel = new Label("Rating: ");

        // Thomas Morton
        // Sliders for speed and satisfaction ratings
        Slider speedSlider = createSlider();
        Slider satisfactionSlider = createSlider();

        // Event handlers for sliders
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                speedRatingLabel.setText("Rating: " + newValue.intValue()));

        satisfactionSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                satisfactionRatingLabel.setText("Rating: " + newValue.intValue()));

        // Add components to the grid pane
        gridPane.add(speedLabel, 0, 0);
        gridPane.add(speedSlider, 1, 0);
        gridPane.add(speedRatingLabel, 2, 0);

        gridPane.add(satisfactionLabel, 0, 1);
        gridPane.add(satisfactionSlider, 1, 1);
        gridPane.add(satisfactionRatingLabel, 2, 1);

        // Create an HBox for the Submit button and center it
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        Button submitButton = new Button("Submit");

        // Event handler for the Submit button (you can replace this with your submission logic)
        submitButton.setOnAction(event -> {
            System.out.println("Speed Rating: " + speedSlider.getValue());
            System.out.println("Satisfaction Rating: " + satisfactionSlider.getValue());
        });

        buttonBox.getChildren().add(submitButton);

        // Add the button box to the grid pane
        gridPane.add(buttonBox, 0, 2, 3, 1);

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    //Zach Paden (easy)
    public static void main(String[] args) {
        launch(args);
    }
}
