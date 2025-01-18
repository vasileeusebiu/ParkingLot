<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="About Parking Lot">
    <h1>About Parking Lot</h1>
    <h2>Registered Cars</h2>

    <div class="container text-center mt-3">
        <c:forEach var="car" items="${licensePlates}">
            <div class="row mb-3">
                <div class="col">${car}</div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>
