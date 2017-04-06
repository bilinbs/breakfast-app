package com.bilin.breakfastapp.service;

import java.util.*;

import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.Order;
import com.bilin.breakfastapp.vo.ServingStyle;

/**
 * 
 */
public interface OrderService {

    /**
     * @return 
     * @throws ServiceException 
     * 
     */
    public Order createOrder(Order order) throws ServiceException;

    /**
     * 
     */
    public void confirmOrder();

    /**
     * 
     */
    public void updateOrder();
    
    public List<BreakfastSet> getAllBfSets() throws ServiceException;

    public BreakfastSet getBfSetById(long bfSetId) throws ServiceException;

    public List<ServingStyle> getServingStylesForBFSet(long bfSetId) throws ServiceException;
    
    public List<ServingStyle> getAllServingStyles() throws ServiceException;

    public ServingStyle getServingStyleById(long sstyleId) throws ServiceException;

    public List<Item> getAllItems() throws ServiceException;

    public Item getItemById(Long id) throws ServiceException;

    public Order calculatePrice(Order order) throws ServiceException;

}