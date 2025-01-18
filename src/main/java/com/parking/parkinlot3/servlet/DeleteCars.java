package com.parking.parkinlot3.servlet;

import com.parking.parkinlot3.ejb.CarsBean;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(name = "DeleteCars", value = "/deleteCars")
public class DeleteCars extends HttpServlet {

    @EJB
    private CarsBean carsBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obține lista de ID-uri ale mașinilor selectate
        String[] carIds = request.getParameterValues("carIds");

        if (carIds != null && carIds.length > 0) {
            // Convertim array-ul de String-uri în Collection<Long>
            Collection<Long> carIdsCollection = Arrays.stream(carIds)
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            // Apelăm metoda pentru a șterge mașinile
            carsBean.deleteCarsByIds(carIdsCollection);
        }

        // Redirecționăm utilizatorul înapoi la pagina de mașini
        response.sendRedirect(request.getContextPath() + "/cars");
    }
}