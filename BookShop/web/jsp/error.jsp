<html>

<div>
    <jsp:include page="header.jsp" />
</div>
    
<body bgcolor="white">

	<%@ page isErrorPage="true" %>
        <%  String msg = (String)request.getAttribute("result");
	    out.print("<h3>" + msg ); 
            session.invalidate(); %>
	
<div>
    <jsp:include page="footer.jsp" />
</div>            
            
</body>
</html>
