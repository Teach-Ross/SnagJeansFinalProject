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
    <title>Snag-templateView</title>

    <link rel="stylesheet" type="text/css"
          href="../webresources/css/normalize.css">

    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
    <style>
        .buildview {
            position: relative;
            background-color: #1F467F;
            opacity: .8;
            width: 140%;
            margin-left: -70%;
            margin-top: -71%;
            border-bottom: 10%;

            /* filter: alpha(opacity=60); /* For IE8 and earlier */
            color: #ffffff ;
            font-size: 40px;
            font-weight: bold;
        }
        .transbox_innernew {
            position: relative;
            background-color: #ffffff;
            opacity: .9;
            width: 95%;
            margin-left: 3%;
            margin-top: 5%;
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

        p{
            font-size: 30px;
        }

        #select1 {
            font-size: 23px;
        }
        #select2 {
            font-size: 23px;
        }
        #style {
            font-size: 23px;
        }
        #cropped {
            font-size: 23px;
        }
        #distress {
            font-size: 23px;
        }
        #rect {
            background-color: ${color};
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

    <div class="buildview">
        <br>
        <p>Create a new jean template though selection of one of the below
            options:</p>
        <div class="transbox_innernew">

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
        </div>
            <br>
            <form action="templateBlank">
                <!--input type="submit" value="Create Template From Scratch" -->
                <button type="submit">
                    <img
                            src="webresources/images/Create_Template_From_Scratch_01.png"
                            width="300" height="75 alt="submit" /></button>
            </form>
            <form action="templateBuild">
                <!-- input type="submit" value="Create Template From Inspiration" -->
                <button type="submit">
                    <img
                            src="webresources/images/Create_A_New_Template_02_Empty.png"
                            width="300" height="75" alt="submit" /></button>
            </form>
            <form action="templateBuildPreferences">
            <!-- input type="submit" value="Create Template From Inspiration" -->
               <button type="submit">
                  <img
                           src="webresources/images/Create_A_New_Template_03_Empty.png"
                           width="300" height="75" alt="submit" /></button>
           </form>
           <br>
        </div>
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
