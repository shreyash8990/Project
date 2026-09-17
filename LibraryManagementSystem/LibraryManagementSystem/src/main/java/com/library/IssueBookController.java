package com.library;

import com.library.dao.IssueBookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IssueBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField studentNameField;
    @FXML private TextField studentPhoneField;
    @FXML private Label messageLabel;

    private IssueBookDAO issueDao = new IssueBookDAO();

    @FXML
    protected void handleIssueBook(ActionEvent event) {
        String bookIdText = bookIdField.getText();
        String studentName = studentNameField.getText();
        String studentPhone = studentPhoneField.getText();

        if (bookIdText.isEmpty() || studentName.isEmpty() || studentPhone.isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ All fields must be filled!");
            return;
        }

        try {
            // Convert the text input into an integer for your DAO
            int bookId = Integer.parseInt(bookIdText);
            
            // Call your DAO logic
            issueDao.issueBook(bookId, studentName, studentPhone);
            
            messageLabel.setStyle("-fx-text-fill: blue;");
            messageLabel.setText("✅ Request processed! Check terminal for status.");
            
            bookIdField.clear();
            studentNameField.clear();
            studentPhoneField.clear();
            
        } catch (NumberFormatException e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ Book ID must be a valid number.");
        } catch (Exception e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ Database error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBackToDashboard(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/library/Dashboard.fxml"));
            Stage stage = (Stage) bookIdField.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
