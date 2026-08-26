
package com.library.dao;

import com.library.database.DBConnection;
import com.library.model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {


    // ADD BOOK
    public void addBook(Book book) {

        String sql = "INSERT INTO books(title,author,publisher,category,isbn,total_copies,available_copies) VALUES(?,?,?,?,?,?,?)";


        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getIsbn());
            ps.setInt(6, book.getTotalCopies());
            ps.setInt(7, book.getAvailableCopies());


            int result = ps.executeUpdate();


            if(result > 0) {

                System.out.println("Book Added Successfully!");

            }


        } catch(Exception e) {

            e.printStackTrace();

        }

    }



    // VIEW ALL BOOKS
    public ArrayList<Book> getAllBooks() {


        ArrayList<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";


        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);



            while(rs.next()) {


                Book book = new Book();


                book.setBookId(rs.getInt("book_id"));

                book.setTitle(rs.getString("title"));

                book.setAuthor(rs.getString("author"));

                book.setPublisher(rs.getString("publisher"));

                book.setCategory(rs.getString("category"));

                book.setIsbn(rs.getString("isbn"));

                book.setTotalCopies(rs.getInt("total_copies"));

                book.setAvailableCopies(rs.getInt("available_copies"));



                books.add(book);

            }



        } catch(Exception e) {

            e.printStackTrace();

        }


        return books;

    }




    // SEARCH BOOK BY TITLE
    public Book searchBook(String title) {


        Book book = null;


        String sql = "SELECT * FROM books WHERE title=?";


        try {


            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1,title);


            ResultSet rs = ps.executeQuery();



            if(rs.next()) {


                book = new Book();


                book.setBookId(rs.getInt("book_id"));

                book.setTitle(rs.getString("title"));

                book.setAuthor(rs.getString("author"));

                book.setPublisher(rs.getString("publisher"));

                book.setCategory(rs.getString("category"));

                book.setIsbn(rs.getString("isbn"));

                book.setTotalCopies(rs.getInt("total_copies"));

                book.setAvailableCopies(rs.getInt("available_copies"));


            }


        } catch(Exception e) {

            e.printStackTrace();

        }


        return book;

    }




    // DELETE BOOK
    public void deleteBook(int bookId) {


        String sql = "DELETE FROM books WHERE book_id=?";


        try {


            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1, bookId);


            int result = ps.executeUpdate();



            if(result > 0) {

                System.out.println("Book Deleted Successfully!");

            }
            else {

                System.out.println("Book Not Found!");

            }



        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}
