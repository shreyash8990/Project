package com.library.dao;

import com.library.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

public class IssueBookDAO {

    public void issueBook(int bookId, String studentName, String studentPhone) {
        // SQL Queries
        String checkBookSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String issueSql = "INSERT INTO issued_books(book_id, student_name, student_phone, issue_date) VALUES(?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {
            
            // 1. Check if the book exists and has available copies
            PreparedStatement checkStmt = con.prepareStatement(checkBookSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int availableCopies = rs.getInt("available_copies");
                
                if (availableCopies > 0) {
                    // 2. Issue the book to the student
                    PreparedStatement issueStmt = con.prepareStatement(issueSql);
                    issueStmt.setInt(1, bookId);
                    issueStmt.setString(2, studentName);
                    issueStmt.setString(3, studentPhone);
                    issueStmt.setDate(4, Date.valueOf(LocalDate.now())); // Gets today's date automatically
                    
                    int result = issueStmt.executeUpdate();

                    if (result > 0) {
                        // 3. Decrease the available copies by 1
                        PreparedStatement updateStmt = con.prepareStatement(updateBookSql);
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();
                        
                        System.out.println(" Book issued successfully to " + studentName + "!");
                    }
                } else {
                    System.out.println(" Sorry, there are no available copies of this book left.");
                }
            } else {
                System.out.println(" Book ID not found in the library.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void returnBook(int bookId, String studentName) {
        String checkSql = "SELECT * FROM issued_books WHERE book_id = ? AND student_name = ?";
        String deleteIssueSql = "DELETE FROM issued_books WHERE book_id = ? AND student_name = ?";
        String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {
            
            // 1. Check if the book was actually issued to this student
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            checkStmt.setString(2, studentName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // 2. Remove the record from issued_books
                PreparedStatement deleteStmt = con.prepareStatement(deleteIssueSql);
                deleteStmt.setInt(1, bookId);
                deleteStmt.setString(2, studentName);
                deleteStmt.executeUpdate();

                // 3. Increase the available copies in the books table
                PreparedStatement updateStmt = con.prepareStatement(updateBookSql);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("✅ Book returned successfully by " + studentName + "!");
            } else {
                System.out.println("❌ No record found of this book being issued to that student.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}





























