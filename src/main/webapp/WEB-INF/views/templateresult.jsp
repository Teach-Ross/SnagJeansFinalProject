<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/13/2017
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag Jeans</title>
</head>
<body>

<form action="templateSave" id="form1">
    Enter Template Name: <input type="text" name="templateName">
    <button type="submit" form="form1" value="Submit">Save Template</button>
</form>

<br>
<br>
<form action="home">
    <input type="submit" value="Discard Changes">
</form>

</body>
</html>
