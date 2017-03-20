<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: mpjoh
  Date: 3/14/2017
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
     <!--spring:url value="resources/css/snagjeansfinalproject.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" /-->
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta name="viewport" content="width=device-width">
    <title>Snag Jeans</title>

    <link rel="stylesheet" type="text/css"
          href="../webresources/css/snagjeansfinalproject.css">
</head>
<body>

<div class="background">
   <div class="transbox">
       <img src="/webresources/images/Snag_Logo_R2_Version5.svg" alt="Snag Logo"
            height="400px" width="800px">
       <h3>Build It. Buy It. Own It.</h3>
      <a id="FB_Login" href="${message}">FB Login!</a>
   </div>
</div>

</body>
</html>
