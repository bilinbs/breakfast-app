package com.bilin.breakfastapp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.OrderDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.OrderService;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.Order;
import com.bilin.breakfastapp.vo.OrderStatus;
import com.bilin.breakfastapp.vo.ServingStyle;

@Service
public class OrderServiceImpl implements OrderService {

    private BreakfastSetDAO bfsetDao = BreakfastSetDAO.getInstance();
    
    private ServingStyleDAO ssDao = ServingStyleDAO.getInstance();
    
    private ItemDAO itemDao = ItemDAO.getInstance();
    
    private OrderDAO orderDao = OrderDAO.getInstance();
    
    @Override
    public Order createOrder(Order order) throws ServiceException{
        
        order.setStatus(OrderStatus.NEW);
        try {
            long orderId = orderDao.insert(order);
            order.setId(orderId);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Exception while creating order", e);
        }
        return order;
    }

    @Override
    public void confirmOrder() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOrder() {
        // TODO Auto-generated method stub

    }
    
   
    public List<BreakfastSet> getAllBfSets() throws ServiceException{
        List<BreakfastSet> result;
        try {
            result = bfsetDao.getAll(true);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting all bf sets", e);
            
        }
        return result;
    }

    @Override
    public BreakfastSet getBfSetById(long bfSetId) throws ServiceException {
        BreakfastSet result;
        try{
            result = bfsetDao.getById(bfSetId);
        }catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting bf set " + bfSetId, e);
            
        }
        return result;
    }

    @Override
    public List<ServingStyle> getServingStylesForBFSet(long bfSetId)
            throws ServiceException {
        List<ServingStyle> result;
        try{
            result = ssDao.getServingStylesForBFSet(bfSetId);
        }catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting styles bf set " + bfSetId, e);
            
        }
        return result;
    }

    @Override
    public ServingStyle getServingStyleById(long sstyleId)
            throws ServiceException {
        ServingStyle result;
        try{
            result =  ssDao.getById(sstyleId);
        }catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting style" + sstyleId, e);
            
        }
        return result;
    }

    @Override
    public List<Item> getAllItems() throws ServiceException {
        try {
            List<Item> items = itemDao.getAll();
            return items;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting all items", e);
        }
    }

    @Override
    public Item getItemById(Long id) throws ServiceException {
        try {
            Item item = itemDao.getById(id);
            return item;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Execption while getting all items", e);
        }
    }

    @Override
    public Order calculatePrice(Order order) throws ServiceException {
        double totalPrice = 0;
        for(BreakfastSet bfSet : order.getBreakfastSets()){
            double bfSetPrice = 0;
            for(Map.Entry<Item,Integer> entry : bfSet.getItems().entrySet()){
                bfSetPrice +=  entry.getKey().getUnitPrice() * (Integer)entry.getValue();
            }
            bfSet.setPrice(bfSetPrice);
            totalPrice += bfSetPrice + bfSet.getServingStyle().getPrice();
        }
        order.setTotalPrice(totalPrice);
        return order;
    }

}
