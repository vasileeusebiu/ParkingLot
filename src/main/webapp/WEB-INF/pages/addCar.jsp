<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="AddCar">
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form-validation.css">
    </head>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
        <div class="row">
            <div class="col-md-12 mb-3">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate" name="license_plate"
                       placeholder="Enter License Plate"
                       value="${param.license_plate != null ? param.license_plate : ''}"
                       required/>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>

            <div class="col-md-12 mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot" name="parking_spot"
                       placeholder="Enter Parking Spot"
                       value="${param.parking_spot != null ? param.parking_spot : ''}"
                       required/>
                <div class="invalid-feedback">
                    Parking Spot is required.
                </div>
            </div>

            <div class="col-md-12 mb-3">
                <label for="owner_id">Owner</label>
                <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                    <option value="" disabled selected>Choose...</option>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}" ${param.owner_id != null && user.id == param.owner_id ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select an owner.
                </div>
            </div>

            <input type="hidden" name="car_id" value="${param.car_id}" />
            <div class="col-md-12 mb-3">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        // JavaScript pentru validare formular
        (function() {
            'use strict'
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);

                    form.addEventListener('input', function(event) {
                        if (event.target.checkValidity()) {
                            event.target.classList.remove('is-invalid');
                            event.target.classList.add('is-valid');
                            event.target.nextElementSibling.style.display = 'none';
                        } else {
                            event.target.classList.remove('is-valid');
                            event.target.classList.add('is-invalid');
                            event.target.nextElementSibling.style.display = 'block';
                        }
                    });
                });
            }, false);
        })();
    </script>
</t:pageTemplate>