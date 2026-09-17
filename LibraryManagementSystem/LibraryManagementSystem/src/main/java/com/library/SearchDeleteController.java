package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchDeleteController {

    @FXML private TextField searchField;
    @FXML private Label messageLabel;
    @FXML private Label bookDetailsLabel;
    @FXML private Button deleteButton;

    private BookDAO bookDao = new BookDAO();
    private Book foundBook = null; // Stores the book if we find it

    @FXML
    protected void handleSearchBook(ActionEvent event) {
        String title = searchField.getText();

        if (title.isEmpty()) {
            showMessage("❌ Please enter a title to search.", "red");
            hideBookDetails();
            return;
        }

        foundBook = bookDao.searchBook(title);

        if (foundBook != null) {
            showMessage("✅ Book found!", "green");
            
            // Format the book details for the screen
            String details = "ID: " + foundBook.getBookId() + "\n" +
                             "Title: " + foundBook.getTitle() + "\n" +
                             "Author: " + foundBook.getAuthor() + "\n" +
                             "ISBN: " + foundBook.getIsbn();
            
            bookDetailsLabel.setText(details);
            bookDetailsLabel.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            showMessage("❌ No book found with that title.", "red");
            hideBookDetails();
        }
    }

    @FXML
    protected void handleDeleteBook(ActionEvent event) {
        if (foundBook != null) {
            try {
                // Your DAO requires the integer ID to delete the book
                bookDao.deleteBook(foundBook.getBookId());
                showMessage("✅ Book successfully deleted!", "green");
                hideBookDetails();
                searchField.clear();
            } catch (Exception e) {
                showMessage("❌ Error deleting book.", "red");
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void handleBackToDashboard(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/library/Dashboard.fxml"));
            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message, String color) {
        messageLabel.setStyle("-fx-text-fill: " + color + ";");
        messageLabel.setText(message);
    }

    private void hideBookDetails() {
        bookDetailsLabel.setVisible(false);
        deleteButton.setVisible(false);
        foundBook = null;
    }
}
