
<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/13/2017
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
    <title>${htmlColor}</title>

    <style>
        #rect{
            background-color: ${htmlColor};
    </style>


</head>
<body>
<h1>Please complete your jean template below:</h1>
<br>
<form action="gather" method="get">
<select name="waistsize" id="select1">
    <option>Select Waist Size</option>
</select>
<select name="inseamsize" id="select2">
    <option>Select Inseam Size</option>
</select>

<select name="style" id="select3">
    <option selected="${style}" value="">Select Jean Style</option>
    <c:set var="test1" value="${style}"/><c:forEach items="${list}" var="option">
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
        <input name="color" class="jscolor {onFineChange:'update(this)'}" value="${htmlColor}">
        <p id="rect" style="border:1px solid gray; width:161px; height:100px;"></p>

    <input type="submit" value="Submit">
</form>

${style}

${name}
<br>
${description}
<br>
${color}
<br>
${colorName0}
<br>
${categoryName0}
<br>
${cropped}
<br>
${distress}















</body>

<script>
    var cropped = "${cropped}";
    var check1 = (cropped === "true");
    document.getElementById("cropped").checked = check1;

    var distress = "${distress}";
    var check2 = (distress === "true");
    document.getElementById("distress").checked = check2;
</script>

<script>
    var e = document.getElementById("select3");
    //e.options[e.selectedIndex].value = "T"; // does not work
    e.value="${style}";

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

