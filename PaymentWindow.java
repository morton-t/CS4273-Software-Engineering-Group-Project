import javafx.application.*;
import javafx.geometry.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.*;
import java.text.*;

//Thomas Morton
public class PaymentWindow extends Application {

    private ToggleGroup paymentMethodToggleGroup;
    private TextArea confirmationTextArea;
    private Label remainingBalanceLabel;
    private TextField paymentAmountField;
    private TextField creditCardNumberField;
    private RadioButton creditCardRadioButton;
    private RadioButton paypalRadioButton;
    private RadioButton cashPaymentRadioButton;
    
    private double initialBalance = 50.00; // Initial balance, you can set your own initial value
    private long transactionNumber = 15232445;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Payment Selection and Confirmation");

        // Create a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Payment method selection buttons
        paymentMethodToggleGroup = new ToggleGroup();
        
        creditCardRadioButton = new RadioButton("Credit Card");
        creditCardRadioButton.setToggleGroup(paymentMethodToggleGroup);
        
        paypalRadioButton = new RadioButton("PayPal");
        paypalRadioButton.setToggleGroup(paymentMethodToggleGroup);
        
        cashPaymentRadioButton = new RadioButton("Cash Payment");
        cashPaymentRadioButton.setToggleGroup(paymentMethodToggleGroup);
        
        // Text field for credit card number
        creditCardNumberField = new TextField();
        creditCardNumberField.setPromptText("Credit Card Number");
        creditCardNumberField.setDisable(true);

        // Text field for payment amount
        paymentAmountField = new TextField();
        paymentAmountField.setPromptText("Enter Amount");

        // Label for the remaining balance
        remainingBalanceLabel = new Label("Remaining Balance: $" + formatCurrency(initialBalance));
        
        // Text area for confirmation message
        confirmationTextArea = new TextArea();
        confirmationTextArea.setEditable(false);
        
        // Add members to the grid pane
        gridPane.add(creditCardRadioButton, 0, 1);
        gridPane.add(cashPaymentRadioButton, 0, 2);
        gridPane.add(paypalRadioButton, 0, 3);
        gridPane.add(new Label("Payment Amount:"), 0, 4);
        gridPane.add(creditCardNumberField, 4, 1);
        gridPane.add(paymentAmountField, 4, 4);
        gridPane.add(remainingBalanceLabel, 0, 5, 2, 1);

        // Listen for changes in the credit card radio button selection
        creditCardRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            creditCardNumberField.setDisable(!newValue);
            creditCardNumberField.clear();
        });

        // Confirm Payment button
        Button confirmPaymentButton = new Button("Confirm Payment");
        confirmPaymentButton.setOnAction(event -> confirmPayment());

        // Add components to a VBox
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(gridPane, confirmationTextArea, confirmPaymentButton);
        vbox.setAlignment(Pos.CENTER);

        // Create the scene and set it on the stage
        Scene scene = new Scene(vbox, 400, 370);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    //  Micah Trent
    private void confirmPayment() {
        RadioButton selectedRadioButton = (RadioButton) paymentMethodToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            String paymentMethod = selectedRadioButton.getText();
            String paymentAmountText = paymentAmountField.getText();
            String confirmationMessage = "";
            
            // Validate input for payment amount
            try {
                double paymentAmount = Double.parseDouble(paymentAmountText);
                if (selectedRadioButton == creditCardRadioButton) {
                    String creditCardNumber = creditCardNumberField.getText();
                    
                    if (!(creditCardNumber.length() == 16 && creditCardNumber.matches("\\d+"))) {
                        confirmationMessage += "\nError: Please enter a valid 16-digit credit card number.";
                        confirmationTextArea.setText(confirmationMessage);
                        return;
                    }
                } 
                else if (selectedRadioButton == paypalRadioButton) {
                    //openPaypalLoginWindow(); //FIXME: This needs to return a value for paypal's payment confirmation!
                }
                
                if (paymentAmount > initialBalance) {
                    confirmationTextArea.setText("Error: Payment amount exceeds remaining balance.");
                    return;
                }
                initialBalance -= paymentAmount;
                remainingBalanceLabel.setText("Remaining Balance: $" + formatCurrency(initialBalance));

                confirmationMessage = "Payment Confirmed!" +
                                        "\n\nSelected Payment Method: " + paymentMethod +
                                        "\nPayment Amount: $" + formatCurrency(paymentAmount) +
                                        "\nRemaining Amount Due: $" + formatCurrency(initialBalance) +
                                        "\n\nTransaction Number: " + transactionNumber;
                                        
                transactionNumber += 1;
                confirmationTextArea.setText(confirmationMessage);
            } 
            catch (NumberFormatException e) {
                confirmationTextArea.setText("Error: Please enter a valid numerical payment amount.");
            }
        } 
        else {
            confirmationTextArea.setText("Please select a payment method.");
        }
    }

    //Thomas Morton
    private String formatCurrency(double amount) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(amount);
    }

    //Thomas Morton
    public static void main(String[] args) {
        launch(args);
    }
}
