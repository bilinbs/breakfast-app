package com.bilin.breakfastapp.controller.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bilin.breakfastapp.controller.OrderController;
import com.bilin.breakfastapp.dao.impl.ItemDAO;
import com.bilin.breakfastapp.dto.ItmQtyDto;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.OrderService;
import com.bilin.breakfastapp.vo.BreakfastSet;
import com.bilin.breakfastapp.vo.Item;
import com.bilin.breakfastapp.vo.Order;
import com.bilin.breakfastapp.vo.ServingStyle;
import com.bilin.breakfastapp.vo.User;

/**
 * 
 */

@Controller
@SessionAttributes({"bfSet","sstyle","order"})
public class BSOrderController implements OrderController {
    
    @Autowired
    OrderService orderService;

    /**
     * Default constructor
     */
    public BSOrderController() {
    }

    /**
     * 
     */
    public void addOrder() {
        // TODO implement here
    }

    /**
     * 
     */
    public void modifyOrder() {
        // TODO implement here
    }

    /**
     * 
     */
    public void viewOrder() {
        // TODO implement here
    }

    @RequestMapping(value="/order-home")
    public String orderHome(ModelMap model){
        try {
            List<BreakfastSet> bfsets = orderService.getAllBfSets();
            model.addAttribute("bfsets",bfsets);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "bfHome";
    }
    
    @RequestMapping(value="/selectServingSyle", method=RequestMethod.GET)
    public String bfSetToServStyle(@RequestParam("bfSetId")long bfSetId, ModelMap model){
        try {
            BreakfastSet bfSet = orderService.getBfSetById(bfSetId);
            model.addAttribute("bfSet", bfSet);
            List<ServingStyle> sstyles = orderService.getServingStylesForBFSet(bfSetId);
            model.addAttribute("sstyles", sstyles);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "selectSS";
    }
    
    @RequestMapping(value="/editItems", method=RequestMethod.GET)
    public String customize(@RequestParam("sstyleId")long sstyleId, ModelMap model){
        try {
            ServingStyle sstyle = orderService.getServingStyleById(sstyleId);
            model.addAttribute("sstyle", sstyle);
            List<Item> items = orderService.getAllItems();
            model.addAttribute("items", items);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "editItems";
    }
    
    @RequestMapping(value="/preconfirm", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String preconfirm(@RequestBody ItmQtyDto[] itmQty,
            @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest,
            HttpServletResponse response) {
        try {
            Map<Item, Integer> itemMap = new HashMap<Item, Integer>();
            for (ItmQtyDto i : itmQty) {
                if (i.value != 0) {
                    Item item;

                    item = orderService.getItemById(i.id);
                    itemMap.put(item, i.value);
                    HttpSession session = httpRequest.getSession();
                    BreakfastSet bfSet = (BreakfastSet) session
                            .getAttribute("bfSet");
                    ServingStyle sstyle = (ServingStyle) session.getAttribute("sstyle");
                    if (bfSet != null) {
                        bfSet.setItems(itemMap);
                    }
                    bfSet.setServingStyle(sstyle);
                    List<BreakfastSet> bfSets = new ArrayList<BreakfastSet>();
                    bfSets.add(bfSet);
                    Order order = new Order();
                    order.setBreakfastSets(bfSets);
                    orderService.calculatePrice(order);
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                        order.setCustomer(user);
                    }
                    session.setAttribute("order", order);

                }
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "delivery-details";
    }
    
    @RequestMapping(value="delivery-details")
    public String getDetails(HttpServletRequest request){
        Order order = (Order)request.getSession().getAttribute("order");
        System.out.println(order);
        return "delivery-details";
    }
    
    @RequestMapping(value="/confirmOrder", method=RequestMethod.POST)
    public String confirmOrder(@RequestParam("noPerson") Integer noOfPersons, @RequestParam("paymentInfo") String paymentInfo,
            @RequestParam("daddress") String delivAddress, @RequestParam("ddate") String delivDate, 
            @RequestParam("dtime") String delivTime, ModelMap model) {
        try {
            Order order = (Order) model.get("order");
            List<BreakfastSet> bfList = new ArrayList<BreakfastSet>();
            BreakfastSet bfset = order.getBreakfastSets().get(0);
            for (int i = 0; i < noOfPersons; i++) {
                bfList.add(bfset);
            }
            orderService.calculatePrice(order);
            order.setBreakfastSets(bfList);
            order.setDeliveryAddress(delivAddress);
            order.setPaymentInfo(paymentInfo);
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date ddate = df.parse(delivDate);

            int hour = Integer.parseInt(delivTime.split(":")[0]);
            int minutes = Integer.parseInt(delivTime.split(":")[1]);
            ddate.setHours(hour);
            ddate.setMinutes(minutes);
            order.setDeliveryTime(ddate);
            order = orderService.createOrder(order);
            model.addAttribute("order", order);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "orderConfirmed";
    }
}