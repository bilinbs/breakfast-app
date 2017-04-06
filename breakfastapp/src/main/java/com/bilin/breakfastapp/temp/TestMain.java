package com.bilin.breakfastapp.temp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.OrderDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.Order;
import com.bilin.breakfastapp.vo.OrderStatus;

public class TestMain {

    public static void main(String[] args) throws DAOException {
        BreakfastSetDAO bfDao = BreakfastSetDAO.getInstance();
        /*Map<Item, Integer> itemMap = *//* List<BreakfastSet> bf = bfDao.getAll();*/
        /*for(Map.Entry<Item, Integer> entry : itemMap.entrySet()){
            System.out.println(entry.getKey().toString() + "::->" + entry.getValue());
            
        }
        
        
*/  /*BreakfastSet bf = new BreakfastSet();
    bf.setName("Heavy");
    bf.setDescription("Simple, heavy for the hungry");
    bf.setPrice(110);
    Map<Item, Integer> itemMap = new HashMap<Item, Integer>();
    Item item1 = new Item();
    item1.setId(1);
    itemMap.put(item1, 3);
    Item item2 = new Item();
    item2.setId(2);
    itemMap.put(item2, 6);
    bf.setItems(itemMap);
    BreakfastSet bf2 = new BreakfastSet();
    bf2.setName("Light");
    bf2.setDescription("Simple, Light for the hungry");
    bf2.setPrice(90);
    Map<Item, Integer> itemMap2 = new HashMap<Item, Integer>();
    itemMap2.put(item1, 2);
    itemMap2.put(item2, 3);
    bf2.setItems(itemMap2);
    Order order = new Order();
    order.setDeliveryAddress("kalamassery");
    order.setPaymentInfo("62452656954666261354");
    Date delDate = new Date();
    delDate.setDate(6);
    delDate.setHours(9);
    delDate.setMinutes(30);
    order.setDeliveryTime(delDate);
    order.setStatus(OrderStatus.NEW);
    List<BreakfastSet> breakfastSets = new ArrayList<BreakfastSet>();
    breakfastSets.add(bf);
    breakfastSets.add(bf2);
    order.setBreakfastSets(breakfastSets);
    OrderDAO.getInstance().insert(order);*/
        /*Order order = OrderDAO.getInstance().getById(4);
        order.setTotalPrice(230);
        order.setStatus(OrderStatus.CONFIRMED);
        OrderDAO.getInstance().update(order);
        System.out.println(order);*/
        BreakfastSet bfs = BreakfastSetDAO.getInstance().getById(4);
        List<Item> items = ItemDAO.getInstance().getAll();
        for(Item item : items){
            System.out.println(item.getName() + "::::::" + bfs.getItems().get(item));
        }
        System.out.println(bfs);
    }

}
