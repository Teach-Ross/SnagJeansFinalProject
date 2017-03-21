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

            <br>
<h1>Would you like to update, discard, or save template as new?</h1>
<br>
<form action="templateSave">
    <input type="hidden" name="templateId" value="${templateId}">
    <input type="submit" value="Update Template">
</form>
<br>
<form action="home">
    <input type="submit" value="Discard Changes">
</form>
<br>
<form action="templateSave" id="form1">
    <input type="hidden" name="templateId" value="0">
    Enter Template Name: <input type="text" name="templateName">
    <button type="submit" form="form1" value="Submit">Save As New Template</button>
</form>

        </div>
    </div>
</div>
</body>

</html>
