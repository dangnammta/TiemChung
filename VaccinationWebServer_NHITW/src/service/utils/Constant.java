/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author namdv
 */
public class Constant {
    
    public static final int numberDay = 7;

    public final static String ACTION_VIEW = "view";
    public final static int ACTIONID_VIEW = 100;
    public final static String ACTION_POST = "post";
    public final static int ACTIONID_POST = 101;
    public final static String ACTION_EDIT = "edit";
    public final static int ACTIONID_EDIT = 102;


    public static void main(String[] args) {

        for (int i = 1; i < 41; i++) {
            String ii = i + "";
            if (i < 10) {
                ii = "0" + i;
            }

            System.out.println("map_type.put(type_key_report_" + ii + ",type_value_report_" + ii + ");");
//            System.out.println(" public final static String type_value_report_" + i + " = \"Phòng \";");
        }

    }
}
