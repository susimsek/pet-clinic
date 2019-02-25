<%--
  Created by IntelliJ IDEA.
  User: Suayb
  Date: 25.02.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--@elvariable id="owner" type=""--%>
<form:form modelAttribute="owner" method="post">
    Fist Name :<form:input path="firstName"/>
    <form:errors path="firstName" cssStyle="color: red"></form:errors>
    <br/>
    Last Name :<form:input path="lastName"/>
    <form:errors path="lastName" cssStyle="color: red"></form:errors>
    <br/>
    <form:button name="submit">Create</form:button>
</form:form>

</body>
</html>
