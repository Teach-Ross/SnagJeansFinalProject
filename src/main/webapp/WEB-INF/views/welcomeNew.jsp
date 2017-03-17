<%--
  Created by IntelliJ IDEA.
  User: mpjoh
  Date: 3/15/2017
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag Jeans</title>
</head>
<h1>New User Page</h1>
<body>
<!-- p> UserID: ${message} </p-->

<form action="welcomeNew">

    Enter Name: <input type="text" name="name"</input> ${errormessage}<br>
    Enter Address: <input type="text" name="address"</input> <br>
    Enter City: <input type="text" name="city"</input><br>
    Enter State: <input type="text" name="state"</input><br>
    Enter Zip: <input type="text" name="zip"</input><br>
    <button type="submit" name="submit">Submit</button>

</form>


</body>
</html>
