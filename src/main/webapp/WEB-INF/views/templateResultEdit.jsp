<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/16/2017
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag-templateResultEdit</title>

    <link rel="stylesheet" type="text/css"
          href="../webresources/css/normalize.css">

    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalprojectv1.css">

    <style>
        .buildview {
        position: relative;
        background-color: #1F467F;
        opacity: .8;
        width: 120%;
        margin-left: -110%;
        margin-top: -71%;
        border-bottom: 10%;

        /* filter: alpha(opacity=60); /* For IE8 and earlier */
        color: #ffffff ;
        font-size: 40px;
        font-weight: bold;
        }
        #form1 {
            text-align: center;
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
            <p>Would you like to update, discard, or save template as new?</p>


            <form action="templateSave" id="form1">
                    Enter Template Name: <input type="text" name="templateName">
                </form>

               <!--button type="submit" form="form1"
                    value="Submit">Save As New Template</button -->

            <br>
            <button type="submit" form="form1" value="Submit">
                 <img
                    src="webresources/images/Save_Template_01_Empty.png"
                    width="250" height="70" alt="submit" /></button>
                 <br>

            <!--form action="templateSave">
                 <div class="enter">
                      <input type="hidden" name="templateId"
                             value="${templateId}">
                      <input type="submit" value="Update Template"></div>
            </form -->

            <form action="templateSave">
                <div class="enter">
                <input type="hidden" name="templateId"
                      value="${templateId}">
                <!--input type="submit" value="Update Template"></div-->

                <button type="submit" form="form1" value="Submit">
                <img
                   src="webresources/images/Update_Template_02_Empty.png"
                   width="250" height="70" alt="submit" /></button>
                <br>
            </form>



           <!-- input type="submit" value="Discard Changes" -->
           <form action="home">
               <button type="submit" value="Discard Changes">
                  <img
                     src="webresources/images/Discard_Changes_02_Empty.png"
                     width="250" height="70" alt="submit" /></button>
           </form>
           <br>
        </div>
</div>
</body>

</html>
