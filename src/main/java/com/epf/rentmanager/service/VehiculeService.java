package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.DataModels.Vehicule;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.dao.VehiculeDao;

public class VehiculeService {

	private VehiculeDao vehiculeDao;
	public static VehiculeService instance;
	
	private VehiculeService() {
		this.vehiculeDao = VehiculeDao.getInstance();
	}
	
	public static VehiculeService getInstance() {
		if (instance == null) {
			instance = new VehiculeService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicule vehicule) throws ServiceException{
		try {
			return VehiculeDao.getInstance().create(vehicule);
		} catch (DaoException e) {
			throw new ServiceException("message");
		}
	}

	public Vehicule findById(long id) throws ServiceException {

		try {
			return VehiculeDao.getInstance().findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("message");
		}
	}

	public List<Vehicule> findAll() throws ServiceException {

		try {
			return VehiculeDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("message");
		}
	}

	public long count() throws ServiceException {
		try {
			return VehiculeDao.getInstance().count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
