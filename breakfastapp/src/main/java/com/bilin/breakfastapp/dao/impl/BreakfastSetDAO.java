package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.exceptions.UtilException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.BREAKFAST_SETS;
import com.bilin.breakfastapp.util.Tables.ITEMS;
import com.bilin.breakfastapp.util.Tables.ITEM_QUANTITIES;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;

/**
 * 
 */
public class BreakfastSetDAO implements BaseDAO<BreakfastSet> {
    
    private static BreakfastSetDAO instance;
    
    private BreakfastSetDAO (){
        
    }
    
    public static BreakfastSetDAO getInstance(){
        if(instance == null){
            instance = new BreakfastSetDAO();
        }
        return instance;
    }

    @Override
    public BreakfastSet getById(long id) throws DAOException {
        String sql = "select * from " + BREAKFAST_SETS._name + " where " + BREAKFAST_SETS.ID
                + " = ?";
        BreakfastSet result = new BreakfastSet();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DBUtils.populateBfSet(result, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing BreakfastSet::getById:"+id, e);
        }
        return result;
    }


    
    public Map<Item, Integer> getItemsForBFSet(long bfSetId) throws DAOException {
        Map<Item, Integer> itemMap = new HashMap<Item,Integer>();
        String sql = "select * from " + ITEM_QUANTITIES._name + ", " + ITEMS._name +
                " where " + ITEM_QUANTITIES.ITEM_ID + " = " + ITEMS.ID + " and " + ITEM_QUANTITIES.BF_SET_ID + " = ?";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setLong(1, bfSetId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item item = DBUtils.populateItem(new Item(), rs);
                int qty = rs.getInt(ITEM_QUANTITIES.QUANTITY);
                itemMap.put(item, qty);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing BreakfastSet::getItemsForBFSet", e);
        }
        return itemMap;
    }

    
    @Override
    public List<BreakfastSet> getAll() throws DAOException {
        return getAll(false);
    }

    public List<BreakfastSet> getAllTemplates() throws DAOException{
        return getAll(true);
    }
    
    public List<BreakfastSet> getAll(boolean isTemplates) throws DAOException{
        String sql = "select * from " + BREAKFAST_SETS._name +
                (isTemplates? BREAKFAST_SETS.ISTEMPLATE + " = true" : "");
        List<BreakfastSet> results = new ArrayList<BreakfastSet>();
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                results.add(DBUtils.populateBfSet(new BreakfastSet(), rs));
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing BreakfastSet::getAll", e);
        }
        return results;
    }
    @Override
    public void insert(BreakfastSet t) throws DAOException {
        insert(t, false);
    }

    public void insert(BreakfastSet t, boolean isTemplate) throws DAOException {
        long bfId;
        String sql =  "insert into " + BREAKFAST_SETS._name + "( " +
            BREAKFAST_SETS.NAME + ", " + BREAKFAST_SETS.DESCRIPTION + ", " +
            BREAKFAST_SETS.SERVING_STYLE + ", " + BREAKFAST_SETS.ISTEMPLATE + ", " +
            BREAKFAST_SETS.PRICE +
            ") values ( ?, ?, ?, ?, ?)";
        Map<Item, Integer> itemMap =t.getItems();
        String sql2 = "insert into " + ITEM_QUANTITIES._name + "( " +
                ITEM_QUANTITIES.BF_SET_ID + ", " + ITEM_QUANTITIES.ITEM_ID + ", " +
                ITEM_QUANTITIES.QUANTITY + ") values (?,?,?)";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps2 = conn.prepareStatement(sql2);){
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            if(null == t.getServingStyle()){
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setLong(3, t.getServingStyle().getId());
            }
            ps.setBoolean(4, isTemplate);
            ps.setDouble(5, t.getPrice());
            ps.execute();
            ResultSet genKey = ps.getGeneratedKeys();
            if(genKey.next()){
                bfId = genKey.getLong(1);
                for(Map.Entry<Item, Integer> entry : itemMap.entrySet()){
                    ps2.setLong(1, bfId);
                    ps2.setLong(2, entry.getKey().getId());
                    ps2.setInt(3, entry.getValue());
                    ps2.addBatch();
                }
                int[] op = ps2.executeBatch();
            } else {
                throw new DAOException("No generated id while inserting breakfastset " + t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Error while inserting " + t, e);
        }
        
    }

    @Override
    public void delete(BreakfastSet t) throws DAOException {
        throw new DAOException("unimplemented method");
        
    }

    @Override
    public BreakfastSet update(BreakfastSet t) {
        // TODO Auto-generated method stub
        return null;
        
    }
    
}