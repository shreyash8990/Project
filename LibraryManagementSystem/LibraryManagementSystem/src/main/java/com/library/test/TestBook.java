package com.library.test;
import com.library.dao.BookDAO;
import com.library.model.Book;

public class TestBook {

    public static void main(String[] args) {


        Book book = new Book(
                "Java Programming",
                "James Gosling",
                "Oracle",
                "Programming",
                "JAVA101",
                5,
                5
        );


        BookDAO dao = new BookDAO();


        dao.addBook(book);


    }
}
