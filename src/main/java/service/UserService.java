package service;

import data_access.*;
import entity.*;
import exception.InvalidIDException;
import utils.JDBCUtils;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static utils.JDBCUtils.getConnection;

public class UserService {
    public UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService() {

    }

    /**
     * 注册
     */
    public void register() throws Exception {
        Scanner in = new Scanner(System.in);
        //实例化一个实体层类对象
        User user = new User();
        //表现
        System.out.println("请输入用户名:");
        user.setUsername(in.nextLine());
        System.out.println("请输入密码:");
        user.setPassword(in.nextLine());
        System.out.println("请输入手机号:");
        user.setPhone_num(in.nextLine());
        System.out.println("请输入邮箱:");
        user.setEmail(in.nextLine());

        //实例化一个数据访问层对象，与UserService关联,参数为实体层类对象。
        this.userDAO = new UserDAO(user);
        //userDAO访问数据库
        try {
            userDAO.create();
            System.out.println("注册成功");
        } catch (Exception e) {
            System.out.println("注册失败");
            e.printStackTrace();
        }
    }

    /**
     * 登录
     */
    public boolean login() throws IOException, SQLException {
        //获取当前用户名和密码(与用户交互构造User对象不是该方法的任务)
        String username = this.userDAO.getUser().getUsername();
        String password = this.userDAO.getUser().getPassword();

        String selectedUsername = null;
        String selectedPassword = null;
        //查询
        Connection conn = getConnection();
        String sql = new String(
                "SELECT user_id, username, password, phone_num, email\n" +
                        "FROM users\n" +
                        "WHERE users.username = ?"// username也是唯一的
        );
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userDAO.getUser().getUsername());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //用来身份认证的字段
            selectedUsername = rs.getString("username");
            selectedPassword = rs.getString("password");
            //补充user的其他字段, 以供登陆后用户的其他操作使用
            User user = this.userDAO.getUser();
            user.setId(rs.getInt("user_id"));
            user.setPhone_num(rs.getString("phone_num"));
            user.setEmail(rs.getString("email"));
        }
        //比对
        if (username.equals(selectedUsername) && password.equals(selectedPassword)) {
            System.out.println("登陆成功");
            return true;
        } else {
            System.out.println("登陆失败");
            return false;
        }
    }

    /**
     * 查询自己的借阅记录, 登陆后才可进行(注:凡用到user_id的方法都需要登陆)
     */
    public void queryBorrowRecord() throws IOException, SQLException {
        Connection conn = getConnection();
        int user_id = userDAO.getUser().getId();
        String sql = new String(
                "SELECT rec.record_id, rec.book_id, rec.isReturn, book_name, authors.fullname AS author\n" +
                        "FROM borrow_records AS rec, books, authors\n" +
                        "WHERE rec.user_id = " + user_id + '\n' +
                        "AND rec.book_id = books.book_id\n" + //borrow_records外键book_id -> books.book_name
                        "AND books.author_id = authors.author_id\n" //books外键author_id -> authors.fullname
        );
        PreparedStatement ps = conn.prepareStatement(sql);
        //查询
        try {
            ResultSet rs = ps.executeQuery();
            JDBCUtils.showResultSet(rs);
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
    }

    /**
     * 根据部分图书名进行模糊查询, 无需登录
     */
    public void queryBookByName(String partialName) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM books WHERE book_name LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, '%' + partialName + '%');
        try {
            ResultSet rs = ps.executeQuery();
            JDBCUtils.showResultSet(rs);
        } catch (SQLException e) {
            System.out.println("查书失败");
            e.printStackTrace();
        }
    }

    /**
     * 借书
     * 事务管理
     */
    public void borrowBook(int id) throws Exception {
        Connection conn = null;
        User user = userDAO.getUser();
        String book_sql = "UPDATE books SET isBorrow = 1 WHERE book_id = ?";
        String record_sql = "INSERT INTO borrow_records (user_id, book_id, date, isReturn) VALUES (?, ?, ?, 0)";
        boolean isBookAvailable = true; // 标识该书是否可用(存在且未被借走)
        // 检查1:查询所借书籍, 并验证是否存在(queryById()会抛出InvalidIDException)
        Book selectedBook = null;
        try {
            BookDAO bookDAO = new BookDAO();
            selectedBook = bookDAO.queryById(id);
            //检查2:检查书本是否已被借走
            if (selectedBook.isBorrow()) {
                System.out.println("抱歉, 该书已被借走");
                isBookAvailable = false; //不可用
            }
        } catch (InvalidIDException e) { //捕获id无效的错误
            isBookAvailable = false; //不可用
            System.out.println("请检查id是否有效");
        }
        // 事务开始
        if (isBookAvailable) { //书未被借走
            try {
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);
                // 1.修改被借书的状态
                PreparedStatement book_ps = conn.prepareStatement(book_sql);
                book_ps.setInt(1, id);
                book_ps.executeUpdate();
                // 2.添加借阅记录
                PreparedStatement record_ps = conn.prepareStatement(record_sql, Statement.RETURN_GENERATED_KEYS);
                record_ps.setInt(1, user.getId());
                record_ps.setInt(2, selectedBook.getId());
                record_ps.setDate(3, new Date(System.currentTimeMillis()));
                record_ps.executeUpdate();
                conn.commit(); //提交
                System.out.println("借书成功");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("借书失败, 已回滚");
                e.printStackTrace();
            }
        }
    }

    /**
     * 还书
     * 事务管理
     */
    public void returnBook(int book_id) throws Exception {
        Connection conn = null;
        String book_sql = "UPDATE books SET isBorrow = 0 WHERE book_id = ?";
        String record_sql = "UPDATE borrow_records SET isReturn = 1 WHERE record_id = ?";
        boolean isBookAvailable = true; // 标识该书是否可还(存在)
        // 检查所还书籍是否存在
        Book book = null;
        try {
            BookDAO bookDAO = new BookDAO();
            book = bookDAO.queryById(book_id);
        } catch (InvalidIDException e) { //捕获id无效的错误
            isBookAvailable = false;
            System.out.println("请检查id是否有效");
        }
        // 事务开始
        if(isBookAvailable) {
            try {
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);
                // 1.修改被借书的状态
                PreparedStatement book_ps = conn.prepareStatement(book_sql);
                book_ps.setInt(1, book_id);
                book_ps.executeUpdate();
                // 2.修改借阅记录状态
                //通过book_id查询其最近的借阅记录的record_id
                int record_id = 0;
                String sql = new String(
                        "SELECT record_id \n" +
                                "FROM borrow_records AS rec, books\n" +
                                "WHERE books.book_id = " + book_id + '\n' +
                                "AND rec.book_id = books.book_id\n" +
                                "ORDER BY rec.date DESC\n" +
                                "LIMIT 1");
                ResultSet rs = JDBCUtils.returnResultSet(sql);
                if (rs.next()) {
                    record_id = rs.getInt(1);
                }
                //通过record_id修改状态
                PreparedStatement record_ps = conn.prepareStatement(record_sql);
                record_ps.setInt(1, record_id);
                record_ps.executeUpdate();
                conn.commit();
                System.out.println("还书成功");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("还书失败, 已回滚");
                e.printStackTrace();
            }
        }
    }
}

