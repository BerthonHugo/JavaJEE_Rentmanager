package com.epf.rentmanager.ui;

import com.epf.rentmanager.DataModels.Client;
import com.epf.rentmanager.DataModels.Vehicule;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehiculeService;

import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        System.out.println(new Client(12, "Mi","Me","mi.me@me.com", LocalDate.now()));
        try{
            System.out.println("FINDALL CLIENTS : " + ClientService.getInstance().findAll());
        }catch (ServiceException e){
            e.printStackTrace();
        }
        try{
            System.out.println("FIND CLIENT BY ID : " + ClientService.getInstance().findById(2));
        }catch (ServiceException e){
            e.printStackTrace();
        }

        try {
            System.out.println("FINDALL VEHICLES : " + VehiculeService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("FIND VEHICLE BY ID : " + VehiculeService.getInstance().findById(2));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }
}
