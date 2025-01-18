package com.parking.parkinlot3.servlet;

import com.parking.parkinlot3.common.UserDto;
import com.parking.parkinlot3.ejb.CarsBean;
import com.parking.parkinlot3.ejb.UsersBean;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "AddCar", value = "/AddCar")
public class AddCar extends HttpServlet {
    @EJB
    private UsersBean usersBean;
    @EJB
    private CarsBean carsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp").forward(request, response);
      }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        String ownerId = request.getParameter("owner_id");


        Long userId = null;
        if (ownerId != null && !ownerId.isEmpty()) {
            try {
                userId = Long.valueOf(ownerId); // Convertim ownerId în Long
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid owner ID.");
                request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp").forward(request, response);
                return;
            }
        }



        if (userId != null) {
            carsBean.createCar(licensePlate, parkingSpot, userId);
        } else {
            request.setAttribute("error", "Owner is required.");
            request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/cars");
    }
}
