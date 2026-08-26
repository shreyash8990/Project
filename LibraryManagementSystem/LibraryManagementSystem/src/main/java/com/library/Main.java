package com.library;
import com.library.dao.IssueBookDAO;
import com.library.dao.AdminDAO;
import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminDAO adminDao = new AdminDAO();

        System.out.println(" Library Management System - LOGIN ");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!adminDao.authenticate(username, password)) {
            System.out.println(" Invalid credentials. Shutting down...");
            return; // This completely stops the program
        }

        System.out.println(" Login Successful!\n");

        BookDAO bookDao = new BookDAO();
        IssueBookDAO issueBookDao = new IssueBookDAO();
        boolean isRunning = true;

        System.out.println("📚 Welcome to the Library Management System!");

        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add a New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search for a Book");
            System.out.println("4. Delete a Book");
            System.out.println("5. Issue a Book");
            System.out.println("6. Return a Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // This clears the enter key from the scanner

            switch (choice) {
                case 1:
                    Book newBook = new Book();
                    
                    System.out.print("Enter Title: ");
                    newBook.setTitle(scanner.nextLine());
                    
                    System.out.print("Enter Author: ");
                    newBook.setAuthor(scanner.nextLine());
                    
                    System.out.print("Enter Publisher: ");
                    newBook.setPublisher(scanner.nextLine());
                    
                    System.out.print("Enter Category: ");
                    newBook.setCategory(scanner.nextLine());
                    
                    System.out.print("Enter ISBN: ");
                    newBook.setIsbn(scanner.nextLine());
                    
                    System.out.print("Enter Total Copies: ");
                    newBook.setTotalCopies(scanner.nextInt());
                    
                    System.out.print("Enter Available Copies: ");
                    newBook.setAvailableCopies(scanner.nextInt());
                    
                    bookDao.addBook(newBook);
                    break;

                case 2:
                    System.out.println("\n--- LIST OF BOOKS ---");
                    ArrayList<Book> books = bookDao.getAllBooks();
                    for (Book b : books) {
                        System.out.println("ID: " + b.getBookId() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor());
                    }
                    break;

                case 3:
                    System.out.print("\nEnter the Exact Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    
                    // Call your DAO search method
                    Book foundBook = bookDao.searchBook(searchTitle);
                    
                    if (foundBook != null) {
                        System.out.println("✅ Book Found: ID " + foundBook.getBookId() + " | " + foundBook.getTitle() + " by " + foundBook.getAuthor() + " | Available: " + foundBook.getAvailableCopies());
                    } else {
                        System.out.println("❌ Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter the Book ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Clear scanner
                    
                    // Call your DAO delete method
                    bookDao.deleteBook(deleteId);
                    break;
                
                case 5:
                    System.out.print("\nEnter the Book ID to Issue: ");
                    int issueId = scanner.nextInt();
                    scanner.nextLine(); // Clear scanner

                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();

                    System.out.print("Enter Student Phone: ");
                    String studentPhone = scanner.nextLine();

                    issueBookDao.issueBook(issueId, studentName, studentPhone);
                    break;
                case 6:
                    System.out.print("\nEnter the Book ID to Return: ");
                    int returnId = scanner.nextInt();
                    scanner.nextLine(); // Clear scanner

                    System.out.print("Enter Student Name: ");
                    String returnStudent = scanner.nextLine();

                    issueBookDao.returnBook(returnId, returnStudent);
                    break;

                case 7:
                    isRunning = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
