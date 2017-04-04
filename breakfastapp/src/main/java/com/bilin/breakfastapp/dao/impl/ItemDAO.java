package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.ITEMS;
import com.bilin.breakfastapp.util.Tables.ITEM_QUANTITIES;
import com.bilin.breakfastapp.vo.Item;

/**
 * 
 */
public class ItemDAO implements BaseDAO<Item> {

    private static ItemDAO instance;
    
    public static ItemDAO getInstance(){
        if(instance == null){
            instance = new ItemDAO();
        }
        return instance;
    }
    /**
     * Default constructor
     */
    private ItemDAO() {
    }

    
    @Override
    public Item getById(long id) throws DAOException {
        String sql = "select * from " + ITEMS._name + " where " + ITEMS.ID
                + " = ?";
        Item result = new Item();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                DBUtils.populateItem(result, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getById:" + id, e );
        }
        return result;
    }


    @Override
    public List<Item> getAll() throws DAOException {
        String sql = "select * from " + ITEMS._name;
        List<Item> results = new ArrayList<Item>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                results.add(DBUtils.populateItem(new Item(), rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getAll", e);
        }
        return results;
    }

    @Override
    public long insert(Item item) throws DAOException {
        String sql = "insert into " + ITEMS._name + " (" + ITEMS.NAME + ","
                + ITEMS.SERVINGUNIT + ", " + ITEMS.DESCRIPTION + ", "
                + ITEMS.UNITPRICE + ") values (?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getServingUnit());
            ps.setString(3, item.getDescription());
            ps.setDouble(4, item.getUnitPrice());
            ps.execute(sql);
            ResultSet genKey =  ps.getGeneratedKeys();
            if(genKey.next()){
                return genKey.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::insert" + item, e);
        }
        return 0;
    }

    @Override
    public void delete(Item item) throws DAOException {
        throw new DAOException("Unimplemented Method");
    }

    @Override
    public Item update(Item item) throws DAOException {
        String sql = "update " + ITEMS._name + " set " +
                ITEMS.NAME + " = ?, " +
                ITEMS.DESCRIPTION + " = ?, " +
                ITEMS.SERVINGUNIT + " = ?, " +
                ITEMS.UNITPRICE + " = ?" + " where " + 
                ITEMS.ID + " = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getServingUnit());
            ps.setDouble(4, item.getUnitPrice());
            ps.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::update" + item, e);
        }
        return item;
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
}