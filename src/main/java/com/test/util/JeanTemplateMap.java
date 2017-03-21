package com.test.util;

import com.test.model.JeanStyleEnum;

import java.util.ArrayList;

public class JeanTemplateMap {

    /*  @param byteString is gathered from html checkboxes
        return false for empty Strings and true otherwise
     */
    public byte checkboxToBtye(String byteString) {
        if (byteString.isEmpty()) {
            return (byte) 0;
        }
        return (byte) 1;
    }

    /*  @param description gathered from ShopStyleAPI
        @param categoires gathered from ShopStypeAPI, contains all of product categories
        method matches category against JeanSystle Enum directly, matches category "classic" to STRAIGHT
        searches description for keywords after
     */

    public JeanStyleEnum getStyle(String description, ArrayList<String> categories) {

        //matches category against JeanStyleEnum Enum directly
        for (JeanStyleEnum style : JeanStyleEnum.values())
            for(String category: categories){
                if (category.contains(style.toString())) {
                    return style;
                }
            }
        //matches for keywords in description to find JeanStyleEnum
        String d = description.toLowerCase();
        if (d.contains("skinny") | d.contains("leggings")) {
            return JeanStyleEnum.SKINNY;
        } else if (d.contains("straight")) {
            return JeanStyleEnum.STRAIGHT;
        } else if (d.contains("relaxed")) {
            return JeanStyleEnum.RELAXED;
        } else if (d.contains("flare")) {
            return JeanStyleEnum.FLARE;
        } else if (d.contains("bootcut")) {
            return JeanStyleEnum.BOOTCUT;
        }
        return JeanStyleEnum.STRAIGHT;
    }

    /*  @param description - gathered from ShopStyleAPI
        @param cateogires gathered from ShopStypeAPI, contains all of product categories
        tests parameters for keywords to set JeanEntity boolean distressed
        default returns false
            */

    public boolean checkDistress(String description, ArrayList<String> categories) {
        for(String category: categories) {
            if (category.contains("Distressed")) {
                return true;
            }
        }
        String d = description.toLowerCase();
        if (d.contains("hole") | d.contains("distress") | d.contains("shred") | d.contains("rip") | d.contains("destroy")| d.contains("slash")) {
            return true;
        }
        return false;
    }
    /*  @param description - gathered from ShopStyleAPI
        @param cateogires gathered from ShopStypeAPI, contains all of product categories
        tests parameters for keywords to set JeanEntity boolean cropped
        default returns false
        */

    public boolean checkCropped(String description, String brandedName, ArrayList<String> categories) {

        for(String category: categories) {
            if (category.contains("Cropped")) {
                return true;
            }
        }

        String b = brandedName.toLowerCase();
        if (b.contains("ankle") | b.contains("crop") | b.contains("capri") | b.contains("cutoff")) {
            return true;
        }

        String d = description.toLowerCase();
        if (d.contains("ankle") | d.contains("crop") | d.contains("capri") | d.contains("cutoff")) {
            return true;
        }


        return false;
    }


}
