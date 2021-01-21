package data_access;

import entity.Book;
import entity.BorrowRecord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowRecordDAO extends DAOSupport {
    BorrowRecord record;
    public BorrowRecordDAO(BorrowRecord record) throws Exception {
        super();
        this.record = record;
    }

    public BorrowRecordDAO() throws Exception {
        super();
    }

    // 增
    public void create() {
        String sql = new String("INSERT INTO borrow_records (user_id,book_id,date,isReturn) VALUES (?, ?, ?, ?)");
        super.update(this.conn, sql, record.getUserId(), record.getBookId(), record.getDate(), record.isReturn());
    }

    // 删, 通过record_id
    public void delete() {
        super.update(this.conn, "DELETE FROM borrow_records WHERE record_id = " + record.getRecordId());
    }

    // 更新, 通过record_id
    public void update(Object... args) {
        super.update(this.conn, "UPDATE borrow_records SET user_id = ?, book_id = ?, date = ?, isReturn = ? WHERE book_id = " + record.getRecordId(), args);
    }

    // 查询, 通过record_id, 返回一个BorrowRecord实例
    public BorrowRecord queryById(int id) {
        BorrowRecord record =new BorrowRecord();
        String sql = "SELECT * FROM borrow_records WHERE record_id = " + id;
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            // book对象字段赋值
            rs.next();
            record.setRecordId(id);
            record.setUserId(rs.getInt(2));
            record.setBookId(rs.getInt(3));
            record.setDate(rs.getDate(4));
            record.setReturn(rs.getBoolean(5));

        } catch (SQLException e) {
            System.out.println("通过ID查询借阅记录时出错");
            e.printStackTrace();
        }
        return record;
    }

}
