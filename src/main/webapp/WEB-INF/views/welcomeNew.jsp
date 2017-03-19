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
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>
<h1>New User Page</h1>
<body>
<!-- p> UserID: ${message} </p-->

<form action="welcomeNew">

    Enter Name: <input type="text" id="name" name="name"</input><br>
    Enter Address: <input type="text" name="address"</input> <br>
    Enter City: <input type="text" name="city"</input><br>
    Enter State: <input type="text" name="state"</input><br>
    Enter Zip: <input type="text" name="zip"</input><br>
    <br>
    <div id="errors"></div>
    <br>
    <input type="submit" value="Submit" onclick="return Validate()"><br>

</form>
</body>
<script>

    function Validate() {
        var test = document.getElementById("name");
        console.log("name =" + test.value + ".");
        if (test.value == "") {
            document.getElementById("errors").
                innerHTML="Name is missing and is a required field";
            return false;
        }
        return true;
    }
</script>
</html>
