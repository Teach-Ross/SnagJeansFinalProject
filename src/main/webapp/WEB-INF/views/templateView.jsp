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
            <c:choose>
                <c:when test="${template.cropped != 0}">
                    <td>YES</td>
                </c:when>
                <c:otherwise>
                    <td>NO</td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${template.distressed != 0}">
                    <td>YES</td>
                </c:when>
                <c:otherwise>
                    <td>NO</td>
                </c:otherwise>
            </c:choose>
            <td style="background-color:${template.color};"><b></b></td>
            <td>${template.waistSize}</td>
            <td>${template.inseamLength}</td>
            <td>$ ${template.price}</td>
            <td><a href="selectTemplate?id=${template.templateId}"> Select </a></td>
            <td><a href="deleteTemplate?id=${template.templateId}"> Delete </a></td>
        </tr>

    </c:forEach>


</table>

<form action="templateBlank">
    <input type="submit" value="Create New Template From Scratch">
</form>
<form action="templateBuild">
    <input type="submit" value="Create New Template From Inspiration">
</form>

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
