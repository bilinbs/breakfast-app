package com.bilin.breakfastapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.util.DBUtils;
import com.bilin.breakfastapp.util.Tables.USERS;
import com.bilin.breakfastapp.vo.User;

/**
 * 
 */
public class UserDAO implements BaseDAO<User> {

    /**
     * Default constructor
     */
    private UserDAO() {
    }

    private static UserDAO instance;
    
    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }
    
    public User getById(String id) throws DAOException {
        if(id == null){
            return null;
        }
        String sql = "select * from " + USERS._name + " where " + USERS.USERID
                + " = ?";
        User result = new User();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DBUtils.populateUser(result, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getById:" + id, e );
        }
        return result;
    }

    @Override
    public List<User> getAll() throws DAOException {
        String sql = "select * from " + USERS._name;
        List<User> result = new ArrayList<User>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(DBUtils.populateUser(new User(), rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::getAll:" , e );
        }
        return result;
    }

    public long insert(User user) throws DAOException {
        String sql = "insert into " + USERS._name + " (" +
                       USERS.USERID + ", " + USERS.NAME + ", " +
                       USERS.ADDRESS + ", " + USERS.EMAIL + ", " + 
                       USERS.PHONE_NO + ", " + USERS.PASSWORD + ", " +
                       USERS.IS_ADMIN + ") values (?,?,?,?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNo());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.isAdmin());
            ps.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Exception while executing Item::insert" + user, e);
        }
        return 0;
    }

    @Override
    public void delete(User t) throws DAOException {
        String sql = "delete from " + USERS._name + " where " + USERS.USERID + " = ?";
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, t.getUserId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new DAOException("Error while deleting user " + t.getUserId(), e);
        }
        
    }

    @Override
    public User update(User t) throws DAOException {
        String sql = "update " + USERS._name + " set " +
                USERS.NAME + " = ?, " +
                USERS.EMAIL + " = ?, " +
                USERS.ADDRESS + " = ?, " +
                USERS.PHONE_NO + " = ?, " +
                USERS.PASSWORD + " = ?, " +
                USERS.IS_ADMIN + " = ?, " + " where " +
                USERS.USERID + " = ?";
        
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, t.getName());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getAddress());
            ps.setString(4, t.getPhoneNo());
            ps.setString(5, t.getPassword());
            ps.setBoolean(6, t.isAdmin());
            ps.setString(7, t.getUserId());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
            throw new DAOException("Exception while updating user " + t.getUserId(), e);
        }
        
        return getById(t.getUserId());
    }

    @Override
    public User getById(long id) throws DAOException {
        throw new DAOException("User can only be retrieved using userid");
    }

    

}