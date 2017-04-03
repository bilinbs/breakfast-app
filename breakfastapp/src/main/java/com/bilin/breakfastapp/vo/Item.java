package com.bilin.breakfastapp.vo;

import java.util.*;

/**
 * 
 */
public class Item {

    /**
     * Default constructor
     */
    public Item() {
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
    public double unitPrice;

    /**
     * 
     */
    public String servingUnit;

    /**
     * 
     */
    public String description;

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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice
                + ", servingUnit=" + servingUnit + ", description="
                + description + "]";
    }


   
}