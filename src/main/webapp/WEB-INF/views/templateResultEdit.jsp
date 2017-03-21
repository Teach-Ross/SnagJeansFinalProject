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

        <div class="transbox_inner">
            <div class="transbox_form">

                <br>
                Would you like to update, discard, or save template as new?<br><br>
                <br>


                    <form action="templateSave">
                        <div class="enter"><input type="hidden" name="templateId" value="${templateId}">
                            <input type="submit" value="Update Template"></div>
                    </form>


                <br>


                    <form action="home">
                        <div class="enter"><input type="submit" value="Discard Changes"></div>
                    </form>

                <br>

                <form action="templateSave" id="form1">
                    <input type="hidden" name="templateId" value="0">
                    <label>Enter Template Name: </label><input type="text" name="templateName">
                    <button type="submit" form="form1" value="Submit">Save As New Template</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>
