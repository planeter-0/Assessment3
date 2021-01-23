package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 */
public class JDBCUtils {
    /**
     * 连接数据库，返回Connection实例
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String driver = props.getProperty("driver");
        // 注册并加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动出错");
            return null;
        }

        // 获取连接
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库出错");
            return null;
        }
    }

    /**
     * 关闭Connection和PreparedStatement
     */
    public static void close(Connection conn, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ps为null");
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("conn为null");
        }
    }

    /**
     * 关闭Connection, PreparedStatement和ResultSet
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ps为null");
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("conn为null");
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("rs为null");
        }
    }

    /**
     * 打印ResultSet
     */
    public static void showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(", \t");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(", \t\t");
                System.out.print(result.getString(i));
            }
            System.out.println();
        }
    }

    /**
     * 检查是否存在该表
     */
    public static boolean isTableExist(String tableName) throws IOException, SQLException {
        //获取连接
        Connection conn = JDBCUtils.getConnection();
        //获取元数据
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getTables("test1", null, "%", new String[]{"TABLE"});
        ResultSetMetaData rmd = rs.getMetaData();
        while (rs.next()) {
            for (int i = 3; i <= rmd.getColumnCount(); i+=10) {
                if(tableName.equals(rs.getString(i)))
                    return true;
            }
        }
        return false;
    }
    /**
     * 返回ResultSet的简单sql执行器, 方法结束后不关闭资源
     */
    public static ResultSet returnResultSet(String sql) throws IOException, SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet results =  ps.executeQuery();
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
