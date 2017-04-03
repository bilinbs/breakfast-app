package com.bilin.breakfastapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.bilin.breakfastapp.dao.Messages;
import com.bilin.breakfastapp.dao.impl.BreakfastSetDAO;
import com.bilin.breakfastapp.dao.impl.ServingStyleDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.exceptions.UtilException;
import com.bilin.breakfastapp.util.Tables.BREAKFAST_SETS;
import com.bilin.breakfastapp.util.Tables.ITEMS;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.ServingStyle;

public class DBUtils {
    
    private DBUtils(){
        
    }
    public static Connection getConnection() throws UtilException {
        try {
            Class.forName("org.h2.Driver"); //$NON-NLS-1$
            String url = Messages.getString("database.connection_url"); //$NON-NLS-1$
            Connection conn = DriverManager.getConnection(url, Messages.getString("database.username"), Messages.getString("database.password")); //$NON-NLS-1$ //$NON-NLS-2$
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
            items = BreakfastSetDAO.getInstance().getItemsForBFSet(bfSet.getId());
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
     * @throws SQLException
     */
    public static Item populateItem(Item item, ResultSet rs) throws SQLException {
        item.setId(rs.getLong(ITEMS.ID));
        item.setDescription(rs.getString(ITEMS.DESCRIPTION));
        item.setName(rs.getString(ITEMS.NAME));
        item.setServingUnit(rs.getString(ITEMS.SERVINGUNIT));
        item.setUnitPrice(rs.getDouble(ITEMS.UNITPRICE));
        return item;
    }

}
