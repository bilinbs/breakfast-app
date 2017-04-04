package com.bilin.breakfastapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.bilin.breakfastapp.dao.Messages;
import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;
import com.bilin.breakfastapp.dao.impl.UserDAO;
import com.bilin.breakfastapp.exceptions.UtilException;
import com.bilin.breakfastapp.util.Tables.BREAKFAST_SETS;
import com.bilin.breakfastapp.util.Tables.ITEMS;
import com.bilin.breakfastapp.util.Tables.ORDERS;
import com.bilin.breakfastapp.util.Tables.SERVING_STYLES;
import com.bilin.breakfastapp.util.Tables.USERS;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.Order;
import com.bilin.breakfastapp.vo.OrderStatus;
import com.bilin.breakfastapp.vo.ServingStyle;
import com.bilin.breakfastapp.vo.User;

public class DBUtils {
    
    private DBUtils(){
        
    }
    public static Connection getConnection() throws UtilException {
        try {
            Class.forName("org.h2.Driver"); //$NON-NLS-1$
            String url = Messages.getString("database.connection_url"); //$NON-NLS-1$
            String userName = Messages.getString("database.username");
            String password = Messages.getString("database.password");
            Connection conn = DriverManager.getConnection(url, userName, password); //$NON-NLS-1$ //$NON-NLS-2$
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new UtilException("DB Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UtilException("Error in getting connection", e);
        }

    }

    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param bfSet
     * @param rs
     * @throws SQLException
     */
    public static BreakfastSet populateBfSet(BreakfastSet bfSet, ResultSet rs) throws UtilException {
        
        try {
            bfSet.setId(rs.getLong(BREAKFAST_SETS.ID));
            bfSet.setDescription(rs.getString(BREAKFAST_SETS.DESCRIPTION));
            bfSet.setName(rs.getString(BREAKFAST_SETS.NAME));
            bfSet.setPrice(rs.getDouble(BREAKFAST_SETS.PRICE));
            ServingStyle ss = ServingStyleDAO.getInstance().
                    getById(rs.getLong(BREAKFAST_SETS.SERVING_STYLE));
            Map<Item, Integer> items;
            items = ItemDAO.getInstance().getItemsForBFSet(bfSet.getId());
            bfSet.setServingStyle(ss);
            bfSet.setItems(items);
            return bfSet;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UtilException("Exception while populating Breakfast set", e);
        }
        
    }
    
    /**
     * @param item
     * @param rs
     * @throws UtilException 
     */
    public static Item populateItem(Item item, ResultSet rs) throws UtilException {
        try {
            item.setId(rs.getLong(ITEMS.ID));
            item.setDescription(rs.getString(ITEMS.DESCRIPTION));
            item.setName(rs.getString(ITEMS.NAME));
            item.setServingUnit(rs.getString(ITEMS.SERVINGUNIT));
            item.setUnitPrice(rs.getDouble(ITEMS.UNITPRICE));
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UtilException("Exception while populating Item", e);
        }
        
    }
    
    /**
     * 
     * @param servingStyle
     * @param rs
     * @return
     * @throws UtilException 
     * @throws SQLException 
     */
    public static ServingStyle populateServingStyle(ServingStyle servingStyle, ResultSet rs) throws UtilException  {
        try {
            servingStyle.setId(rs.getLong(SERVING_STYLES.ID));
            servingStyle.setName(rs.getString(SERVING_STYLES.NAME));
            servingStyle.setDescription(rs.getString(SERVING_STYLES.DESCRIPTION));
            servingStyle.setPrice(rs.getDouble(SERVING_STYLES.PRICE));
            return servingStyle;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UtilException("Exception while populating Serving Style", e);
        }
        
    }
    
    /**
     * 
     * @param user
     * @param rs
     * @return
     * @throws UtilException
     */
    public static User populateUser(User user, ResultSet rs) throws UtilException {
        try{
            user.setUserId(rs.getString(USERS.USERID));
            user.setEmail(rs.getString(USERS.EMAIL));
            user.setName(rs.getString(USERS.NAME));
            user.setAddress(rs.getString(USERS.ADDRESS));
            user.setPassword(rs.getString(USERS.PASSWORD));
            user.setPhoneNo(rs.getString(USERS.PHONE_NO));
            user.setAdmin(rs.getBoolean(USERS.IS_ADMIN));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UtilException("Exception while populating User", e);
        }
        
    }
    
    /**
     * 
     * @param order
     * @param rs
     * @return
     * @throws UtilException
     */
    public static Order populateOrder(Order order, ResultSet rs) throws UtilException {
        try{
            order.setId(rs.getLong(ORDERS.ID));
            order.setBreakfastSets(BreakfastSetDAO.getInstance().getBFSetsForOrder(order.getId()));
            order.setCustomer(UserDAO.getInstance().getById(rs.getString(ORDERS.CUSTOMER)));
            order.setDeliveryAddress(rs.getString(ORDERS.DELIVERY_ADDRESS));
            order.setTotalPrice(rs.getDouble(ORDERS.TOTAL_PRICE));
            order.setPaymentInfo(rs.getString(ORDERS.PAYMENT_INFO));
            order.setStatus(OrderStatus.valueOf(rs.getString(ORDERS.ORDER_STATUS)));
            order.setDeliveryTime(rs.getTimestamp(ORDERS.DELIVERY_TIME));
            return order;
        } catch (Exception e){
            e.printStackTrace();
            throw new UtilException("Exception while populating Order", e);
        }
    }

}
