<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/15/2017
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag Jeans</title>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>
<body>
<h1>Please complete your jean template below:</h1>
<br>
<form action="gather" method="get">
    <input id="templateId" type="hidden" name="templateId" value="0">
    <input type="hidden" name="templateName" value="">
    <select name="waistsize" id="select1">
        <option value="null">Select Waist Size</option>
    </select>

    <select  name="inseamsize" id="select2">
        <option value="null">Select Inseam Size</option>
    </select>
    <select name="style" id="style">
        <option value="null">Select Jean Style</option>
        <c:set var="test1" value=""/><c:forEach items="${list}" var="option">
        <option value="${option}">
            <c:out value="${option}"></c:out>
        </option>
    </c:forEach>
    </select>


    <input type='hidden' value="" name="cropped">
    <input type="checkbox" name="cropped" id="cropped" value="1">
    <label for="cropped">Cropped</label>

    <input type='hidden' value="" name="distress">
    <input type="checkbox" name="distress" id="distress" value="1">
    <label for="distress">Distressed</label>


    <p>Select Fabric Swatch:
        <input name="color" class="jscolor {onFineChange:'update(this)'}">
    <p id="rect" style="border:1px solid gray; width:161px; height:100px;"></p>

    <input type="submit" value="Submit" onclick="return Validate()">
    <div style="color:red;"id="errors"></div>
</form>

</body>
<script>

    function Validate() {
        var test = document.getElementById("select1");
        var test2 = document.getElementById("select2");
        var test3 = document.getElementById("style")
        if (test.value == "null" || test2.value == "null" || test3.value == "null") {
            //If the "Please Select" option is selected display error.
           document.getElementById("errors").innerHTML="Please Select All Options";
            return false;
        }
        return true;
    }

</script>

<script>
    function dropDown(id, min, max){
        var select = document.getElementById(id);
        for(var i = min; i <= max; i++) {
            var opt = i;
            var el = document.createElement("option");
            el.textContent = opt;
            el.value = opt;
            select.appendChild(el);
        }
    }

    dropDown("select1", 20, 40);
    dropDown("select2", 20, 40);
</script>

<script src="jscolor.js"></script>

<script>
    function update(jscolor) {
        // 'jscolor' instance can be used as a string
        document.getElementById('rect').style.backgroundColor = '#' + jscolor
    }
</script>
</html>
