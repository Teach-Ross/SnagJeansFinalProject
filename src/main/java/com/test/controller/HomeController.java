package com.test.controller;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.DAO.UserDao;
import com.test.model.JeanStyleEnum;
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
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;



@Controller
public class HomeController {

    public UserDao accessUser = new UserDao();

    @RequestMapping("/")

    public ModelAndView landingPage(HttpServletResponse response)
    {

        response.addCookie(new Cookie("userID", "" + "Ross"));


        FBConnection fbConnection = new FBConnection();
        return new
                ModelAndView("landingpage","message", fbConnection.getFBAuthUrl());

    }

    @RequestMapping("welcome2")

    public ModelAndView helloWorld2(@RequestParam("code") String code,
                                    @CookieValue("userID") String userID) {


        if (code == null || code.equals("")) {
            throw new RuntimeException (
                    "ERROR: Didn't get code paramter in callback");
        }

        FBConnection fbConnection = new FBConnection();
        String accessToken = fbConnection.getAccessToken(code);

        FBGraph fbGraph = new FBGraph(accessToken);
        String graph = fbGraph.getFBGraph();
        Map fbProfileData = fbGraph.getGraphData(graph);

        String out = "";
        out = out.concat("<h1>Facebook Login using Java</h1>");
        out = out.concat("<h2>Application Main Menu</h2>");
        // out = out.concat("<div>Welcome "+fbProfileData.get("name"));
        // out = out.concat("<div>Your Email: "+fbProfileData.get("email"));
        // out = out.concat("<div>You are "+fbProfileData.get("gender"));;
        out = out.concat("<div>You are "+fbProfileData.get("id"));

        String saveUserID = fbProfileData.get("id").toString();


        if (accessUser.userIdExists(saveUserID)) {
            return new
            ModelAndView("welcomeExists","message",out);
        }
        else {
            return new
            ModelAndView("welcomeNew","message",out);
        }


    }

    //Create UserEntity and insert into database
    @RequestMapping("welcomeNew")

    public String welcomeNew(){
        return "welcomeNew";
    }
    //asks user if they would like to create blank template or build template from inspiration
    @RequestMapping("newTemplate")
    public String newTemplate(){
        return "newTemplate";
    }

    //pulls users old templates from database, allows user to select one to edit
    @RequestMapping("editTemplate")
    public String editTemplate(){
        return "editTemplate";
    }

    @RequestMapping("build")
    public String jeansImages(Model model)
    {
        //used to generate random offset for search query and to generate random product index
        Random random = new Random();

        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            int offset = random.nextInt(500);
            HttpGet getPage = new HttpGet("/api/v2/products?pid=uid5921-39054839-10&fts=jeans&limit=25&offset=" + offset);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);


            JSONArray ar = obj.getJSONArray("products");

            //used to store useful

            //identify random product
            int[] numbers = random.ints(0,24).distinct().limit(3).toArray();
            JSONObject productObject = ar.getJSONObject(numbers[0]);
            String image1 = productObject.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
            int id1 = productObject.getInt("id");
            String idString =  Integer.toString(id1);
            ArrayList<String> jean1 = new ArrayList<String>();
            jean1.add(image1);
            jean1.add(idString);

            model.addAttribute("image1", image1);
            model.addAttribute("id1", id1);
            model.addAttribute("jean1", jean1);


            JSONObject productObject2 = ar.getJSONObject(numbers[1]);
            String image2 = productObject2.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
            int id2 = productObject2.getInt("id");
            String idString2 =  Integer.toString(id2);
            ArrayList<String> jean2 = new ArrayList<String>();
            jean2.add(image2);
            jean2.add(idString2);

            model.addAttribute("image2", image2);
            model.addAttribute("id2", id2);
            model.addAttribute("jean2", jean2);

            JSONObject productObject3 = ar.getJSONObject(numbers[2]);
            String image3 = productObject3.getJSONObject("image").getJSONObject("sizes").getJSONObject("XLarge").getString("url");
            int id3 = productObject3.getInt("id");
            String idString3 =  Integer.toString(id3);
            ArrayList<String> jean3= new ArrayList<String>();
            jean3.add(image3);
            jean3.add(idString3);

            model.addAttribute("image3", image3);
            model.addAttribute("id3", id3);
            model.addAttribute("jean3", jean3);



            return "welcome";

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        return "welcome";
    }


    @RequestMapping("result")

    public String getResult(Model model, @RequestParam("id") String productId) {
        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            String productUrl = "http://api.shopstyle.com/api/v2/products/" + productId + "?pid=uid5921-39054839-10";
            HttpGet getPage = new HttpGet(productUrl);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);



            //gather product name and description
            model.addAttribute("name", obj.getString("brandedName"));
            model.addAttribute("description", obj.getString("description"));

            //gather product category
            JSONArray categoryArray = obj.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject tag = categoryArray.getJSONObject(i);
                String name = "categoryName" + i;
                model.addAttribute(name, tag.getString("name"));
            }

            JSONArray colorArray = obj.getJSONArray("colors");
            JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
            model.addAttribute("color", color.getString("name"));

            for(int i = 0; i < colorArray.length(); i++){
                JSONObject color2 = colorArray.getJSONObject(i);
                JSONArray ar = color2.getJSONArray("canonicalColors");
                for(int j = 0; j < ar.length(); j++){
                    String colorName = "colorName" + j;
                    model.addAttribute(colorName, ar.getJSONObject(j).getString("name"));
                }

            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "result";
    }

    @RequestMapping("result2")

    public String getResult2(Model model, @RequestParam("image") String imageUrl) {
        String apiKey = "acc_9cf903d4cf36e57",
                apiSecret = "d8254b91c035c098d5a35a93190609a7";


        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> respoonse2 = Unirest.get("https://api.imagga.com/v1/colors")
                    .queryString("url", imageUrl)
                    .basicAuth(apiKey, apiSecret)
                    .header("Accept", "application/json")
                    .asJson();

            JSONArray obj2 = respoonse2.getBody().getObject().getJSONArray("results");

            JSONArray obj = respoonse2.getBody().getObject().getJSONArray("results").getJSONObject(0).getJSONObject("info").getJSONArray("foreground_colors");

            model.addAttribute("percent", obj.getJSONObject(0).getInt("percentage"));
            model.addAttribute("colorName", obj.getJSONObject(0).getString("closest_palette_color"));
            model.addAttribute("colorHtml", obj.getJSONObject(0).getString("html_code"));


        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return "result2";
    }

    @RequestMapping("template")
    public String displayTemplate(Model model){



        model.addAttribute("list", JeanStyleEnum.values());


        return "template";
    }

    @RequestMapping("template2")
    public String displayTemplate(Model model,
    @RequestParam("jean") ArrayList<String> jean) {

        String imageUrl = jean.get(0).substring(1);
        int length = jean.get(1).length();
        String productId = jean.get(1).substring(0, length-1);
        String htmlColor = getHtml(imageUrl);
        model.addAttribute("htmlColor", htmlColor);

        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            String productUrl = "http://api.shopstyle.com/api/v2/products/" + productId + "?pid=uid5921-39054839-10";
            HttpGet getPage = new HttpGet(productUrl);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);


            //gather product name and description
            model.addAttribute("name", obj.getString("brandedName"));
            model.addAttribute("description", obj.getString("description"));

            //gather product category
            JSONArray categoryArray = obj.getJSONArray("categories");
            String category = categoryArray.getJSONObject(0).getString("name");
            String brandedName = obj.getString("brandedName");
            String description = obj.getString("description");

            JeanStyleEnum styleEnum = getStyle(description, category);
            boolean cropped = checkCropped(description, category, brandedName);
            boolean distress = checkDistress(description, category);
            model.addAttribute("style", styleEnum);
            model.addAttribute("list", JeanStyleEnum.values());
            model.addAttribute("cropped", cropped);
            model.addAttribute("distress", distress);

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject tag = categoryArray.getJSONObject(i);
                String name = "categoryName" + i;
                model.addAttribute(name, tag.getString("name"));
            }

            JSONArray colorArray = obj.getJSONArray("colors");
            JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
            model.addAttribute("color", color.getString("name"));

            for(int i = 0; i < colorArray.length(); i++){
                JSONObject color2 = colorArray.getJSONObject(i);
                JSONArray ar = color2.getJSONArray("canonicalColors");
                for(int j = 0; j < ar.length(); j++){
                    String colorName = "colorName" + j;
                    model.addAttribute(colorName, ar.getJSONObject(j).getString("name"));
                }

            }






        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "template";
    }

    @RequestMapping("gather")
    public String displayTemplate2(
            @RequestParam("waistsize") int waistSize,
            @RequestParam("inseamsize") int inseamSize,
            @RequestParam("style") JeanStyleEnum styleEnum,
            @RequestParam("color") String htmlColor,
            @RequestParam("cropped") String cropped,
            @RequestParam("distress") String distress,
            @CookieValue("userID") String userId,
            Model model){

        model.addAttribute("out1", styleEnum.toString());
        model.addAttribute("out2", waistSize);
        model.addAttribute("out3", inseamSize);
        model.addAttribute("out4", htmlColor);
        model.addAttribute("out5", cropped);
        model.addAttribute("out6", distress);
        model.addAttribute("userId", userId);




        return "templateresult";
    }
/*        try {
            //provides access to by sending requests through http protocol to other http servers
            HttpClient http = HttpClientBuilder.create().build();

            //address to call, port 80 is a default
            //port number 443 for https connection (usually)
            HttpHost host = new HttpHost("api.shopstyle.com", 80, "http");

            //reference to location we are trying to retrieve data from
            String productUrl = "http://api.shopstyle.com/api/v2/products/" + productId + "?pid=uid5921-39054839-10";
            HttpGet getPage = new HttpGet(productUrl);

            //execute HTTP request and get HTTP response back
            HttpResponse resp = http.execute(host, getPage);

            // Put the JSON to a string object
            String jsonString = EntityUtils.toString(resp.getEntity());
            JSONObject obj = new JSONObject(jsonString);



            //gather product name and description
            model.addAttribute("name", obj.getString("brandedName"));
            model.addAttribute("description", obj.getString("description"));

            //gather product category
            JSONArray categoryArray = obj.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject tag = categoryArray.getJSONObject(i);
                String name = "categoryName" + i;
                model.addAttribute(name, tag.getString("name"));
            }

            JSONArray colorArray = obj.getJSONArray("colors");
            JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
            model.addAttribute("color", color.getString("name"));

            for(int i = 0; i < colorArray.length(); i++){
                JSONObject color2 = colorArray.getJSONObject(i);
                JSONArray ar = color2.getJSONArray("canonicalColors");
                for(int j = 0; j < ar.length(); j++){
                    String colorName = "colorName" + j;
                    model.addAttribute(colorName, ar.getJSONObject(j).getString("name"));
                }

            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "result";
    }*/

    public JeanStyleEnum getStyle(String description, String category) {

        //matches category against JeanStyleEnum Enum directly
        for (JeanStyleEnum style : JeanStyleEnum.values()) {
            if (category.contains(style.toString())) {
                return style;
            }
        }
        //matches for keywords in description to find JeanStyleEnum
        String d = description.toLowerCase();
        if (d.contains("skinny") | d.contains("leggings")) {
            return JeanStyleEnum.SKINNY;
        } else if (category.contains("Classic") | d.contains("straight")) {
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

    public String getHtml(String imageUrl){

        String apiKey = "acc_9cf903d4cf36e57",
                apiSecret = "d8254b91c035c098d5a35a93190609a7";
        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> respoonse2 = Unirest.get("https://api.imagga.com/v1/colors")
                    .queryString("url", imageUrl)
                    .basicAuth(apiKey, apiSecret)
                    .header("Accept", "application/json")
                    .asJson();

            JSONArray obj2 = respoonse2.getBody().getObject().getJSONArray("results");

            JSONArray obj = respoonse2.getBody().getObject().getJSONArray("results").getJSONObject(0).getJSONObject("info").getJSONArray("foreground_colors");

            return obj.getJSONObject(0).getString("html_code");

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkCropped(String description, String category, String brandedName){
        if(category.contains("Cropped")){
            return true;
        }

        String b = brandedName.toLowerCase();
        if(b.contains("ankle")|b.contains("crop")|b.contains("capri")|b.contains("cutoff")){
            return true;
        }

        String d = description.toLowerCase();
        if(d.contains("ankle")|d.contains("crop")|d.contains("capri")|d.contains("cutoff")){
            return true;
        }



        return false;
    }

    private boolean checkDistress(String description, String category) {
        if (category.contains("Distressed")) {
            return true;
        }
        String d = description.toLowerCase();
        if (d.contains("hole") | d.contains("distress") | d.contains("shred") |d.contains("rip") |d.contains("destroy")){
            return true;
        }
        return false;
    }


}
