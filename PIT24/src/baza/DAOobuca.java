package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domen.Obuca;

public class DAOobuca {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/prodavnica_obuce", "root", "");
	}

	
	public ArrayList<Obuca> vratiObucuPSTM() throws ClassNotFoundException, SQLException {
		ArrayList<Obuca> lista = new ArrayList<Obuca>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `obuca`");
		
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id_obuca");
			int cena = resultSet.getInt("cena");
			int broj = resultSet.getInt("broj_komada");
			String vrsta = resultSet.getString("vrsta");
			
			boolean muske = resultSet.getBoolean("muska");

			Obuca a  = new Obuca (id,cena,broj, vrsta, muske);

			lista.add(a);
		    }
		
		close();
		return lista;
	}
	
	
	
	public int insertObucaLastInsertID(Obuca a) throws ClassNotFoundException, SQLException {
		

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO `obuca`( `cena`, `broj_komada`, `vrsta`, `muska`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setInt(1, a.getCena());
		preparedStatement.setInt(2, a.getBroj());
		preparedStatement.setString(3, a.getVrsta());
		preparedStatement.setBoolean(4, a.isMuska());
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		ResultSet keys = preparedStatement.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);
		close();
		return id;
	}
	
	public ArrayList<Obuca> vratiObucaSortiranePoCeniOpadajuce() throws ClassNotFoundException, SQLException {

		ArrayList<Obuca> lista = new ArrayList<Obuca>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `obuca` ORDER BY cena DESC");

		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_obuca");
			int cena = resultSet.getInt("cena");
			int broj = resultSet.getInt("broj_komada");
			String vrsta = resultSet.getString("vrsta");
			
			boolean muske = resultSet.getBoolean("muska");

			Obuca a  = new Obuca (id,cena,broj, vrsta, muske);

			lista.add(a);
		    }
		
		close();
		return lista;
	}
	public ArrayList<Obuca> vratiObucaSortiranePoCeniRasuce() throws ClassNotFoundException, SQLException {

		ArrayList<Obuca> lista = new ArrayList<Obuca>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `obuca` ORDER BY cena");

		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_obuca");
			int cena = resultSet.getInt("cena");
			int broj = resultSet.getInt("broj_komada");
			String vrsta = resultSet.getString("vrsta");
			
			boolean muske = resultSet.getBoolean("muska");

			Obuca a  = new Obuca (id,cena,broj, vrsta, muske);

			lista.add(a);
		    }
		
		close();
		return lista;
	}
	public ArrayList<Obuca> ispisMuska()throws ClassNotFoundException, SQLException {
		ArrayList<Obuca> lista = new ArrayList<Obuca>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `obuca` WHERE obuca.muska=1");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_obuca");
			int cena = resultSet.getInt("cena");
			int broj = resultSet.getInt("broj_komada");
			String vrsta = resultSet.getString("vrsta");
			
			boolean muske = resultSet.getBoolean("muska");

			Obuca a  = new Obuca (id,cena,broj, vrsta, muske);

			lista.add(a);
		      }
		close();
		return lista;
	}
	public ArrayList<Obuca> ispisZenska()throws ClassNotFoundException, SQLException {
		ArrayList<Obuca> lista = new ArrayList<Obuca>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `obuca` WHERE obuca.muska=0");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_obuca");
			int cena = resultSet.getInt("cena");
			int broj = resultSet.getInt("broj_komada");
			String vrsta = resultSet.getString("vrsta");
			
			boolean muske = resultSet.getBoolean("muska");

			Obuca a  = new Obuca (id,cena,broj, vrsta, muske);

			lista.add(a);
		}
		close();
		return lista;
	}
	
	public void deleteObucaById(int id) throws ClassNotFoundException, SQLException {
		
		connect();
		preparedStatement = connect.prepareStatement("DELETE FROM `obuca`  WHERE id_obuca = ?");

		preparedStatement.setInt(1, id);
		
		preparedStatement.execute();
		
		
		close();
	}
	public void insertObuca(Obuca a) throws ClassNotFoundException, SQLException {
	
		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO `obuca`( `cena`, `broj_komada`, `vrsta`, `muska`) VALUES (?,?,?,?)"); 
		
		preparedStatement.setInt(1, a.getCena());
		preparedStatement.setInt(2, a.getBroj());
		preparedStatement.setString(3, a.getVrsta());
		preparedStatement.setBoolean(4, a.isMuska());
		
		preparedStatement.execute();
		
		
		close();
	}
	
public void updateObuca(Obuca a) throws ClassNotFoundException, SQLException {
		
		connect();
		preparedStatement = connect.prepareStatement("UPDATE `obuca` SET `cena`=?,`broj_komada`=?,`vrsta`=?, `muska`=?  WHERE id_obuca = ?");

		preparedStatement.setInt(1, a.getCena());
		preparedStatement.setInt(2, a.getBroj());
		preparedStatement.setString(3, a.getVrsta());
		preparedStatement.setBoolean(4, a.isMuska());
		preparedStatement.setInt(5, a.getId_obuce());
		
		preparedStatement.execute();
		
		
		close();
	}
	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DAOobuca() {
		super();
		
	}


}


