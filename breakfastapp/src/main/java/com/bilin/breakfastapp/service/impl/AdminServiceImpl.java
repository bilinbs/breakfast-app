package com.bilin.breakfastapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;
import com.bilin.breakfastapp.dto.ItemQtyDto;
import com.bilin.breakfastapp.dto.ItemQtyListDto;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.AdminService;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.ServingStyle;

@Service
public class AdminServiceImpl implements AdminService {
    
    private ItemDAO itemDao = ItemDAO.getInstance();
    
    private ServingStyleDAO servingStyleDao = ServingStyleDAO.getInstance();
    
    private BreakfastSetDAO bfSetDao = BreakfastSetDAO.getInstance();


    @Override
    public void modifyItem() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeItem() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addBFSet() {
        // TODO Auto-generated method stub

    }

    @Override
    public void modifyBFSet() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeBFSet() {
        // TODO Auto-generated method stub

    }


    @Override
    public void modifyServingStyle() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeServingStyle() {
        // TODO Auto-generated method stub

    }
    
    public List<String> getAllServingUnits() throws ServiceException{
        try {
            return itemDao.getAllServingUnits();
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException("Exception while getting all serving units",e);
        }
    }

    @Override
    public Item addItem(String name, String desc, String servingUnit,
            double price) throws ServiceException {
        Item item =new Item();
        item.setName(name);
        item.setDescription(desc);
        item.setServingUnit(servingUnit);
        item.setUnitPrice(price);
        try {
            long itemId = itemDao.insert(item);
            item.setId(itemId);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Exception while inserting item", e);
        }
        return item;
    }

    @Override
    public ServingStyle addServingStyle(String name, String desc, double price)
            throws ServiceException {
        ServingStyle sstyle  = new ServingStyle();
        sstyle.setName(name);
        sstyle.setDescription(desc);
        sstyle.setPrice(price);
        try{
            long id = servingStyleDao.insert(sstyle);
            sstyle.setId(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Exception while inserting serving style", e);
        }
        return sstyle;
    }

    @Override
    public BreakfastSet addBFSet(String name, String desc, Double price,
            ItemQtyListDto itemQtyListDto, long[] sstyles) throws ServiceException {
        BreakfastSet bfSet = new BreakfastSet();
        bfSet.setName(name);
        bfSet.setDescription(desc);
        bfSet.setPrice(price);
        bfSet.setItems(new HashMap<Item,Integer>());
        for(ItemQtyDto itemQty: itemQtyListDto.getItemQtyList()){
            bfSet.getItems().put(new Item(itemQty.getId()), itemQty.getQty());
        }
        List<ServingStyle> servingStyles = new ArrayList<ServingStyle>();
        for(long styleId: sstyles){
            servingStyles.add(new ServingStyle(styleId));
        }
        try {
            long bfId = bfSetDao.insert(bfSet,true);
            bfSet.setId(bfId);
            
            servingStyleDao.insertServingStylesForBFSet(bfId, servingStyles);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Exception while inserting breakfast set", e);
        }
        return bfSet;
    }

}
