package com.ysh;

public class ElectricCyle {


    public static final String tableName = "T_Y";

    public static final String[] fields = new String[]{
            "obj_id",
            "JCGZIX"
    };


    public ElectricCyle() {
    }

    /**
     * @return
     */
    public static String getTableName() {
        return tableName;
    }

    public static String[] getFields() {
        System.out.println("==============");
        System.out.println();
        return fields;


    }
}
