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

 <a href="/SpringHibernateExample/customer/add">Add new Customer</a>

 <table width="100%" border="1">
  <tr>
   <td>ID</td>
   <td>balance</td>
   <td>customer ID</td>
   <td>branch ID</td>

  </tr>
  <c:forEach items="${accountslist}" var="account" >
   <tr>
    <td><c:out value="${account.idacc}"/></td>
    <td><c:out value="${account.balance}"/></td>
    <td><c:out value="${account.branch.getIdbranch()}"/></td>
    <td><c:out value="${account.customer.getIdcustomer()}"/></td>

   </tr>
  </c:forEach>
 </table>


</body>
</html>
