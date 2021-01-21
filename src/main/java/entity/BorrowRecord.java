package entity;

import java.sql.Date;

public class BorrowRecord {
    private int recordId;
    private int userId;
    private int bookId;
    private Date date;
    private boolean isReturn;

    public BorrowRecord(int userId, int bookId, Date date, boolean isReturn) {
        this.userId = userId;
        this.bookId = bookId;
        this.date = date;
        this.isReturn = isReturn;
    }

    public BorrowRecord(int userId, int bookId, Date date) {
        this.userId = userId;
        this.bookId = bookId;
        this.date = date;
        this.isReturn = false; //默认值
    }
    public BorrowRecord(int recordId){
        this.recordId = recordId;
    }

    public BorrowRecord(){

    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", date=" + date +
                ", isReturn=" + isReturn +
                '}';
    }
}
