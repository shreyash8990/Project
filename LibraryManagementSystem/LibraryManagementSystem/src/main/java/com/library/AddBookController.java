package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private Label messageLabel;

    private BookDAO bookDao = new BookDAO();

    @FXML
    protected void handleSaveBook(ActionEvent event) {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ All fields must be filled!");
            return;
        }

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        
        // Assuming your Book model has a setIsbn method. If it uses a different name, we can change this!
        newBook.setIsbn(isbn);

        try {
            bookDao.addBook(newBook); 
            
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("✅ Book saved successfully!");
            
            titleField.clear();
            authorField.clear();
            isbnField.clear();
            
        } catch (Exception e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ Failed to save book to database.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBackToDashboard(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/library/Dashboard.fxml"));
            Stage stage = (Stage) titleField.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

