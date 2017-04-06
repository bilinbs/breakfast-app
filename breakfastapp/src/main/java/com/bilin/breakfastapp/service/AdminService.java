package com.bilin.breakfastapp.service;


import java.util.List;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.OrderDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;
import com.bilin.breakfastapp.dto.ItemQtyListDto;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.ServingStyle;

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
     * @throws ServiceException 
     * 
     */
    public Item addItem(String name, String desc, String servingUnit, double price) throws ServiceException;

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
    public ServingStyle addServingStyle(String name, String desc, double Price) throws ServiceException;

    /**
     * 
     */
    public void modifyServingStyle();

    /**
     * 
     */
    public void removeServingStyle();
    
    public List<String> getAllServingUnits() throws ServiceException;

    public BreakfastSet addBFSet(String name, String desc, Double price,
            ItemQtyListDto itemQtyListDto, long[] sstyles) throws ServiceException;

}