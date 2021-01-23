package DAOTest;

import data_access.BookDAO;
import entity.Book;

public class BookDAOTest {
    /**
     * 分别取消注释进行测试
     */
    public static void main(String[] args) throws Exception {
        //增 通过
//        Book book = new Book("流畅的女装",1,"工具人出版社");
//        BookDAO bookDAO = new BookDAO(book);
//        bookDAO.create();

        //删 通过
//        Book book = new Book(15);
//        BookDAO bookDAO = new BookDAO(book);
//        bookDAO.delete();

        //改 通过
//        Book book = new Book(17);
//        BookDAO bookDAO = new BookDAO(book);
//        bookDAO.update("女装必知必会","3","emmm","0");

        //查 通过
//        BookDAO bookDAO = new BookDAO();
//        Book selectedBook = bookDAO.queryById(6); //有效id
//        System.out.println(selectedBook);

//        BookDAO bookDAO = new BookDAO();
//        Book selectedBook = bookDAO.queryById(1245); //无效id
//        System.out.println(selectedBook);
    }
}
