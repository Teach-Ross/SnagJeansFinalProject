package com.test.model;

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


/*
    private void setStyle(String description, String category){

       //matches category against JeanStyleEnum Enum directly
        for(JeanStyleEnum style: JeanStyleEnum.values()){
            if(style.toString().contains(category)){
                this.temp.setStyle(style);
                return;
            }
        }

        //matches for keywords in description to find JeanStyleEnum
        String d = description.toLowerCase();
        if(d.contains("skinny") | d.contains("leggings")){
            this.temp.setStyle(JeanStyleEnum.SKINNY);
            return;
        }else if(category.contains("Classic")| d.contains("straight")){
            this.temp.setStyle(JeanStyleEnum.STRAIGHT);
        }
        else if(d.contains("relaxed")){
            this.temp.setStyle(JeanStyleEnum.RELAXED);
            return;
        }else if(d.contains("flare")){
            this.temp.setStyle(JeanStyleEnum.FLARE);
            return;
        }else if(d.contains("bootcut")){
            this.temp.setStyle(JeanStyleEnum.BOOTCUT);
            return;
        }


    }


    *//*  @param description - gathered from ShopStyleAPI
        @param category - gathered from ShopStyle API
        tests parameters for keywords to set JeanEntity boolean distressed
        default returns false
     *//*

    private void checkDistress(String description, String category) {
        if (category.contains("Distressed")) {
            this.temp.setDistressed(true);
            return;
        }
        String d = description.toLowerCase();
        if (d.contains("hole") | d.contains("distress")) {
            this.temp.setDistressed(true);
            return;
        }
        this.temp.setDistressed(false);
    }

    *//*  @param description - gathered from ShopStyleAPI
        @param category - gathered from ShopStyle API
        tests parameters for keywords to set JeanEntity boolean cropped
        default returns false
     *//*
    private void checkCropped(String description, String category){
        if(category.contains("Cropped")){
            this.temp.setCropped(true);
            return;
        }

        String d = description.toLowerCase();
        if(d.contains("ankle")|d.contains("crop")|d.contains("capri")|d.contains("cutoff")){
            this.temp.setCropped(true);
            return;
        }
        this.temp.setCropped(false);
    }*/



}
