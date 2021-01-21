package data_access;

import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO extends DAOSupport {
    private Book book;

    public BookDAO(Book book) throws Exception {
        super();
        this.book = book;
    }

    public BookDAO() throws Exception {
        super();
    }

    // 增
    public void create() {
        String sql = new String("INSERT INTO books (book_name,author_id,publisher,isBorrow) VALUES (?, ?, ?, ?)");
        super.update(this.conn, sql, book.getName(), book.getAuthor_id(), book.getPublisher(), book.isBorrow());
    }

    // 删, 通过book_id
    public void delete() {
        super.update(this.conn, "DELETE FROM books WHERE book_id = " + book.getId());
    }

    // 更新, 通过book_id
    public void update(Object... args) {
        super.update(this.conn, "UPDATE books SET book_name = ?, author_id = ?, publisher = ?, isBorrow = ? WHERE book_id = " + book.getId(), args);
    }

    // 查询, 通过book_id, 返回一个Book实例
    public Book queryById(int id) {
        Book book =new Book();
        String sql = "SELECT * FROM books WHERE book_id = " + id;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            // book对象字段赋值
            rs.next();
            book.setId(id);
            book.setName(rs.getString(2));
            book.setAuthor_id(rs.getInt(3));
            book.setPublisher(rs.getString(4));
            book.setBorrow(rs.getBoolean(5));

        } catch (SQLException e) {
            System.out.println("通过ID查询书时出错");
            e.printStackTrace();
        }
        return book;
    }
}
