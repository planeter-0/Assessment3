import utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ReadPropertiesTest {
    public static void main(String[] args) throws IOException, SQLException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}
