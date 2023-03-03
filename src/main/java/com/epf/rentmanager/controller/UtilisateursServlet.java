package com.epf.rentmanager.controller;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UtilisateursServlet extends HttpServlet {

    private ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            request.setAttribute("usersCount", this.clientService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
