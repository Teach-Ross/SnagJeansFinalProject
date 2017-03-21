<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/18/2017
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag-templateBuildPreferences</title>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalprojectv1.css">

    <style>
        .buildpreference {
            position: relative;
            background-color: #1F467F;
            opacity: 0.9;
            width: 55%;
            margin-left: 2%;
            margin-top: -32%;
            border-bottom: 10%;

            /* filter: alpha(opacity=60); /* For IE8 and earlier */
            color: #ffffff ;
            font-size: 40px;
            font-weight: bold;
        }
        h3 {
            font-size: 40px;
        }

        h4 {
            text-align: center;
            font-size: 35px;
        }

    </style>
</head>
<body>

<div class="background">
    <div class="transbox">
        <img src="/webresources/images/Snag_Logo_R2_Version5.svg" alt="Snag Logo"
             height="300px" width="500px">
        <h3>Build It. Buy It. Own It.</h3>
    </div>
</div>

    <div class="buildpreference">
         <div>
             <h4>The below jeans were selected based on your previous experiences:</h4>

            <form action="templateBuildResult">

                <button type="submit"
                     name="jean" value="${jean1}"><img src="${image1}"
                                                   alt="jeans1"></button>
                <button type="submit"
                     name="jean" value="${jean2}"><img src="${image2}"
                                                   alt="jeans2"></button>
                <button type="submit"
                     name="jean" value="${jean3}"><img src="${image3}"
                                                   alt="jeans3"></button>
            </form>
            <form action="templateBuildPreferences">
                 <!-- input type="submit" value="Next Three Images" -->
                 <button type="submit">
                     <img
                        src="webresources/images/Next_Three_Images.png"
                        width="250" height="70" alt="submit" /></button>
             </form>
             <br>
    </div>
</div>



</body>
</html>
