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

</head>
<body>
<h1>Would you like to save or discard your template?</h1>
<br>
<form action="templateSave" id="form1">
    Enter Template Name: <input type="text" name="templateName">
    <button type="submit" form="form1" value="Submit">Save Template</button>
</form>

<br>
<form action="home">
    <input type="submit" value="Discard Changes">
</form>



</body>
</html>
