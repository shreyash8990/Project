import com.library.dao.BookDAO;
import com.library.model.Book;

public class SearchBook {

    public static void main(String[] args) {

        BookDAO dao = new BookDAO();

        Book book = dao.searchBook("Java Programming");

        if(book != null) {
            System.out.println(book);
        }
        else {
            System.out.println("Book Not Found");
        }
    }
}
