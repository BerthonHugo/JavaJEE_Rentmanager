package com.epf.rentmanager.controller;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehiculeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/details")
public class UsersDetailsServlet extends HttpServlet {

    private ClientService clientService = ClientService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();
    private VehiculeService vehicleService = VehiculeService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            request.setAttribute("reservationsCount", this.reservationService.findResaCountByClientId(id));
            request.setAttribute("vehicleCount",this.reservationService.findResaCountByClientId(id));

            //request.setAttribute("vehicles", this.vehicleService.);
            request.setAttribute("reservations",this.reservationService.findResaByClientId(id));

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }



}