package com.bilin.breakfastapp.vo;


/**
 * 
 */
public class Item {

    /**
     * Default constructor
     */
    public Item() {
    }

    public Item(long id){
        this.id = id;
    }
    /**
     * 
     */
    private long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private double unitPrice;

    /**
     * 
     */
    private String servingUnit;

    /**
     * 
     */
    private String description;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }


   
}