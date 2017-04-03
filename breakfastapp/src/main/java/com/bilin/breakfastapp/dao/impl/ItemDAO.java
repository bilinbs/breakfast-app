package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.ITEMS;

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
    public void insert(Item item) throws DAOException {
        String sql = "insert into " + ITEMS._name + " (" + ITEMS.NAME + ","
                + ITEMS.SERVINGUNIT + ", " + ITEMS.DESCRIPTION + ", "
                + ITEMS.UNITPRICE + ") values (?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getServingUnit());
            ps.setString(3, item.getDescription());
            ps.setDouble(3, item.getUnitPrice());
            ps.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::insert" + item, e);
        }

    }

    @Override
    public void delete(Item item) throws DAOException {
        throw new DAOException("Unimplemented Method");
    }

    @Override
    public Item update(Item item) throws DAOException {
        String sql = "update " + ITEMS._name + " set " +
                ITEMS.NAME + " = ?" +
                ITEMS.DESCRIPTION + " = ?" +
                ITEMS.SERVINGUNIT + " = ?" +
                ITEMS.UNITPRICE + " = ?" + " where " + 
                ITEMS.ID + " = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getServingUnit());
            ps.setDouble(3, item.getUnitPrice());
            ps.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::update" + item, e);
        }
        return item;
    }

}