package com.bilin.breakfastapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.bilin.breakfastapp.exceptions.DAOException;

/**
 * 
 */
public interface BaseDAO<T> {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * @throws DAOException 
     * 
     */
    public T getById(long id) throws DAOException;

    /**
     * 
     */
    public List<T> getAll() throws DAOException;

    /**
     * 
     */
    public void insert(T t) throws DAOException;

    /**
     * 
     */
    public void delete(T t) throws DAOException;

    /**
     * 
     */
    public T update(T t) throws DAOException;

}