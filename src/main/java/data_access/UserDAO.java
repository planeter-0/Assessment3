package data_access;

import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO extends DAOSupport {
    User user;

    public UserDAO(User user) throws Exception {
        super();
        this.user = user;
    }

    public UserDAO() throws Exception {
        super();
    }

    // 增
    public void create() {
        String sql = new String("INSERT INTO users (username, password, phone_num, email) VALUES (?, ?, ?, ?)");
        super.update(this.conn, sql, user.getUsername(), user.getPassword(), user.getPhone_num(), user.getEmail());
    }

    // 删, 通过user_id
    public void delete() {
        super.update(this.conn, "DELETE FROM users WHERE user_id = " + user.getId());
    }

    // 更新, 通过user_id
    public void update(Object... args) {
        super.update(this.conn, "UPDATE users SET username = ?, password = ?, phone_num = ?, email = ? WHERE user_id = " + user.getId(), args);
    }

    // 查询, 通过user_id, 返回一个User实例
    public User queryById(int id) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE user_id = " + id;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            // user对象字段赋值
            rs.next();
            user.setId(id);
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPhone_num(rs.getString(4));
            user.setEmail(rs.getString(5));

        } catch (SQLException e) {
            System.out.println("通过ID查询用户时出错");
            e.printStackTrace();
        }
        return user;
    }

    public User getUser() {
        return user;
    }
}
