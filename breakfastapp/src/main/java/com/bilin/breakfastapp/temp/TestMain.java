package com.bilin.breakfastapp.temp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;

public class TestMain {

    public static void main(String[] args) throws DAOException {
        BreakfastSetDAO bfDao = BreakfastSetDAO.getInstance();
        /*Map<Item, Integer> itemMap = *//* List<BreakfastSet> bf = bfDao.getAll();*/
        /*for(Map.Entry<Item, Integer> entry : itemMap.entrySet()){
            System.out.println(entry.getKey().toString() + "::->" + entry.getValue());
            
        }
        
        
*/  BreakfastSet bf = new BreakfastSet();
    bf.setName("Heavy");
    bf.setDescription("Simple, heavy for the hungry");
    bf.setPrice(90);
    Map<Item, Integer> itemMap = new HashMap<Item, Integer>();
    Item item1 = new Item();
    item1.setId(1);
    itemMap.put(item1, 2);
    Item item2 = new Item();
    item2.setId(2);
    itemMap.put(item2, 6);
    bf.setItems(itemMap);
    bfDao.insert(bf, true);
    }

}
