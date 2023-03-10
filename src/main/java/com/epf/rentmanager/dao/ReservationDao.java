package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.DataModels.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "" +
			"SELECT Reservation.id, Reservation.client_id, Reservation.vehicle_id, Reservation.debut, Reservation.fin, Client.nom, Client.prenom, Vehicle.constructeur " +
			"FROM Reservation " +
			"LEFT JOIN Vehicle ON Reservation.vehicle_id = Vehicle.id " +
			"LEFT JOIN Client ON Reservation.client_id = Client.id " +
			"ORDER BY debut;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS reservationsCount FROM Reservation;";
	private static final String COUNT_RESERVATIONS_BY_CLIENT_QUERY = "SELECT COUNT(id) AS reservationsCount FROM Reservation WHERE client_id=?;";
		
	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, reservation.getClient_id());
			stmt.setInt(2, reservation.getvehicule_id());
			stmt.setDate(3, Date.valueOf(reservation.getDebut()));
			stmt.setDate(4, Date.valueOf(reservation.getFin()));
			stmt.execute();
			ResultSet resultSet = stmt.getGeneratedKeys();

			int id = 0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;

		} catch (SQLException e) {
			throw new DaoException("erreur a eu lieu lors de la creation d'une r??servation");
		}
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			stmt.setInt(1, reservation.getId());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Une erreur a eu lieu lors de la suppression d'une r??servation");
		}
		//return 1 si delete with success - 0 si error
	}

	public Reservation findById(long id) throws DaoException{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATION_QUERY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int reservationClientId = rs.getInt("client_id");
			int reservationVehicleId = rs.getInt("vehicle_id");
			LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
			LocalDate reservationFin = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation((int) id, reservationClientId, reservationVehicleId, reservationDebut, reservationFin);

			return reservation;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		ArrayList<Reservation>array = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			stmt.setLong(1, clientId);
			ResultSet resultat = stmt.executeQuery();
			while(resultat.next()){

				int id = resultat.getInt("id");
				int vehicule_id = resultat.getInt("vehicle_id");
				LocalDate debut = resultat.getDate("debut").toLocalDate();
				LocalDate fin = resultat.getDate("fin").toLocalDate();
				array.add(new Reservation(id,0, vehicule_id, debut, fin));
			}
			System.out.println("Test " + array);
			return array;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Une erreur a eu lieu lors de la r??cup??ration de la r??servation");
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		ArrayList<Reservation>array = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			stmt.setLong(1, vehicleId);
			ResultSet resultat = stmt.executeQuery();
			while(resultat.next()){

				int id = resultat.getInt("id");
				int client_id = resultat.getInt("client_id");
				LocalDate debut = resultat.getDate("debut").toLocalDate();
				LocalDate fin = resultat.getDate("fin").toLocalDate();
				array.add(new Reservation(id, client_id, debut, fin));
			}
			return array;

		} catch (SQLException e) {
			throw new DaoException("Une erreur a eu lieu lors de la r??cup??ration de la r??servation");
		}
	}

	public List<Reservation> findAll() throws DaoException {
		ArrayList<Reservation>array = new	ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				int vehicule_id = rs.getInt("vehicle_id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String constructeur = rs.getString("constructeur");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				//new Reservation(id,client_id,vehicule_id,debut,fin),nom,prenom,constructeur;
				//array.add(new ReservationStruct(new Reservation(id,client_id,vehicule_id,debut,fin),nom,prenom,constructeur));
				array.add(new Reservation(id,client_id,vehicule_id,debut,fin));
			}


			//System.out.println("test " + array);
			return array;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public long count() throws DaoException {
		int reservationsCount = 1;
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(COUNT_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationsCount = rs.getInt(reservationsCount);
			}

			return reservationsCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long countByClientId(long clientId) throws DaoException {
		int reservationsCount = 1;
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(COUNT_RESERVATIONS_BY_CLIENT_QUERY);
			pstmt.setLong(1, clientId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationsCount = rs.getInt(reservationsCount);
			}

			return reservationsCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
//	public class ReservationStruct{
//		public Reservation reservation;
//		public String nom;
//		public String prenom;
//		public String constructeur;
//
//		public ReservationStruct(Reservation reservation, String nom, String prenom, String constructeur) {
//			this.reservation = reservation;
//			this.nom = nom;
//			this.prenom = prenom;
//			this.constructeur = constructeur;
//		}
//	}

}
