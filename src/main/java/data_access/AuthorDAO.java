package data_access;

import entity.Author;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO extends DAOSupport {
    Author author;

    public AuthorDAO(Author author) throws Exception {
        super();
        this.author = author;
    }

    public AuthorDAO() throws Exception {

    }

    // 增
    public void create() {
        String sql = new String("INSERT INTO authors (fullname, country, description) VALUES (?, ?, ?)");
        super.update(this.conn, sql, author.getFullName(), author.getCountry(), author.getDescription());
    }

    // 删, 通过author_id
    public void delete() {
        super.update(this.conn, "DELETE FROM authors WHERE author_id = " + author.getId());
    }

    // 更新, 通过author_id
    public void update(Object... args) {
        super.update(this.conn, "UPDATE authors SET fullname = ?, country = ?, description = ? WHERE author_id = " + author.getId(), args);
    }

    // 查询, 通过author_id, 返回一个Author实例
    public Author queryById(int id) {
        Author author = new Author();
        String sql = "SELECT * FROM authors WHERE author_id = " + id;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            // author对象字段赋值
            rs.next();
            author.setId(id);
            author.setFullName(rs.getString(2));
            author.setCountry(rs.getString(3));
            author.setDescription(rs.getString(4));

        } catch (SQLException e) {
            System.out.println("通过ID查询作者时出错");
            e.printStackTrace();
        }
        return author;
    }

}
