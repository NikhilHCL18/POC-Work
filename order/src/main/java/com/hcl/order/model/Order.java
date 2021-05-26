package com.hcl.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Order")
public class Order {
    @Id
    String id;

    private String restaurantId;
    private List<ItemQuantity> items;
    private int totalPrice;
    private long orderTime;
    private String specialNote;
    private long deliveryTime;

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<ItemQuantity> getItems() {
        return items;
    }

    public void setItems(List<ItemQuantity> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", orderTime=" + orderTime +
                ", specialNote='" + specialNote + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", userInfo=" + userInfo +
                '}';
    }
}
