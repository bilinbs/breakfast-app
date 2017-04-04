package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.ORDERS;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Order;

/**
 * 
 */
public class OrderDAO implements BaseDAO<Order> {

    /**
     * Default constructor
     */
    private OrderDAO() {
    }

    private static OrderDAO instance;
    
    public static OrderDAO getInstance(){
        if(instance == null){
            instance = new OrderDAO();
        }
        return instance;
    }
    @Override
    public Order getById(long id) throws DAOException {
        String sql = "select * from " + ORDERS._name + " where " + ORDERS.ID + " = ?" ;
        Order result = new Order();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DBUtils.populateOrder(result, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getById:" + id, e );
        }
        return result;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        String sql = "select * from " + ORDERS._name ;
        List<Order> results = new ArrayList<Order>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                results.add(DBUtils.populateOrder(new Order(), rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getAll", e );
        }
        return results;
    }

    @Override
    public long insert(Order t) throws DAOException {
        long orderId;
        String sql = "insert into " + ORDERS._name + "( " +
                    ORDERS.ORDER_STATUS + ", " + ORDERS.DELIVERY_TIME + ", " +
                    ORDERS.DELIVERY_ADDRESS + ", " + ORDERS.CUSTOMER + ", " + 
                    ORDERS.PAYMENT_INFO + ", " + ORDERS.TOTAL_PRICE + ") values (?, ?, ?, ?, ?, ?)";
        try(Connection conn =  DBUtils.getConnection();
                PreparedStatement ps  = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            ps.setString(1, t.getStatus().name());
            ps.setTimestamp(2, new Timestamp(t.getDeliveryTime().getTime()));
            ps.setString(3, t.getDeliveryAddress());
            if(t.getCustomer() == null){
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, t.getCustomer().getUserId());
            }
            ps.setString(5, t.getPaymentInfo());
            ps.setDouble(6, t.getTotalPrice());
            ps.execute();
            ResultSet genKey = ps.getGeneratedKeys();
            if(genKey.next()){
                orderId = genKey.getLong(1);
            } else {
                throw new DAOException("no generated key in inserting order");
            }
            List<Long> bfIds = new ArrayList<Long>();
            for(BreakfastSet set : t.getBreakfastSets()){
                bfIds.add(BreakfastSetDAO.getInstance().insert(set));
            }
            BreakfastSetDAO.getInstance().addBFSetsForOrder(orderId, bfIds);
            
        } catch (Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while inserting order", e);
        }
        return orderId;
    }

    
    @Override
    public void delete(Order t) throws DAOException {
        throw new DAOException("unimplemented method");
        
    }

    @Override
    public Order update(Order order) throws DAOException {
        String sql = "update " + ORDERS._name + " set " +
                    ORDERS.ORDER_STATUS + " = ?," +
                    ORDERS.CUSTOMER + " = ?, " +
                    ORDERS.DELIVERY_ADDRESS + " = ?, " +
                    ORDERS.PAYMENT_INFO + " = ?, " +
                    ORDERS.TOTAL_PRICE + " = ?, " +
                    ORDERS.DELIVERY_TIME + " = ? " + " where " +
                    ORDERS.ID + " = ?";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, order.getStatus().name());
            if(order.getCustomer() == null){
                ps.setNull(2, java.sql.Types.VARCHAR);
            } else {
                ps.setString(2, order.getCustomer().getUserId());
            }
            ps.setString(3, order.getDeliveryAddress());
            ps.setString(4, order.getPaymentInfo());
            ps.setDouble(5, order.getTotalPrice());
            ps.setTimestamp(6, new Timestamp(order.getDeliveryTime().getTime()));
            ps.setLong(7, order.getId());
            ps.execute();
        } catch(Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while updating order " + order.getId(), e);
        }
        return getById(order.getId());
    }

}