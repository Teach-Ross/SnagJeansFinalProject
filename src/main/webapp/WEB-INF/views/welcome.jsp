<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 1/12/2017
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>
<body>
<form action="template2">

<button type="submit" name="jean" value="${jean1}"><img src="${image1}" alt="jeans1"></button>
<button type="submit" name="jean" value="${jean2}"><img src="${image2}" alt="jeans2"></button>
<button type="submit" name="jean" value="${jean3}"><img src="${image3}" alt="jeans3"></button>
</form>


<form action="result2">
    <input type="image" src="${image1}" alt="Submit" name="image" value="${image1}">
    <input type="image" src="${image2}" alt="Submit" name="image" value="${image2}">
    <input type="image" src="${image3}" alt="Submit" name="image" value="${image3}">
</form>

</body>
</html>
