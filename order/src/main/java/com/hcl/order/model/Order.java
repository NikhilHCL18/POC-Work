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
    private String customerId;
    private long unitPreparationTime;
    private String orderStatus;
    private long driverId;

    private UserInfo userInfo;
    private AddressInfo addressInfo;


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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getUnitPreparationTime() {
        return unitPreparationTime;
    }

    public void setUnitPreparationTime(long unitPreparationTime) {
        this.unitPreparationTime = unitPreparationTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
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
                ", customerId='" + customerId + '\'' +
                ", unitPreparationTime=" + unitPreparationTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", driverId=" + driverId +
                ", userInfo=" + userInfo +
                ", addressInfo=" + addressInfo +
                '}';
    }
}
