import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.ArrayList;

public class ViewBooks {

    public static void main(String[] args) {

        BookDAO dao = new BookDAO();

        ArrayList<Book> books = dao.getAllBooks();


        for(Book book : books){

            System.out.println("-------------------");
            System.out.println(book);

        }
    }
}
