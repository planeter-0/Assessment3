public class Book {
    private String name;
    private String author;
    private boolean isLent;

    public Book(String name, String author, boolean isLent) {
        this.name = name;
        this.author = author;
        this.isLent = isLent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isLent() {
        return isLent;
    }

    public void setLent(boolean lent) {
        isLent = lent;
    }
}
