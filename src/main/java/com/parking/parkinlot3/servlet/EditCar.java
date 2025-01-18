package com.parking.parkinlot3.servlet;

import com.parking.parkinlot3.common.CarDto;
import com.parking.parkinlot3.common.UserDto;
import com.parking.parkinlot3.ejb.CarsBean;
import com.parking.parkinlot3.ejb.UsersBean;
import com.parking.parkinlot3.entites.User;
import com.parking.parkinlot3.entites.Car;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid car ID.");
            return;
        }

        try {
            Long carId = Long.parseLong(idParam);
            CarDto car = carsBean.findById(carId);
            if (car == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found.");
                return;
            }

            request.setAttribute("car", car);
            List<UserDto> users = usersBean.findAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid car ID format.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long ownerId = Long.parseLong(request.getParameter("owner_id"));
        Long carId = Long.parseLong(request.getParameter("car_id"));

        carsBean.updateCar(carId, licensePlate, parkingSpot, ownerId); // actualizează mașina

        response.sendRedirect(request.getContextPath() + "/cars");
    }
}