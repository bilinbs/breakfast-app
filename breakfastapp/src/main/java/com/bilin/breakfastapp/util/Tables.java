package com.bilin.breakfastapp.util;

public final class Tables {
    
    public static final class ITEMS {
        public static final String _name = "items";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String UNITPRICE = "unitprice";
        public static final String SERVINGUNIT = "servingunit";
        public static final String DESCRIPTION = "description";
    }
    
    public static final class BREAKFAST_SETS {
        public static final String _name = "breakfast_sets";
        public static final String ID ="id";
        public static final String NAME ="name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        public static final String ISTEMPLATE = "isTemplate";
        public static final String SERVING_STYLE = "serving_style";
    }
    
    public static final class ITEM_QUANTITIES {
        public static final String _name = "item_qtys";
        public static final String BF_SET_ID = "bf_set_id";
        public static final String ITEM_ID = "item_id";
        public static final String QUANTITY = "quantity";
    }

    public static final class SERVING_STYLES {
        public static final String _name = "serving_styles";
        public static final String ID = "id";
        public static final String  NAME = "name";
        public static final String  DESCRIPTION = "description";
        public static final String  PRICE = "price";
    }
    
    public static final class BREAKFASTSET_SERVINGSTYLES {
        public static final String _name = "bfset_servingstyles_map";
        public static final String SERVING_STYLE_ID = "serving_style_id";
        public static final String  BF_SET_ID = "bf_set_id";
    }
    
    public static final class USERS {
        public static final String _name = "users";
        public static final String USERID = "userId";
        public static final String EMAIL = "email";
        public static final String ADDRESS = "address";
        public static final String PHONE_NO = "phone_no";
        public static final String NAME = "name";
        public static final String PASSWORD = "password";
        public static final String IS_ADMIN = "is_admin";
    }
    
    public static final class ORDERS {
        public static final String _name = "orders";
        public static final String ID = "id";
        public static final String  CUSTOMER = "customer";
        public static final String TOTAL_PRICE = "total_price";
        public static final String PAYMENT_INFO = "payment_info";
        public static final String ORDER_STATUS = "order_status";
        public static final String DELIVERY_ADDRESS = "delivery_address";
        public static final String DELIVERY_TIME = "delivery_time";
    }
    
    public static final class BREAKFAST_SETS_ORDERS {
        public static final String _name = "breakfast_sets_orders";
        public static final String ORDER_ID = "order_id";
        public static final String BREAKFAST_SET_ID = "breakfast_set_id";
    }
}

