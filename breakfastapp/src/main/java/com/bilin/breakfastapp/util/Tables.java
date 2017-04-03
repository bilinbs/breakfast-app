package com.bilin.breakfastapp.util;

public final class Tables {
    
    public static final class ITEMS {
        public static String _name = "items";
        public static String ID = "id";
        public static String NAME = "name";
        public static String UNITPRICE = "unitprice";
        public static String SERVINGUNIT = "servingunit";
        public static String DESCRIPTION = "description";
    }
    
    public static final class BREAKFAST_SETS {
        public static String _name = "breakfast_sets";
        public static String ID ="id";
        public static String NAME ="name";
        public static String DESCRIPTION = "description";
        public static String PRICE = "price";
        public static String ISTEMPLATE = "isTemplate";
        public static String SERVING_STYLE = "serving_style";
    }
    
    public static final class ITEM_QUANTITIES {
        public static String _name = "item_qtys";
        public static String BF_SET_ID = "bf_set_id";
        public static String ITEM_ID = "item_id";
        public static String QUANTITY = "quantity";
    }

}
