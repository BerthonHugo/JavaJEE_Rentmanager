package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.DataModels.Client;
import com.epf.rentmanager.dao.ClientDao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws ServiceException{
		try {
			return ClientDao.getInstance().create(client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("message");
		}
	}

	public Client findById(long id) throws ServiceException {

		try {
			return ClientDao.getInstance().findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("message");
		}
	}

	public List<Client> findAll() throws ServiceException {

		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("message");
		}
	}
	public long count() throws ServiceException {
		try {
			return ClientDao.getInstance().count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
