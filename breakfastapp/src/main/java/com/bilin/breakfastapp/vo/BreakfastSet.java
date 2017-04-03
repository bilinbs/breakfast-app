package com.bilin.breakfastapp.vo;

import java.util.*;

/**
 * 
 */
public class BreakfastSet {

    /**
     * Default constructor
     */
    public BreakfastSet() {
    }

    /**
     * 
     */
    public long id;

    /**
     * 
     */
    public String name;

    /**
     * 
     */
    public String description;

    /**
     * 
     */
    public Map<Item,Integer> items;

    /**
     * 
     */
    public ServingStyle servingStyle;

    /**
     * 
     */
    public double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    public ServingStyle getServingStyle() {
        return servingStyle;
    }

    public void setServingStyle(ServingStyle servingStyle) {
        this.servingStyle = servingStyle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BreakfastSet [id=" + id + ", name=" + name + ", description="
                + description + ", items=" + items + ", servingStyle="
                + servingStyle + ", price=" + price + "]";
    }






}