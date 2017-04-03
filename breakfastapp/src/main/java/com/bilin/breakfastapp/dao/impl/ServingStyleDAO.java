package com.bilin.breakfastapp.dao.impl;

import java.util.*;

import com.bilin.breakfastapp.dao.BaseDAO;
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
    public ServingStyle getById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ServingStyle> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(ServingStyle t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(ServingStyle t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ServingStyle update(ServingStyle t) {
        // TODO Auto-generated method stub
        return null;
    }
    
}