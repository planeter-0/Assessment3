package entity;
/**
 * ORM (object relational mapping)
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 * 实体类Book
 */
public class Book {
    private int id;
    private String name;
    private int author_id;
    private String publisher;
    private boolean isBorrow;

    public Book(String name, int author_id, String publisher, boolean isBorrow) {
        this.name = name;
        this.author_id = author_id;
        this.publisher = publisher;
        this.isBorrow = isBorrow;
    }

    public Book(String name, int author_id, String publisher) {
        this.name = name;
        this.author_id = author_id;
        this.publisher = publisher;
        this.isBorrow = false; // 默认值
    }

    public Book(int id) {
        this.id = id;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author_id=" + author_id +
                ", publisher='" + publisher + '\'' +
                ", isBorrow=" + isBorrow +
                '}';
    }
}
