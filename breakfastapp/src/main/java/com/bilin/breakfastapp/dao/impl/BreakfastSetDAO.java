package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.BREAKFAST_SETS;
import com.bilin.breakfastapp.util.Tables.BREAKFAST_SETS_ORDERS;
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
    public long insert(BreakfastSet t) throws DAOException {
        return insert(t, false);
    }

    public long insert(BreakfastSet t, boolean isTemplate) throws DAOException {
        long bfId;
        String sql =  "insert into " + BREAKFAST_SETS._name + "( " +
            BREAKFAST_SETS.NAME + ", " + BREAKFAST_SETS.DESCRIPTION + ", " +
            BREAKFAST_SETS.SERVING_STYLE + ", " + BREAKFAST_SETS.ISTEMPLATE + ", " +
            BREAKFAST_SETS.PRICE +
            ") values ( ?, ?, ?, ?, ?)";
        
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ){
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
                insertItemsForBfSet(bfId, t.getItems());
                return bfId;
            } else {
                throw new DAOException("No generated id while inserting breakfastset " + t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Error while inserting " + t, e);
        }
        
    }

    @Override
    public BreakfastSet update(BreakfastSet t) throws DAOException {
        String sql = "update " + BREAKFAST_SETS._name + " set " +
                BREAKFAST_SETS.NAME + " = ? , " +
                BREAKFAST_SETS.DESCRIPTION + " = ? , " +
                BREAKFAST_SETS.PRICE + " = ? , " +
                BREAKFAST_SETS.SERVING_STYLE +  " = ?";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setDouble(3, t.getPrice());
            if(null == t.getServingStyle()){
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setLong(4, t.getServingStyle().getId());
            }
            ps.executeUpdate();
            deleteItemsForBfSet(t.getId());
            insertItemsForBfSet(t.getId(), t.getItems());
        } catch(Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while updating breakfast set " + t.getId(), e);
        }
        return getById(t.id);
        
     }
    /**
     * 
     * @param id
     * @throws DAOException
     */
    private void deleteItemsForBfSet(long id) throws DAOException {
        String sql = "delete from " + ITEM_QUANTITIES._name + " where " + 
                ITEM_QUANTITIES.BF_SET_ID + " = ?";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while deleting items for breakfast set " + id, e);
        }
    }

    /**
     * @param bfId
     * @param itemMap
     * @throws SQLException
     * @throws DAOException 
     */
    public void insertItemsForBfSet(long bfId, Map<Item, Integer> itemMap) throws SQLException, DAOException {
        String sql = "insert into " + ITEM_QUANTITIES._name + "( " +
                ITEM_QUANTITIES.BF_SET_ID + ", " + ITEM_QUANTITIES.ITEM_ID + ", " +
                ITEM_QUANTITIES.QUANTITY + ") values (?,?,?)";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
        for(Map.Entry<Item, Integer> entry : itemMap.entrySet()){
            ps.setLong(1, bfId);
            ps.setLong(2, entry.getKey().getId());
            ps.setInt(3, entry.getValue());
            ps.addBatch();
        }
        int[] op = ps.executeBatch();
        System.out.println("Inserted items: " + op[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Error while inserting items for set " + bfId, e);
        }
    }

    @Override
    public void delete(BreakfastSet t) throws DAOException {
        throw new DAOException("unimplemented method");
        
    }


    /**
     * 
     * @param orderId
     * @return
     * @throws DAOException
     */
    public List<BreakfastSet> getBFSetsForOrder(long orderId) throws DAOException{
        String sql = "select * from " + BREAKFAST_SETS._name + ", " +
                    BREAKFAST_SETS_ORDERS._name + " where " +
                    BREAKFAST_SETS_ORDERS.BREAKFAST_SET_ID + " = " + BREAKFAST_SETS.ID +
                    " and " + BREAKFAST_SETS_ORDERS.ORDER_ID + " = ?";
        List<BreakfastSet> results = new ArrayList<BreakfastSet>();
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                results.add(DBUtils.populateBfSet(new BreakfastSet(), rs));
            }
            return results;
        } catch (Exception e){
            e.printStackTrace();
            throw new DAOException("Error while geting BF set for order" + orderId, e);
        }
    }
    
    public void addBFSetsForOrder(long orderId, List<Long> bfIds) throws DAOException {
        String sql = "insert into " + BREAKFAST_SETS_ORDERS._name + "(" +
                    BREAKFAST_SETS_ORDERS.BREAKFAST_SET_ID + ", " +
                    BREAKFAST_SETS_ORDERS.ORDER_ID + ") values (?,?)";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            for(Long bfId : bfIds){
                ps.setLong(1, bfId);
                ps.setLong(2, orderId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while inserting bf sets for order " + orderId, e);
        }
    }
    
}