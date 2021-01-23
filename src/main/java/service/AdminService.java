package service;

import data_access.*;
import entity.*;
import utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminService {
    public static final String adminPassword = "123456";
    public BookDAO bookDAO;
    public AuthorDAO authorDAO;
    public UserDAO userDAO;
    public BorrowRecordDAO recordDAO;

    public AdminService() {

    }

    // 登录
    public static boolean login() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入管理员密码: ");
        if (in.nextLine().equals(AdminService.adminPassword)) {
            System.out.println("管理员登入成功");
            return true;
        } else {
            System.out.println("管理员密码错误");
            return false;
        }
    }

    // 输出被选中的表格, 不通过DAO层
    public static void viewTable(String table_name) throws Exception {
        // 检查该表是否存在
        if (JDBCUtils.isTableExist(table_name)){
            System.out.println("存在该表,查询成功");
            //获取连接
            Connection conn = JDBCUtils.getConnection();
            String sql = new String("SELECT * FROM "+table_name);
            PreparedStatement ps = conn.prepareStatement(sql);
            //查询
            try {
                ResultSet rs = ps.executeQuery();
                JDBCUtils.showResultSet(rs);
            } catch (SQLException e) {
                System.out.println("viewTable查询失败");
                e.printStackTrace();
            }
        }else{
            System.out.println("不存在的表");
        }
    }

    /**
     * 根据user_id查询某用户的所有借阅记录
     *
     */
    public static void queryRecordOfUser(int user_id) throws IOException, SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = new String(
                "SELECT rec.record_id, rec.book_id, rec.isReturn, book_name, authors.fullname AS author\n" +
                "FROM borrow_records AS rec, books, authors\n"+
                "WHERE rec.user_id = "+user_id+ '\n' +
                "AND rec.book_id = books.book_id\n" + //borrow_records外键book_id -> books.book_name
                "AND books.author_id = authors.author_id\n" //books外键author_id -> authors.fullname
                );
        PreparedStatement ps = conn.prepareStatement(sql);
        //查询
        try {
            ResultSet rs = ps.executeQuery();
            JDBCUtils.showResultSet(rs);
        } catch (SQLException e) {
            System.out.println("queryRecordOfUser查询失败");
            e.printStackTrace();
        }
    }

    /**根据book_id查询某图书的所有借阅历史
    /*/
    //record_id, user_name, book_name, isReturn, authors.fullname, date(时间降序)
    public static void queryRecordOfBook(int book_id) throws IOException, SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = new String(
                "SELECT rec.record_id, rec.book_id, rec.isReturn, rec.date, book_name, authors.fullname AS author\n" +
                        "FROM borrow_records AS rec, books, authors\n"+
                        "WHERE books.book_id = "+book_id+ '\n' +
                        "AND rec.book_id = books.book_id\n" +
                        "AND books.author_id = authors.author_id\n"
        );
        PreparedStatement ps = conn.prepareStatement(sql);
        //查询
        try {
            ResultSet rs = ps.executeQuery();
            JDBCUtils.showResultSet(rs);
        } catch (SQLException e) {
            System.out.println("queryRecordOfUser查询失败");
            e.printStackTrace();
        }
    }
}
