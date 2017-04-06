package com.bilin.breakfastapp.service;


import com.bilin.breakfastapp.dao.impl.UserDAO;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.vo.User;

/**
 * 
 */
public interface LoginService {

    /**
     * 
     */
    public UserDAO userDAO = UserDAO.getInstance();

    
    public User authenticateUser(String userName, String password) throws ServiceException;
}