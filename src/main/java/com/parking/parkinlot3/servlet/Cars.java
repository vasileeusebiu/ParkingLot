package com.parking.parkinlot3.servlet;

import com.parking.parkinlot3.ejb.CarsBean;
import com.parking.parkinlot3.common.CarDto;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Cars", value = "/cars")
public class Cars extends HttpServlet {

    @Inject
    CarsBean carsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalParkingSpots = 20;
        int totalCars = carsBean.getTotalCars();
        int freeParkingSpots = totalParkingSpots - totalCars;

        request.setAttribute("numberOfFreeParkingSpots", freeParkingSpots);

        List<CarDto> cars = carsBean.findAllCars();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String[] carIdsAsString = request.getParameterValues("car_ids");
        if (carIdsAsString != null) {
            List<Long> carIds = new ArrayList<>();
            for (String carIdAsString : carIdsAsString) {
                carIds.add(Long.parseLong(carIdAsString));
            }
            carsBean.deleteCarsByIds(carIds);
        }
        response.sendRedirect(request.getContextPath() + "/cars");
        }
    }