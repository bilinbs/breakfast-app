package com.bilin.breakfastapp.service;

import java.util.*;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.OrderDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;

/**
 * 
 */
public interface AdminService {

    /**
     * 
     */
    public OrderDAO orderDAO = null;

    /**
     * 
     */
    public ServingStyleDAO servingStyleDAO = null;

    /**
     * 
     */
    public ItemDAO itemDAO = null;

    /**
     * 
     */
    public BreakfastSetDAO breakfastSetDAO = null;

    /**
     * 
     */
    public void addItem();

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
    public void addBFSet();

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
    public void addServingStyle();

    /**
     * 
     */
    public void modifyServingStyle();

    /**
     * 
     */
    public void removeServingStyle();

}