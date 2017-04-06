package com.bilin.breakfastapp.controller;

import org.springframework.ui.ModelMap;

/**
 * 
 */
public interface AdminController {

    /**
     * 
     */
    public String addItem(ModelMap model);

    /**
     * 
     */
    public void modifyItem();

    /**
     * 
     */
    public void removeItem();

    /**
     * 
     */
    public String addBFSet(ModelMap model);

    /**
     * 
     */
    public void modifyBFSet();

    /**
     * 
     */
    public void removeBFSet();

    /**
     * 
     */
    public String addServingStyle(ModelMap model);

    /**
     * 
     */
    public void modifyServingStyle();

    /**
     * 
     */
    public void removeServingStyle();

    /**
     * 
     */
    public void manageOrder();

}