package project;

import java.util.Date;

public class Item {
    private int id;
    private int authorId;
    private Date endDate;
    private String description;
    private String title;
    private boolean closed;

    public Item() {
        // Default constructor
    }

    // Constructor with all attributes
    public Item(int id, int authorId, Date endDate, String description, String title, boolean closed) {
        this.id = id;
        this.authorId = authorId;
        this.endDate = endDate;
        this.description = description;
        this.title = title;
        this.closed = closed;
    }

    // Getters and setters for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
