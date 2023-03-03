package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.DataModels.Client;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS clientsCount FROM Client;";
	
	public long create(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getnom());
			stmt.setString(2, client.getPrenom());
			stmt.setString(3, client.getMail());
			stmt.setDate(4, Date.valueOf(client.getNaissance()));
			stmt.execute();
			ResultSet resultSet = stmt.getGeneratedKeys();
			int id = 0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new DaoException("message");

		}

	}
	
	public long delete(Client client) throws DaoException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(DELETE_CLIENT_QUERY);
			stmt.setInt(1, client.getId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("message");
		}

		//return 1 si delete with success - 0 si error

	}

	public Client findById(long id) throws DaoException {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(FIND_CLIENT_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String mail = rs.getString("email");
			LocalDate naissance = rs.getDate("naissance").toLocalDate();

			return new Client(nom,prenom,mail,naissance);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("message");
		}

	}

	public List<Client> findAll() throws DaoException {
		List<Client>array = new ArrayList<Client>();
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet resultat = stmt.executeQuery();

			while(resultat.next()){
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String mail = resultat.getString("email");
				LocalDate naissance = resultat.getDate("naissance").toLocalDate();

				array.add(new Client(id,nom,prenom,mail,naissance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("message");
		}
		return array;
	}

	public long count() throws DaoException {
		int clientsCount = 1;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				clientsCount = rs.getInt(clientsCount);
			}

			return clientsCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
