package com.test.controller;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.DAO.TemplateDao;
import com.test.DAO.UserDao;
import com.test.model.JeanStyleEnum;
import com.test.model.JeanTemplate;
import com.test.model.User;
import com.test.model.UserPreferences;
import com.test.util.JeanTemplateMap;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


@Controller

public class HomeController {

    //CRUD for user database
    private UserDao accessUser = new UserDao();
    //CRUD for template database
    private TemplateDao accessTemplate = new TemplateDao();
    //helper class for converting raw data from APIs and html selections to JeanTemplate object
    private JeanTemplateMap templateMap = new JeanTemplateMap();
    //used to store JeanTemplates for inserting or updating JeanTemplate table in database
    private JeanTemplate userJean = new JeanTemplate();
    //used to store preferences based existing user templates
    private UserPreferences userPreferences = new UserPreferences();

    //simple website homepage, uses FBConnection to send authUrl to view
    @RequestMapping("/")
    public ModelAndView landingPage() {
        //create FBConnection instance
        FBConnection fbConnection = new FBConnection();
        //fb.Connection.getFBAuthUrl() directs to FB login and then back to specified REDIRECTURI after successful login
        return new
                ModelAndView("landingpage", "message", fbConnection.getFBAuthUrl());

    }

    @RequestMapping("/privacy")
    public String privacy(){
        return "privacyPolicy";
    }
    /*  mapping for new or existing users after successful facebook longin and redirect

        @param code used to obtain accessToken which also us to access FBGraph info
        @param response used to store cookie with user info
        @param model holds attributes sent to view
     */
    @RequestMapping("/welcome2")

    public String gatherFbData(@RequestParam("code") String code,
                               HttpServletResponse response, Model model) {
        //verifies code recieved from fb login
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback");
        }

        FBConnection fbConnection = new FBConnection();
        //gathers accessToken using code
        String accessToken = fbConnection.getAccessToken(code);
        //gathers fbGraph String using accessToken
        FBGraph fbGraph = new FBGraph(accessToken);
        String graph = fbGraph.getFBGraph();
        Map fbProfileData = fbGraph.getGraphData(graph);
        //stores fbId locally
        String id = fbProfileData.get("id").toString();
        //creates cookie with user fbId, set cookie to expire when browser closes, attaches cookie HttpServlet
        Cookie userCookie = new Cookie("userTag", id);
        userCookie.setMaxAge(-1);
        response.addCookie(userCookie);


        if (accessUser.userIdExists(id)) {
            //use fbId to access user within database
            User newUser = accessUser.selectUser(id);
            //gather userName to attach to model to send to view
            String userName = newUser.getName();
            //loads userPrefences intially when user logins
            userPreferences.buildUserPreferences(id);
            //add userName to model to display on welcomeExists
            model.addAttribute("message", userName);
            return
                    "welcomeExists";
        } else {
            //loads userPrefernces intially when user logins, for new user this loads "empty preferences"
            userPreferences.buildUserPreferences(id);
            return "welcomeNew";
        }
    }

    /*
        @param String name
        @param String address
        @param String city
        @param String zip
        @param String userId
        userId is gathered from cookie added at login
        all other parameters gathered from form in welcomeNew html
        Method uses these parameters to create new User and insert into database
     */
    @RequestMapping("/welcomeNew")

    public ModelAndView welcomeNew(@RequestParam("name") String name,
                                   @RequestParam("address") String address,
                                   @RequestParam("city") String city,
                                   @RequestParam("state") String state,
                                   @RequestParam("zip") String zip,
                                   @CookieValue("userTag") String userId) {

        //validates user has entered name. If name is empty returns error message to welcomeNew
        if (name.equalsIgnoreCase("")) {
            String errorMessage = "Name is missing and is a required field";
            return new ModelAndView("welcomeNew", "errormessage", errorMessage);
        } else {
            User addUser = new User();

            addUser.setUserId(userId);
            addUser.setName(name);
            addUser.setAddress(address);
            addUser.setCity(city);
            addUser.setState(state);
            addUser.setZip(zip);
            //uses UserDao to insert User into database
            accessUser.insert(addUser);

            return new
                    ModelAndView("templateDirect", " ", " ");
        }
    }
    /*  @param model holds attributes sent to view
        @param userId
        directs user to welcomeExist when they click a home link through throughout the website
        gathers userId from cookie in order to access and display their userName saved in database
     */
    @RequestMapping("/home")
    public String home(@CookieValue("userTag") String userId,
                       Model model) {
        User newUser = accessUser.selectUser(userId);
        String userName = newUser.getName();
        model.addAttribute("message", userName);

        return "welcomeExists";
    }

    @RequestMapping("/newTemplate")
    //directs user request to create template to templateDirect view
    //which will allow them to create template from scratch, inspiration or preferenced inspiration
    public String newTemplate() {
        return "templateDirect";
    }

    /*  @param userId
        @param model
        grabs userId from cookie, loads arraylist with all templates matching userId
        allows user to select or delete
     */

    @RequestMapping("/editTemplate")
    public String editTemplate(Model model, @CookieValue(value="userTag", required = false) String userID) {
        //uses templateDao to gather all user templates
        ArrayList<JeanTemplate> templateList = accessTemplate.selectAllUserTemplates(userID);

        model.addAttribute("templateList", templateList);

        return "templateView";
    }

    @RequestMapping("/deleteTemplate")
    public String deleteCustomer(@RequestParam("id") int templateId,
                                 @CookieValue("userTag") String userID,
                                 Model model) {
        // temp will store info for the object that we want to delete
        JeanTemplate temp = new JeanTemplate();
        temp.setTemplateId(templateId);
        accessTemplate.delete(temp);

        ArrayList<JeanTemplate> templateList = accessTemplate.selectAllUserTemplates(userID);

        model.addAttribute("templateList", templateList);

        return "templateView";

    }
    /*  @param templateId gathered from user selection on templateView page
        @param model
        gathers JeanTemplate object from database by matching templateId
     */

    @RequestMapping("/selectTemplate")
    public String selectTemplate(@RequestParam("id") int templateId, Model model) {
        //assigns JeanTemplate gathered from database
        JeanTemplate temp = accessTemplate.selectTemplate(templateId);

        //convers string to JeanStyleEnum
        JeanStyleEnum style = JeanStyleEnum.valueOf(temp.getJeanStyle().toUpperCase());
        //converts byte values from database to boolean
        boolean cropped = (temp.getCropped() != 0);
        boolean distress = (temp.getDistressed() != 0);

        //sends array of all JeanStyleEnums to view
        model.addAttribute("list", JeanStyleEnum.values());
        //sends all JeanTemplate attributes to view
        model.addAttribute("style", style);
        model.addAttribute("cropped", cropped);
        model.addAttribute("distress", distress);
        model.addAttribute("color", temp.getColor());
        model.addAttribute("waistSize", temp.getWaistSize());
        model.addAttribute("inseamSize", temp.getInseamLength());
        model.addAttribute("templateId", templateId);
        model.addAttribute("templateName", temp.getTemplateName());


        return "templateBuildResult";
    }

    /*  param model
        direct user to templateBlank which will display unpopulated template
     */

    @RequestMapping("/templateBlank")
    public String displayBlankTemplate(Model model) {
        //sends array of all JeanStyleEnums to view
        model.addAttribute("list", JeanStyleEnum.values());
        return "templateBlank";
    }

    /*  @param model
        gathers random images from ShopStyleAPI to display
     */
    @RequestMapping("/templateBuild")
    public String jeansImages(Model model) {

        //used to generate random offset for search query and to generate random product index
        Random random = new Random();
        //uses gatherImages method to obtain JSONObject from ShopStyleApi
        JSONObject obj = gatherImages("/api/v2/products?pid=uid5921-39054839-10&fts=jeans");

        JSONArray ar = obj.getJSONArray("products");

        //identify random product with three random distinct ints 0-24
        int[] numbers = random.ints(0, 24).distinct().limit(3).toArray();
        JSONObject productObject = ar.getJSONObject(numbers[0]);
        //gathers image url
        String image1 = productObject.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
       //gathers product id
        int id1 = productObject.getInt("id");
        String idString = Integer.toString(id1);
        //stores image url and product id in ArrayList
        ArrayList<String> jean1 = new ArrayList<String>();
        jean1.add(image1);
        jean1.add(idString);
        //attach both of these to model
        model.addAttribute("image1", image1);
        model.addAttribute("jean1", jean1);

        JSONObject productObject2 = ar.getJSONObject(numbers[1]);
        //gathers image url
        String image2 = productObject2.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
        //gathers product id
        int id2 = productObject2.getInt("id");
        String idString2 = Integer.toString(id2);
        //attach both of these to model
        ArrayList<String> jean2 = new ArrayList<String>();
        jean2.add(image2);
        jean2.add(idString2);

        model.addAttribute("image2", image2);
        model.addAttribute("jean2", jean2);

        JSONObject productObject3 = ar.getJSONObject(numbers[2]);
        //gathers image url
        String image3 = productObject3.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
        //gathers product id
        int id3 = productObject3.getInt("id");
        String idString3 = Integer.toString(id3);
        //attach both of these to model
        ArrayList<String> jean3 = new ArrayList<String>();
        jean3.add(image3);
        jean3.add(idString3);

        model.addAttribute("image3", image3);
        model.addAttribute("jean3", jean3);


        return "templateBuild";
    }
    /*  @param model
        returns three jean images to view, these images are skewed towards user preferences
     */
    @RequestMapping("/templateBuildPreferences")
    public String jeanImagesPreferences(Model model,
                                         @CookieValue("userTag") String userID) {
        //used to generate random offset for search query and to generate random product index
        Random random = new Random();

        //gathers user preference for JeanStyle
        String jeanStyle = userPreferences.getJeanStyle();
        String searchQuery = "";
        //user with no templates will have empty string for jeanStyle
        if(!jeanStyle.isEmpty()) {

            //gather user preferences for cropped and distressed
            boolean crop = userPreferences.isCropped();
            boolean distress = userPreferences.isDistressed();
            boolean favoriteCroppedDistress = userPreferences.isFavoriteCroppedOrDistressed();

            //returns random seach query weighted for user prefences
            searchQuery = returnRandomSearch(jeanStyle, crop, distress, favoriteCroppedDistress);
        }else{
            //returns simple search for jeans
            searchQuery = "/api/v2/products?pid=uid5921-39054839-10&cat=jeans";
        }

        JSONObject obj = gatherImages(searchQuery);

        JSONArray ar = obj.getJSONArray("products");

        //used to store useful

        //see comments from templateBuild mapping
        int[] numbers = random.ints(0, 24).distinct().limit(3).toArray();
        JSONObject productObject = ar.getJSONObject(numbers[0]);
        String image1 = productObject.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
        int id1 = productObject.getInt("id");
        String idString = Integer.toString(id1);
        ArrayList<String> jean1 = new ArrayList<String>();
        jean1.add(image1);
        jean1.add(idString);

        model.addAttribute("image1", image1);
        model.addAttribute("jean1", jean1);

        JSONObject productObject2 = ar.getJSONObject(numbers[1]);
        String image2 = productObject2.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
        int id2 = productObject2.getInt("id");
        String idString2 = Integer.toString(id2);
        ArrayList<String> jean2 = new ArrayList<String>();
        jean2.add(image2);
        jean2.add(idString2);

        model.addAttribute("image2", image2);
        model.addAttribute("jean2", jean2);

        JSONObject productObject3 = ar.getJSONObject(numbers[2]);
        String image3 = productObject3.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
        int id3 = productObject3.getInt("id");
        String idString3 = Integer.toString(id3);
        ArrayList<String> jean3 = new ArrayList<String>();
        jean3.add(image3);
        jean3.add(idString3);

        model.addAttribute("image3", image3);
        model.addAttribute("jean3", jean3);


        return "templateBuildPreferences";
    }
    /*  @param jean ArrayList contains both productid and image url
        @param model
        method uses these parameters to gather information about jean selection
        and pre populate view of template
     */
    @RequestMapping("templateBuildResult")
    public String displayTemplate(Model model,
                                  @RequestParam("jean") ArrayList<String> jean) {


        //use of substring indexes to decode arraylist gathered from view
        String imageUrl = jean.get(0).substring(1);
        int length = jean.get(1).length();
        String productId = jean.get(1).substring(0, length - 1);

        //sends imageUrl to ImmaggaApi, getHtmlColor returns string of html color code
        String htmlColor = getHtmlColor(imageUrl);
        model.addAttribute("color", htmlColor);

        //call ShopStyle api to gather jean specific information and map to display on template page
        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            //query here is for information on one specific product
            String productUrl = "http://api.shopstyle.com/api/v2/products/" + productId + "?pid=uid5921-39054839-10";
            HttpGet getPage = new HttpGet(productUrl);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);

            //gather product category
            //each jean from ShopStyle can have mutiple categories, these are gathered and stored in an arraylist
            JSONArray categoryArray = obj.getJSONArray("categories");
            ArrayList<String> categories = new ArrayList<String>();
            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject tag = categoryArray.getJSONObject(i);
                categories.add(tag.getString("name"));
            }
            //gather product name and description
            String brandedName = obj.getString("brandedName");
            String description = obj.getString("description");


            //converts description, categories and brandedName gathered from ShopStyle API to java objects used to prepopulate jean template
            JeanStyleEnum styleEnum = templateMap.getStyle(description, categories);
            boolean cropped = templateMap.checkCropped(description, brandedName, categories);
            boolean distress = templateMap.checkDistress(description, categories);
            model.addAttribute("style", styleEnum);
            model.addAttribute("list", JeanStyleEnum.values());
            model.addAttribute("cropped", cropped);
            model.addAttribute("distress", distress);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "templateBuildResult";
    }


    /*  @param waistSize
        @param inseamSize
        @param styleEnum
        @param htmlColor
        @param cropped
        @param distress
        all these parameters are gathered from user selections on template form
        @param templateId
        @param templateName
        used when user is editing existing template, helps mapping to update or save as new
        @param userId
        used to set userId to save in database
        @param model
        Method gathers information from user selections on view and creates a JeanTemplate object
     */
    @RequestMapping("gather")
    public String displayTemplate(
            @RequestParam("waistsize") int waistSize,
            @RequestParam("inseamsize") int inseamSize,
            @RequestParam("style") JeanStyleEnum styleEnum,
            @RequestParam("color") String htmlColor,
            @RequestParam("cropped") String cropped,
            @RequestParam("distress") String distress,
            //these parameters will only be available when user is editing existing template
            @RequestParam(value = "templateId", required = false) int templateId,
            @RequestParam(value = "templateName", required = false) String templateName,
            @CookieValue("userTag") String userId,
            Model model) {


        userJean.setWaistSize(waistSize);
        userJean.setInseamLength(inseamSize);
        userJean.setJeanStyle(styleEnum.toString());
        userJean.setColor(htmlColor);
        userJean.setUserId(userId);
        userJean.setCropped(templateMap.checkboxToBtye(cropped));
        userJean.setDistressed(templateMap.checkboxToBtye(distress));
        //base price for jean starts at $40 and increase $5 for both distressed and cropped selections
        BigDecimal bd = new BigDecimal(40.00);
        if (userJean.getDistressed() != 0) {
            bd = bd.add(new BigDecimal(5));
        }
        if (userJean.getCropped() != 0) {
            bd = bd.add(new BigDecimal(5));
        }
        userJean.setPrice(bd);

        //passes templateId to view if available
        model.addAttribute("templateId", templateId);


        String tempName = templateName;

        //templateId parameter will be 0 for newTemplates
        //this test identifies if template is new or existing
        if (templateId != 0) {
            userJean.setTemplateName(tempName);
            //templateResultEdit view will allow user to update, save as new or discard changes
            return "templateResultEdit";
        }
        //templateResult view will allow user to save as new or discard changes
        return "templateResult";
    }

    /*  @param templateName
        will be available if user has chosen to save new template
        @param templateId
        @param userId
        @param model
        Method tests if user attempting to save new template or update old template
     */

    @RequestMapping("templateSave")
    public String saveTemplate(
            @RequestParam(value = "templateId", required = false) String templateId,
            @RequestParam(value = "templateName", required = false) String templateName,
            @CookieValue("userTag") String userID,
            Model model) {

        //assigns templateId to String temp, temp will be null if user is saving newTemplate
        String temp = templateId;

        if (temp != null) {
            //updates template in database, linking to existing entry by templateId
            accessTemplate.update(userJean, Integer.valueOf(temp));
        } else {

            userJean.setTemplateId(0);
            userJean.setTemplateName(templateName);
            accessTemplate.insert(userJean);
        }

        ArrayList<JeanTemplate> templateList = accessTemplate.selectAllUserTemplates(userID);
        userPreferences.buildUserPreferences(userID);

        model.addAttribute("templateList", templateList);


        return "templateView";
    }


    /*  @param imageUrl gathered from ShopStyleApi
        method return String of HTML hex code of most prominent color identified in image by ImaggaApi
     */
    public String getHtmlColor(String imageUrl) {

        String apiKey = apiKey = "acc_9cf903d4cf36e57",
                apiSecret = "d8254b91c035c098d5a35a93190609a7";
        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> response = Unirest.get("https://api.imagga.com/v1/colors")
                    //specifies query sent to ImaggaApi
                    .queryString("url", imageUrl)
                    //specifies key needed to accept API
                    .basicAuth(apiKey, apiSecret)
                    .header("Accept", "application/json")
                    //specifies that we want response from API in JSON format
                    .asJson();

            JSONArray obj = response.getBody().getObject().getJSONArray("results");

            //returns generic jean color if API calls fails and returns empty JSON
            while(obj.isNull(0)){
                System.out.println("ERROR FOUND FOR COLOR IDENTIFICATION");
                System.out.println(imageUrl);
                return "#4e6590";
            }

            JSONArray obj2 = obj.getJSONObject(0).getJSONObject("info").getJSONArray("foreground_colors");

            //tests again and returns generic jean color if API calls fails and returns empty JSON
            while(obj2.isNull(0)){
                System.out.println("ERROR FOUND FOR COLOR IDENTIFICATION");
                System.out.println(imageUrl);
                return "#4e6590";
            }

            //gathers String of htmlCode if all other tests pass
            return obj2.getJSONObject(0).getString("html_code");

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*  @param jeanStyle user preference for jeanstyle
        @param cropped user preference for cropped jeans
        @param distress user preference for distressed jeans
        @param favoriteCroppedDistress user preference for cropped over distressed
     */
    private String returnRandomSearch(String jeanStyle, boolean cropped, boolean distress, boolean favoriteCroppedDistress) {
        Random random = new Random();

        String search0 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans";
        String search1 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans+" + jeanStyle.toLowerCase();
        //assigns jeans + jeanstyle search
        String search2 = search1;
        //assigns jeans search
        String search3 = search0;
        //assigns jeans + jeanstyle search
        String search4 = search1;

        //if user prefers cropped but not distressed, weighted random for cropped is 40%
        if (cropped && !distress) {
            search2 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans+" + jeanStyle.toLowerCase() + "+cropped";
            search3 = search2;
        }
        //if user prefers distressed but not distressed, weighted random for distressed is 40%
        else if (!cropped && distress)
        {   search2 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans+" + jeanStyle.toLowerCase() + "+distressed";
            search3 = search2;
        }
        //if user prefers both cropped and distressed, program tests which they prefer more
        else if(cropped && distress){
            search2 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans" + jeanStyle.toLowerCase() + "+cropped";
            search3 = "/api/v2/products?pid=uid5921-39054839-10&fts=jeans" + jeanStyle.toLowerCase() + "+distressed";
            //if user prefers cropped over distressed, weighted random for distressed is 20%, and cropped is %30
            if(favoriteCroppedDistress){
                search4 = search2;
            }
            //if user prefers cropped over distressed, weighted random for distressed is 30%, and cropped is %20
            else{
                search4 = search3;
            }
        }


        String[] search = {search0, search1, search2, search3, search4};
        int[] weight = {0, 1, 1, 1, 1, 2, 2, 3, 3, 4};
        //returns search string generated by random index
        return search[weight[random.nextInt(10)]];
    }


    /*  @param search, specifies what type of query is made to ShopStyleApi
        returns JSONObject based on this parameter
     */
    private JSONObject gatherImages(String search) {
        //used to generate random offset for search query and to generate random product index
        Random random = new Random();


        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            int offset = random.nextInt(250);
            HttpGet getPage = new HttpGet(search + "&limit=25&offset=" + offset);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);

            return obj;

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}