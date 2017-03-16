<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 1/12/2017
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Snag Jeans</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Template Name</th>
        <th>Jean Style</th>
        <th>Cropped</th>
        <th>Distressed</th>
        <th>Jean Color</th>
        <th>Waist Size</th>
        <th>Inseam Length</th>
        <th>Price</th>
    </tr>
    <c:forEach var="template" items="${templateList}">
        <tr>
            <td>${template.templateName}</td>
            <td>${template.jeanStyle}</td>
            <c:if test="${message.message != null}">
                <div class="msg">test1: ${message.message}</div>
            </c:if>
            <c:if test="${message.message != ''}">
                <div class="msg">test2: ${message.message}</div>
            </c:if>
            <td id="cropped">${template.cropped}</td>
            <td id="distress"> ${template.distressed}</td>
            <td style="background-color:${template.color};"><b></b></td>
            <td>${template.waistSize}</td>
            <td>${template.inseamLength}</td>
            <td>$ ${template.price}</td>
            <td><a href="selectTemplate?id=${template.templateId}"> Select </a></td>
            <td><a href="deleteTemplate?id=${template.templateId}"> Delete </a></td>
        </tr>

    </c:forEach>


</table>
</body>

<%--<script>
    var crop = document.getElementById("cropped").innerHTML
    if(crop === "0" ){
        document.getElementById("cropped").innerHTML = "NO";
    }
    if(crop === "1"){
        document.getElementById("cropped").innerHTML = "YES";
    }


</script>--%>
</html>
