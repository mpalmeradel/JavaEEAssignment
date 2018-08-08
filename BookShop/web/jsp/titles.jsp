<%@ taglib uri="/WEB-INF/tlds/BookShopTags.tld" prefix = "bookShopTags"  %>

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<p>
  <%@ page import="model.*" %>
  <%@ page import="java.util.*"%>
  <%@ page import="java.text.*"%>
</p>
<div>
    <jsp:include page="header.jsp" />
</div>
<table width="38%" border="0" cellspacing="1" cellpadding="0" height="53" align="left">
  
    <bookShopTags:DisplayCartSmall />
    
<p>&nbsp;</p><p>&nbsp;</p>
<p>&nbsp;</p><hr>
<h2 align="center">WELCOME TO THE ONLINE BOOKSHOP</h2>
<form name="form1" method="post" action="./books">
 <input type="hidden" name="action" value="add_to_cart">
  
 <bookShopTags:BookSelection />
    
</form>
<p><a href="./books?action=view_cart">View Shopping Cart</a></p>

<div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>
