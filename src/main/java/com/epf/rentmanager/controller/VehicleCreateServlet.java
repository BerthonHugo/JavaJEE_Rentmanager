package com.epf.rentmanager.controller;


import com.epf.rentmanager.DataModels.Vehicule;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehiculeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicles/create")
public class VehicleCreateServlet extends HttpServlet {

    private VehiculeService vehicleService = VehiculeService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String constructeur =request.getParameter("manufacturer");
        int nbPlaces = Integer.parseInt(request.getParameter("seats"));

        try {
            vehicleService.create(new Vehicule(0,constructeur,nbPlaces));

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/vehicles");
    }
}
