<%--
  Created by IntelliJ IDEA.
  User: rteach
  Date: 3/15/2017
  Time: 7:43 PM
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
        <p>Create a new Jeans Template:</p>
      <form action="templateBlank">
        <!--input type="submit" value="Create Template From Scratch" -->
          <button type="submit">
           <img
            src="webresources/images/Create_Template_From_Scratch_01.png"
                width="500" height="150" alt="submit" /></button>
      </form>
      <form action="templateBuild">
        <!-- input type="submit" value="Create Template From Inspiration" -->
          <button type="submit">
              <img
                src="webresources/images/Create_A_New_Template_02_Empty.png"
             width="500" height="150" alt="submit" /></button>
     </form>
        <br><br>
    </div>
</div>
<!-- div id="right"
<iframe width="1200" height="615"
        src="http://www.glamour.com/story/flattering-jeans-reese-witherspoon-kendall-jenner-denim"
        frameborder="0" allowfullscreen></iframe>
</div-->

</body>
</html>
