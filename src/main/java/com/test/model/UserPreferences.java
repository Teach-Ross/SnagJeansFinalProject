package com.test.model;


import com.test.DAO.TemplateDao;

//stores user prefered jean preferences locally
public class UserPreferences {
    private String jeanStyle;
    private boolean cropped;
    private boolean distressed;
    private boolean favoriteCroppedOrDistressed;
    private TemplateDao accessTemplate = new TemplateDao();

    public UserPreferences() {
    }

    public UserPreferences(String jeanStyle, boolean cropped, boolean distressed, boolean favoriteCroppedOrDistressed) {
        this.jeanStyle = jeanStyle;
        this.cropped = cropped;
        this.distressed = distressed;
        this.favoriteCroppedOrDistressed = favoriteCroppedOrDistressed;
    }

    public void buildUserPreferences (String userId){
        this.jeanStyle = accessTemplate.selectSearchJeanType(userId);
        Object[] preferences = accessTemplate.selectSearchCroppedDistressed(userId);
        this.cropped = (boolean) preferences[0];
        this.distressed = (boolean) preferences[1];
        this.favoriteCroppedOrDistressed = (boolean) preferences[2];

    }

    public String getJeanStyle() {
        return jeanStyle;
    }

    public void setJeanStyle(String jeanStyle) {
        this.jeanStyle = jeanStyle;
    }

    public boolean isCropped() {
        return cropped;
    }

    public void setCropped(boolean cropped) {
        this.cropped = cropped;
    }

    public boolean isDistressed() {
        return distressed;
    }

    public void setDistressed(boolean distressed) {
        this.distressed = distressed;
    }

    public boolean isFavoriteCroppedOrDistressed() {
        return favoriteCroppedOrDistressed;
    }

    public void setFavoriteCroppedOrDistressed(boolean favoriteCroppedOrDistressed) {
        this.favoriteCroppedOrDistressed = favoriteCroppedOrDistressed;
    }
}
