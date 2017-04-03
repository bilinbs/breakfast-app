package bilin.breakfastapp.vo;

import java.util.*;

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
    public long id;

    /**
     * 
     */
    public User customer;

    /**
     * 
     */
    public List<BreakfastSet> breakfastSets;

    /**
     * 
     */
    public double totalPrice;

    /**
     * 
     */
    public String paymentInfo;

    /**
     * 
     */
    public OrderStatus status;

    /**
     * 
     */
    public Date deliveryTime;

    /**
     * 
     */
    public String deliveryAddress;




    /**
     * @return
     */
    public long getId() {
        // TODO implement here
        return 0;
    }

    /**
     * @param value
     */
    public void setId(long value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public User getCustomer() {
        // TODO implement here
        return null;
    }

    /**
     * @param value
     */
    public void setCustomer(User value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<BreakfastSet> getBreakfastSets() {
        // TODO implement here
        return null;
    }

    /**
     * @param value
     */
    public void setBreakfastSets(List<BreakfastSet> value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getTotalPrice() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param value
     */
    public void setTotalPrice(double value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getPaymentInfo() {
        // TODO implement here
        return "";
    }

    /**
     * @param value
     */
    public void setPaymentInfo(String value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Date getDeliveryTime() {
        // TODO implement here
        return null;
    }

    /**
     * @param value
     */
    public void setDeliveryTime(Date value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getDeliveryAddress() {
        // TODO implement here
        return "";
    }

    /**
     * @param value
     */
    public void setDeliveryAddress(String value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public OrderStatus getStatus() {
        // TODO implement here
        return null;
    }

    /**
     * @param value
     */
    public void setStatus(OrderStatus value) {
        // TODO implement here
    }

}