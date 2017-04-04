package com.bilin.breakfastapp.vo;


/**
 * 
 */
public class ServingStyle {

    /**
     * Default constructor
     */
    public ServingStyle() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServingStyle [id=" + id + ", name=" + name + ", description="
                + description + ", price=" + price + "]";
    }



}