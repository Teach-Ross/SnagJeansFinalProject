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
    <title>Snag-welcomeNew</title>

    <link rel="stylesheet" type="text/css"
          href="../webresources/css/normalize.css">

    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>

<body>
<div class="background">
    <div class="transbox">
        <img src="/webresources/images/Snag_Logo_R2_Version5.svg" alt="Snag Logo"
             height="300px" width="500px">
        <h3>Build It. Buy It. Own It.</h3>
    </div>

    <div class="transbox_new">

            <br>

            <!-- p> UserID: ${message} </p-->

            <div class="transbox_form">

                    <form action="welcomeNew">

                    Please register below:<br><br>

                    <label>Enter Name: </label><input type="text" id="name" name="name"><br>
                    <br>
                    <label>Enter Address: </label><input type="text" name="address"> <br>
                    <br>
                    <label>Enter City: </label><input type="text" name="city"><br>
                    <br>
                    <label>Enter State: </label><input type="text" name="state"><br>
                    <br>
                    <label>Enter Zip: </label><input type="text" name="zip"><br>
                    <br>

                    <button type="submit" value="Submit" onclick="return Validate()">
                                            <img
                                src="webresources/images/Submit_01_Empty.png"
                    width="300" height="75" alt="submit" /></button>
                    <!-- div class="submit"><input type="submit" value="Submit"
                                     onclick="return Validate()"--><br>
                    <div id="errors"></div>

           </div>

                </form>
                <br>

            </div>
        </div>
    </div>
</div>
</body>

<script>

    function Validate() {
        var test = document.getElementById("name");
        console.log("name =" + test.value + ".");
        if (test.value == "") {
            document.getElementById("errors").innerHTML = "Name is missing and is a required field";
            return false;
        }
        return true;
    }
</script>
</html>
