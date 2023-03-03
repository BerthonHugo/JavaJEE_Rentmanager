package com.epf.rentmanager.dao;

import com.epf.rentmanager.DataModels.Vehicule;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDao {
	
	private static VehiculeDao instance = null;
	private VehiculeDao() {}

	public static VehiculeDao getInstance() {
		if(instance == null) {
			instance = new VehiculeDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS vehiclesCount FROM Vehicle;";
	
	public long create(Vehicule vehicule) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, vehicule.getConstructeur());
			stmt.setInt(2, vehicule.getNb_places());

			ResultSet rs = stmt.getGeneratedKeys();

			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;

		} catch (SQLException e) {
			throw new DaoException("message");
		}

	}

	public long delete(Vehicule vehicule) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			stmt.setInt(1, vehicule.getId());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("message");
		}
		//return 1 si delete with success - 0 si error
	}

	public Vehicule findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(FIND_VEHICLE_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			int vehicule_id = rs.getInt("id");
			String constructeur = rs.getString("constructeur");
			int nb_places = rs.getInt("nb_places");

			return new Vehicule(vehicule_id,constructeur,nb_places);

		} catch (SQLException e) {
			throw new DaoException("message");
		}
	}

	public List<Vehicule> findAll() throws DaoException {
		List<Vehicule> vehicules = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(FIND_VEHICLES_QUERY);

			while (rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");

				vehicules.add(new Vehicule(id, constructeur, nbPlaces));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("message");
		}

		return vehicules;
		
	}
	public long count() throws DaoException {
		int vehiclesCount=1;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vehiclesCount = rs.getInt(vehiclesCount);
			}

			return vehiclesCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
