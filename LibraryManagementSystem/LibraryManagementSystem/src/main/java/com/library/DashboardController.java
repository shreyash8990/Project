package com.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    protected void handleAddBook(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/library/AddBook.fxml"));
            // Grab the window from the button that was clicked
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root, 600, 400));
        } catch (Exception e) {
            System.out.println("Error loading Add Book screen!");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleViewBooks(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/library/ViewBooks.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root, 600, 400));
        } catch (Exception e) {
            System.out.println("Error loading View Books screen!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void handleSearchDelete(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/library/SearchDelete.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root, 600, 400));
        } catch (Exception e) {
            System.out.println("Error loading Search & Delete screen!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void handleIssueBook(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/library/IssueBook.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root, 600, 400));
        } catch (Exception e) {
            System.out.println("Error loading Issue Book screen!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void handleReturnBook(ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/library/ReturnBook.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root, 600, 400));
        } catch (Exception e) {
            System.out.println("Error loading Return Book screen!");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleExit(ActionEvent event) {
        System.out.println("Shutting down...");
        System.exit(0);
    }
}
