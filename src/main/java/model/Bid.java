package model;

import java.util.Date;

public class Bid {
    private int id;
    private Date date;
    private int amount;
    private int bidderId;
    private int itemId;
    private String statement;
    private boolean winner;
    private boolean paidClient;
    private boolean paidBidder;
    private String itemTitle;
    private String image_url;
    private String bidderName;

    public Bid() {
        // Default constructor
    }

    // Constructor with all attributes
    public Bid(int id, Date date, int amount, int bidderId, int itemId, String statement,
               boolean winner, boolean paidClient, boolean paidBidder, String image_url, String bidderName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.statement = statement;
        this.winner = winner;
        this.paidClient = paidClient;
        this.paidBidder = paidBidder;
        this.image_url = image_url;
        this.bidderName = bidderName;
    }

    // Getters and setters for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isPaidClient() {
        return paidClient;
    }

    public void setPaidClient(boolean paidClient) {
        this.paidClient = paidClient;
    }

    public boolean isPaidBidder() {
        return paidBidder;
    }

    public void setPaidBidder(boolean paidBidder) {
        this.paidBidder = paidBidder;
    }

    public String getItemTitle() {
        return itemTitle;
    }
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
    public String getImageUrl() {
        return this.image_url;
    }
    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }
    public String getBidderName() {
        return this.bidderName;
    }
    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }
}
