package com.epf.rentmanager.service;

import com.epf.rentmanager.DataModels.Reservation;
import com.epf.rentmanager.dao.ReservationDao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        try {
            return ReservationDao.getInstance().create(reservation);
        } catch (DaoException e) {
            throw new ServiceException("message");
        }
    }


    public List<Reservation> findAll() throws ServiceException {
        try {
            return ReservationDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("message");
        }
    }

    public Reservation findById(long id) throws ServiceException {
        try {
            return ReservationDao.getInstance().findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("message");
        }
    }
    public long count() throws ServiceException {
        try {
            return ReservationDao.getInstance().count();
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
