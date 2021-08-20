<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Boot provaREST</title>
</head>


<div class="container">
    <h1>Spring Boot Web JSP Example</h1>
    
    
    <div>
   	    <form method="POST" action="${pageContext.request.contextPath}/chiamaRest">
               <input type="submit" value="chiama Rest">
        </form>
    </div>
    
    <div>
   	    <form method="POST" action="${pageContext.request.contextPath}/chiamaRestStatus">
               <input type="submit" value="chiama Rest con status">
        </form>
    </div>
</div>

<body>

</body>
</html>