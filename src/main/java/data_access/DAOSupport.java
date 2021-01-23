package data_access;

import java.sql.*;
import java.util.ArrayList;

import utils.JDBCUtils;

/**
 * 为其他的DAO类提供基本方法
 */
public class DAOSupport {
    protected Connection conn;

    public DAOSupport() throws Exception {
        this.conn = JDBCUtils.getConnection();
    }

    // 无ResultSet返回的sql执行器
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 2.遍历填充参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            // 3.执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4.资源的关闭
            JDBCUtils.close(conn, ps);
        }
        return 0;
    }

}
