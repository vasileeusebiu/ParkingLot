<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <table class="table">
        <tbody>
        <c:forEach var="user" items="${users}">
                <td>${user.username}</td>
                <td>${user.email}</td>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>