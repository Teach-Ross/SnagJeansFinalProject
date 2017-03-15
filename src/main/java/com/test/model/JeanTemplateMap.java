package com.test.model;

public class JeanTemplateMap {
    private JeanTemplate temp = new JeanTemplate();

   /* @RequestParam("waistsize") int waistSize,
    @RequestParam("inseamsize") int inseamSize,
    @RequestParam("style") JeanStyleEnum styleEnum,
    @RequestParam("color") String htmlColor,
    @RequestParam("cropped") String cropped,
    @RequestParam("distress") String distress,
    @CookieValue("userID") String userId,
    Model model){
*/
    public JeanTemplate returnNewJeanEntity(int waiseSize, int inseamSize, JeanStyleEnum styleEnum, String htmlColor, String cropped, String distress, String userdId) {
        temp.setWaistSize(waiseSize);
        temp.setInseamLength(inseamSize);
        temp.setJeanStyle(styleEnum.toString());
        temp.setColor(htmlColor);
        this.setCropped(cropped);
        this.setDistress(distress);
        temp.setUserId(userdId);

        return this.temp;
    }


    /*  @param cropped gathered from html checkbox
        method sets cropped byte value(mysql holder for boolean)
     */
    private void setCropped(String cropped) {


        //checkbox will return empty string value for non-cropped jeans
        if (cropped.isEmpty()) {
            temp.setCropped((byte) 0);
            return;
        }
        temp.setCropped((byte) 1);
    }


    /*  @param distress gathered from html checkbox
         method sets distressed byte value(mysql holder for boolean)
      */
    private void setDistress(String distress) {

        //checkbox will return empty string value for non-distressed jeans
        if (distress.isEmpty()) {
            temp.setDistressed((byte) 0);
            return;
        }
        temp.setDistressed((byte) 1);
    }
}
