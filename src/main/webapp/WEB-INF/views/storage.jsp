<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Storage</title>

    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<%@include file="authheader.jsp" %>

<h1>STORAGE</h1>
<form:form enctype="multipart/form-data"
           action="${pageContext.request.contextPath}/storage/files?${_csrf.parameterName}=${_csrf.token}"
           method="post">
    <input id="file" type="file" name="file"/>
    <input type="submit" value="Send File"/>
    <%--<input type="hidden"--%>
           <%--name="${_csrf.parameterName}"--%>
           <%--value="${_csrf.token}"/>--%>
</form:form>


<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Users </span></div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Type</th>
            <th>Path</th>

            <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                <th>SSO ID</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${files}" var="user">
            <tr>
                <td>${user.type}</td>
                <td>${user.path}</td>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <td>${user.user_id.ssoId}</td>
                </sec:authorize>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
