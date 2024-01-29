package model;

import java.util.Date;

public class Item {
    private int id;
    private int authorId;
    private Date endDate;
    private String description;
    private String title;
    private boolean closed;
    private float maxAmount;
    private int totalNo;
    private String imageUrl;

    public Item() {
        // Default constructor
    }

    // Constructor with all attributes
    public Item(int id, int authorId, Date endDate, String description, String title, boolean closed, float maxAmount, int totalNo, String imageUrl) {
        this.id = id;
        this.authorId = authorId;
        this.endDate = endDate;
        this.description = description;
        this.title = title;
        this.closed = closed;
        this.maxAmount = maxAmount;
        this.totalNo = totalNo;
        this.imageUrl = imageUrl;
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
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    public float getMaxAmount() {
        return this.maxAmount;
    }
    public void setMaxAmount(float maxAmount) {
        this.maxAmount = maxAmount;
    }
    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getTotalNo() {
        return this.totalNo;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
}
