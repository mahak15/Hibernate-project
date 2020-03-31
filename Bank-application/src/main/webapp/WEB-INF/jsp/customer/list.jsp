<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer</title>
<%@ page isELIgnored="false" %>
</head>
<body>


 <table width="100%" border="1">
  <tr>

   <td>Customer ID</td>
   <td>Customer Name</td>

  </tr>
  <c:forEach items="${customerlist}" var="customer" >
   <tr>

    <td><c:out value="${customer.idcustomer}"/></td>
    <td><c:out value="${customer.name}"/></td>

   </tr>
  </c:forEach>
 </table>


</body>
</html>
