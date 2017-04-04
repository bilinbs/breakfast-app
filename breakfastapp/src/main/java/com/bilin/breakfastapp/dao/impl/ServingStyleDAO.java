package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.BREAKFASTSET_SERVINGSTYLES;
import com.bilin.breakfastapp.util.Tables.SERVING_STYLES;
import com.bilin.breakfastapp.vo.ServingStyle;

/**
 * 
 */
public class ServingStyleDAO implements BaseDAO<ServingStyle> {

    private ServingStyleDAO() {
    }
    
    private static ServingStyleDAO instance;
    
    public static ServingStyleDAO getInstance(){
        if(instance == null){
            instance = new ServingStyleDAO();
        }
        return instance;
    }
    @Override
    public ServingStyle getById(long id) throws DAOException {
        String sql = "select * from " + SERVING_STYLES._name + " where " + SERVING_STYLES.ID
                + " = ?";
        ServingStyle result = new ServingStyle();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DBUtils.populateServingStyle(result, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Serving style::getById:" + id, e );
        }
        return result;
    }

    @Override
    public List<ServingStyle> getAll() throws DAOException {
        String sql = "select * from " + SERVING_STYLES._name;
        List<ServingStyle> results = new ArrayList<ServingStyle>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                results.add(DBUtils.populateServingStyle(new ServingStyle(), rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing ServingStyle::getAll", e);
        }
        return results;
    }

    @Override
    public long insert(ServingStyle servingStyle) throws DAOException {
        String sql = "insert into " + SERVING_STYLES._name + " (" + SERVING_STYLES.NAME + ","
                + SERVING_STYLES.DESCRIPTION + ", " + SERVING_STYLES.PRICE + ") values (?,?,?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, servingStyle.getName());
            ps.setString(2, servingStyle.getDescription());
            ps.setDouble(3, servingStyle.getPrice());
            ps.execute(sql);
            ResultSet genKey = ps.getGeneratedKeys();
            if(genKey.next()){
                return genKey.getLong(1);
            }else {
                throw new DAOException("No generated id while inserting servingStyle ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::insert" + servingStyle, e);
        }
    }

    @Override
    public void delete(ServingStyle t) throws DAOException {
        throw new DAOException("Method not implemented");
        
    }

    @Override
    public ServingStyle update(ServingStyle t) throws DAOException {
        String sql = "update " + SERVING_STYLES._name + " set " +
                SERVING_STYLES.NAME + " = ?, " +
                SERVING_STYLES.DESCRIPTION + " = ?, " +
                SERVING_STYLES.PRICE + " = ?" + " where " + 
                SERVING_STYLES.ID + " = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setDouble(3, t.getPrice());
            ps.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing ServingStyle::update" + t, e);
        }
        return t;
    }
    
    /**
     * 
     * @param bfId
     * @return
     * @throws DAOException
     */
    public List<ServingStyle> getServingStylesForBFSet(long bfId) throws DAOException{
        List<ServingStyle> results = new ArrayList<>();
        String sql = "select * from " + SERVING_STYLES._name + ", " + BREAKFASTSET_SERVINGSTYLES._name +
                " where " + SERVING_STYLES.ID + "=" + BREAKFASTSET_SERVINGSTYLES.SERVING_STYLE_ID +
                " and " + BREAKFASTSET_SERVINGSTYLES.BF_SET_ID + " = ?";
        try(Connection conn =DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                results.add(DBUtils.populateServingStyle(new ServingStyle(), rs));
            }
            return results;
        } catch(Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while getting serving styles for bfset" + bfId, e);
        }
        
    }
    
    public void insertServingStylesForBFSet(long bfId, List<ServingStyle> servingStyles) throws DAOException{
        String sql = "insert into " + BREAKFASTSET_SERVINGSTYLES._name + "( " +
                BREAKFASTSET_SERVINGSTYLES.BF_SET_ID + ", " + BREAKFASTSET_SERVINGSTYLES.SERVING_STYLE_ID + ") values (?,?)";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            for(ServingStyle style : servingStyles){
                ps.setLong(1, bfId);
                ps.setLong(2, style.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        }catch (Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while inserting serving styles for bfset " + bfId,e );
        }
                
    }
    
}