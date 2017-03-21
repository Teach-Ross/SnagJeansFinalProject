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
    <title>Snag-templateResult</title>

    <link rel="stylesheet" type="text/css"
          href="../webresources/css/normalize.css">

    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalprojectv1.css">

    <style>
        .buildresult {
            position: relative;
            background-color: #1F467F;
            opacity: 0.7;
            width: 50%;
            margin-left: 5%;
            margin-top: -26%;
            border-bottom: 10%;

            /* filter: alpha(opacity=60); /* For IE8 and earlier */
            color: #ffffff ;
            font-size: 40px;
            font-weight: bold;
        }
    </style>
</head>

<div class="background">
    <div class="transbox">
        <img src="/webresources/images/Snag_Logo_R2_Version5.svg" alt="Snag Logo"
             height="300px" width="500px">
        <h3>Build It. Buy It. Own It.</h3>
    </div>
</div>

<body>

<div class="buildresult">
    <div>
        <h4>Would you like to save or discard your template?</h4>
        <p>

        <form action="templateSave" id="form1">
             Enter Template Name: <input type="text" name="templateName">
        </form>

            <form>
            <!--button type="submit" form="form1" value="Submit">
                  Save Template</button -->
            <button type="submit" form="form1" value="Submit">
               <img
                   src="webresources/images/Save_Template_01_Empty.png"
                   width="250" height="70" alt="submit" /></button>
            </form>


        <!-- input type="submit" value="Discard Changes" -->
        <form action="home">
            <button type="submit" value="Discard Changes">
                <img
                    src="webresources/images/Discard_Changes_02_Empty.png"
                    width="250" height="70" alt="submit" /></button>
        </form>
        <br>
</body>
</html>
