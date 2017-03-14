
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
    <title>Title</title>

</head>
<body>

<form action="gather" method="post">
<select name="waistsize" id="select1">
    <option>Select Waist Size</option>
</select>
<select name="inseamsize" id="select2">
    <option>Select Inseam Size</option>
</select>

<select name="style" id="styleList">
    <option selected="${style}" value="">Select Jean Style</option>
    <c:set var="test1" value="${style}"/><c:forEach items="${list}" var="option">
    <option value="${option}">
        <c:out value="${option}"></c:out>
    </option>
</c:forEach>

</select>
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


<script src="jscolor.js"></script>
<p>Select Fabric Swatch:
    <input class="jscolor {onFineChange:'update(this)'}" value="cc66ff">

<p id="rect" style="border:1px solid gray; width:161px; height:100px;">











</body>
<script src="jscolor.js"></script>
<script>
    function update(jscolor) {
        // 'jscolor' instance can be used as a string
        document.getElementById('rect').style.backgroundColor = '#' + jscolor
    }
</script>
<script>

    var e = document.getElementById("styleList");
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



 /*   var select = document.getElementById("select1");

    for(var i = 20; i <= 40; i += 2) {
        var opt = i;
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        el.name = "waistsize";
        select.appendChild(el);
    }

    var select = document.getElementById("select2");

    for(var i = 20; i <= 40; i += 2) {
        var opt = i;
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        el.name = "inseamsize";
        select.appendChild(el);
    }*/
</script>

</html>

