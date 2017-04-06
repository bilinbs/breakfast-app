package com.bilin.breakfastapp.service.impl;

import org.springframework.stereotype.Service;

import com.bilin.breakfastapp.dao.impl.UserDAO;
import com.bilin.breakfastapp.exceptions.DAOException;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.LoginService;
import com.bilin.breakfastapp.vo.User;

@Service
public class LoginServiceImpl implements LoginService {
    
    UserDAO userDao = UserDAO.getInstance();

    @Override
    public User authenticateUser(String userId, String password) throws ServiceException {
        User user;
        try {
            user = userDao.getById(userId);
            if(user == null || !password.equals(user.getPassword())){
                
                return null;
            } else {
                return user;
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Exception while getting user", e);
        }
    }

}
