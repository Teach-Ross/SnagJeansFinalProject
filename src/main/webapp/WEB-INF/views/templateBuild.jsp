<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 1/12/2017
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag Jeans - TemplateBuild</title>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>
<body>

<div class="buildpreference">
    <div>
      <h1>Select a pair of jeans to use as inspiration:</h1>
    <br>
    <form action="templateBuildResult">

      <button type="submit"
              name="jean" value="${jean1}"><img src="${image1}" alt="jeans1"></button>
      <button type="submit"
              name="jean" value="${jean2}"><img src="${image2}" alt="jeans2"></button>
      <button type="submit"
              name="jean" value="${jean3}"><img src="${image3}" alt="jeans3"></button>
    </form>
    <br>
    <form action="templateBuild">
      <input type="submit" value="Next Three Images">
    </form>
    </div>
</div>

</body>
</html>
