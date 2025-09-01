package org.top.springwithdbexample.api;

public class OrderCreateData  {
    private String description;
    private Integer userId;

    public OrderCreateData() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}