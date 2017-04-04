package com.bilin.breakfastapp.dao;


import java.util.List;

import com.bilin.breakfastapp.exceptions.DAOException;

/**
 * 
 */
public interface BaseDAO<T> {
    
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
    public long insert(T t) throws DAOException;

    /**
     * 
     */
    public void delete(T t) throws DAOException;

    /**
     * 
     */
    public T update(T t) throws DAOException;

}