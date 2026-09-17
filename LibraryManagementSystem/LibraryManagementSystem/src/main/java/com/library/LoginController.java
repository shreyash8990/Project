package com.library;

import com.library.dao.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private AdminDAO adminDao = new AdminDAO();

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (adminDao.authenticate(username, password)) {
            try {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("✅ Login Successful! Loading menu...");

                // Load the new Dashboard UI
                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/com/library/Dashboard.fxml"));
                
                // Get the current window and change its scene to the Dashboard
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(dashboardRoot, 600, 400));
                stage.setTitle("Library Dashboard");
                
            } catch (Exception e) {
                System.out.println("Error loading the dashboard screen!");
                e.printStackTrace();
            }
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ Invalid credentials. Try again.");
        }
    }
}
