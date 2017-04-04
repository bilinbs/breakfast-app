package com.bilin.breakfastapp.vo;

import java.util.Date;
import java.util.List;

/**
 * 
 */
public class Order {

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * 
     */
    private long id;

    /**
     * 
     */
    private User customer;

    /**
     * 
     */
    private List<BreakfastSet> breakfastSets;

    /**
     * 
     */
    private double totalPrice;

    /**
     * 
     */
    private String paymentInfo;

    /**
     * 
     */
    private OrderStatus status;

    /**
     * 
     */
    private Date deliveryTime;

    /**
     * 
     */
    private String deliveryAddress;

    @Override
    public String toString() {
        return "Order [id=" + id + ", customer=" + customer + ", breakfastSets="
                + breakfastSets + ", totalPrice=" + totalPrice
                + ", paymentInfo=" + paymentInfo + ", status=" + status
                + ", deliveryTime=" + deliveryTime + ", deliveryAddress="
                + deliveryAddress + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<BreakfastSet> getBreakfastSets() {
        return breakfastSets;
    }

    public void setBreakfastSets(List<BreakfastSet> breakfastSets) {
        this.breakfastSets = breakfastSets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


}