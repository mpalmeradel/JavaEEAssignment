

<%@ taglib uri="/WEB-INF/tlds/BookShopTags.tld" prefix = "bookShopTags"  %>

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<%@ page import="model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<div>
    <jsp:include page="header.jsp" />
</div>

<body bgcolor="#FFFFFF" text="#000000">
<h2 align="center"><b>The followings Items are in your shopping cart </b></h2>
<form name="form1" method="post" action="./books">
<input type="hidden" name="action" value="update_cart">
   
    <bookShopTags:DisplayCart />
    
</form>
<p><a href="./books?action=continue">Continue Shopping</a></p>
<p><a href="./books?action=checkout">Check Out</a></p>

<div>
    <jsp:include page="footer.jsp" />
</div>

</body>
</html>
