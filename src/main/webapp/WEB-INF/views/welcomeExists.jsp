<%--
  Created by IntelliJ IDEA.
  User: mpjoh
  Date: 3/15/2017
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Snag Jeans</title>
    <link rel="stylesheet" type="text/css"
          href="webresources/css/snagjeansfinalproject.css">
</head>
<body>

   <div class="transbox_welcome">

       <div class="transbox_inner">
           <br>
           <p> Welcome ${message}!</p>
          <form action="newTemplate">
          <!--button type="submit" value="Create New Template" -->
          <button type="submit">
             <img
             src="webresources/images/Create_New_Template_01_Empty.png"
             width="500" height="150" alt="submit" /></button>
          </form>
          <form action="editTemplate">
          <!-- input type="submit" value="View Existing Template" -->
          <button type="submit">
             <img
              src="webresources/images/Create_Existing_Template_02_Empty.png"
              width="500" height="150" alt="submit" /></button>
          </form>
       <br><br>
       </div>
   </div>
<!--div id="right"
<iframe width="1200" height="615"
        src="http://www.glamour.com/story/flattering-jeans-reese-witherspoon-kendall-jenner-denim"
        frameborder="0" allowfullscreen></iframe>
</div -->

</body>
</html>
