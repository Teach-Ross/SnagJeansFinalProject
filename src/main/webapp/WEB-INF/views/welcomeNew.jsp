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
    <title>New</title>
</head>
<h1>New User Page</h1>
<body>
<p> UserID: ${message} </p>

<form action="welcomeNew">

    Enter Name: <input type="text" name="name"</input><br>
    Enter Address: <input type="text" name="address"</input> <br>
    Enter City: <input type="text" name="city"</input><br>
    Enter State: <input type="text" name="state"</input><br>
    Enter Zip: <input type="text" name="zip"</input><br>
    Enter Userid: <input type="text" name="userId"</input><br>
    <br>
    <br>
    Enter Type of Form (1/Blank, 2/Build):
           <input type="text" name="template"</input>

    <button type="submit" name="submit">Submit</button>

</form>

</body>
</html>
