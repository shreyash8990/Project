import com.library.dao.BookDAO;

public class DeleteBook {

    public static void main(String[] args) {

        BookDAO dao = new BookDAO();

        dao.deleteBook(2);

    }
}
