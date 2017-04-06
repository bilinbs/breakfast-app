package com.bilin.breakfastapp.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilin.breakfastapp.controller.AdminController;
import com.bilin.breakfastapp.dto.ItemQtyDto;
import com.bilin.breakfastapp.dto.ItemQtyListDto;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.AdminService;
import com.bilin.breakfastapp.service.OrderService;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.ServingStyle;

/**
 * 
 */
@Controller
public class BSAdminController implements AdminController {

    /**
     * 
     * Default constructor
     */
    public BSAdminController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(List.class, "sstyles", new CustomCollectionEditor(List.class){
            @Override
            protected Object convertElement(Object element)
            {
                Long id = null;
                if(element instanceof String){
                    id = Long.parseLong((String)element);
                } else if(element instanceof Long){
                    id = (Long) element;
                } else {
                    id = 0l;
                }
                return new ServingStyle(id);
            }
        });
    }
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value="adminHome",method=RequestMethod.GET)
    public String adminHome(){
        
        return "admin/adminHome";
    }
    

    /**
     * 
     */
    @RequestMapping(value="/addItem", method=RequestMethod.GET)
    public String addItem(ModelMap model) {
        try {
            List<String> servingUnits = adminService.getAllServingUnits();
            model.addAttribute("servingUnits", servingUnits);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "admin/addItem";
    }
    
    @RequestMapping(value="/addItem", method=RequestMethod.POST)
    public String addItem(@RequestParam("item-name") String name, @RequestParam("item-desc") String desc,
            @RequestParam("serving-unit") String servUnits, @RequestParam("unit-price") Double price, ModelMap model) {
        try {
            Item item = adminService.addItem(name, desc, servUnits, price);
            model.addAttribute("item", item);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "admin/addItem";
    }

    /**
     * 
     */
    public void modifyItem() {
        // TODO implement here
    }

    /**
     * 
     */
    public void removeItem() {
        // TODO implement here
    }

    /**
     * 
     */
    @RequestMapping(value="/addBFSet", method=RequestMethod.GET)
    public String addBFSet(ModelMap model) {
        ItemQtyListDto itemQtyListDto = new ItemQtyListDto();
        itemQtyListDto.setItemQtyList(new ArrayList<ItemQtyDto>());
        try {
            List<Item> items = orderService.getAllItems();
            for(Item item : items){
                itemQtyListDto.getItemQtyList().add(new ItemQtyDto(item.getName(),item.getId(), 0));
            }
            List<ServingStyle> sstyles = orderService.getAllServingStyles();
            model.addAttribute("sstyles", sstyles);
            model.addAttribute("itemQtyListDto", itemQtyListDto );
            
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "admin/addBFset";
    }

    
    @RequestMapping(value="/addBFSet", method=RequestMethod.POST)
    public String addBFSet(@ModelAttribute("itemQtyListDto")ItemQtyListDto itemQtyListDto,
            @RequestParam("bfset-name")String name, @RequestParam("bfset-desc")String desc, 
            @RequestParam("bfset-price")Double price, @RequestParam("bfset-sstyles") long[] sstyles,
            ModelMap model) {
            try {
                BreakfastSet bfset = adminService.addBFSet(name, desc, price, itemQtyListDto, sstyles);
                model.addAttribute("bfset", bfset);
            } catch (ServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        /*} catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        return "admin/addBFset";
    }
    
    

    /**
     * 
     */
    public void modifyBFSet() {
        // TODO implement here
    }

    /**
     * 
     */
    public void removeBFSet() {
        // TODO implement here
    }

    /**
     * 
     */
    @RequestMapping(value="/addServingStyle", method=RequestMethod.GET)
    public String addServingStyle(ModelMap model) {
        return "admin/addServingStyle";
    }

    @RequestMapping(value="/addServingStyle", method=RequestMethod.POST)
    public String addItem(@RequestParam("sstyle-name") String name, @RequestParam("sstyle-desc") String desc,
             @RequestParam("sstyle-price") Double price, ModelMap model) {
        try {
            ServingStyle sstyle = adminService.addServingStyle(name, desc, price);
            model.addAttribute("sstyle", sstyle);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "admin/addServingStyle";
    }
    /**
     * 
     */
    public void modifyServingStyle() {
        // TODO implement here
    }

    /**
     * 
     */
    public void removeServingStyle() {
        // TODO implement here
    }

    /**
     * 
     */
    public void manageOrder() {
        // TODO implement here
    }

}