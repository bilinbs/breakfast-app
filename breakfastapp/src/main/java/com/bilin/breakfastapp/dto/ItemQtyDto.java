package com.bilin.breakfastapp.dto;


public class ItemQtyDto {
    
    private String name;
    
    private long id;
    
    private int qty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ItemQtyDto(String name, long id, int qty) {
        super();
        this.name = name;
        this.id = id;
        this.qty = qty;
    }

    public ItemQtyDto(){
    }

}
