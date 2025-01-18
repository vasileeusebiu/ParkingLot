package com.parking.parkinlot3.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.util.List;
import com.parking.parkinlot3.ejb.CarsBean;

public class AboutServlet extends HttpServlet {
    @EJB
    private CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> licensePlates = carsBean.getAllLicensePlates();
        if (licensePlates != null) {
            for (String licensePlate : licensePlates) {
                System.out.println("License Plate: " + licensePlate);
            }
        }
        request.setAttribute("licensePlates", licensePlates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/about.jsp");
        dispatcher.forward(request, response);
    }}
