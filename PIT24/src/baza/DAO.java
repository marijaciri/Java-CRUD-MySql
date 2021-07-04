package baza;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import domen.Racunar;


public class DAO {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/racunari", "root", "");
	}

	
	public ArrayList<Racunar> vratiRacunarPSTM() throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar`");
		
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("status_nov");

			Racunar a  = new Racunar (id, vrsta,cena, nov);

			lista.add(a);
		}
		
		close();
		return lista;
	}
	public int insertRacunarLastInsertID(Racunar a) throws ClassNotFoundException, SQLException {
		

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO `racunar`( `vrsta`, `cena`, `status_nov`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, a.getVrsta());
		preparedStatement.setDouble(2, a.getCena());
		preparedStatement.setBoolean(3, a.isNov());
		
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		ResultSet keys = preparedStatement.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);
		close();
		return id;
	}
	public ArrayList<Racunar> vratiRacunarSortiranePoCeniOpadajuce() throws ClassNotFoundException, SQLException {

		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar` ORDER BY cena DESC");

		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("status_nov");

			Racunar a  = new Racunar (id, vrsta,cena, nov);

			lista.add(a);
		}
		
		close();
		return lista;
	}
	public ArrayList<Racunar> vratiRacunarSortiranePoCeniRasuce() throws ClassNotFoundException, SQLException {

		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar` ORDER BY cena");

		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("status_nov");

			Racunar a  = new Racunar (id, vrsta,cena, nov);

			lista.add(a);
		}
		
		close();
		return lista;
	}
	public ArrayList<Racunar> skupljiRacunari(double cena)throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar` WHERE cena > ?");
		preparedStatement.setDouble(1, cena);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena1 = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("status_nov");
			if(cena1 >= cena) {
			Racunar a  = new Racunar (id, vrsta,cena1, nov);
			lista.add(a);
		        }
		      }
		close();
		return lista;
	}
	public ArrayList<Racunar> skupljiRacunari2(double cena)throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena1 = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("status_nov");
			if(cena1 >= cena) {
			Racunar a  = new Racunar (id, vrsta,cena1, nov);
			lista.add(a);
		        }
		      }
		close();
		return lista;
	}
	public ArrayList<Racunar> prikazNov(boolean nov)throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena1 = resultSet.getDouble("cena");
			boolean nov1 = resultSet.getBoolean("status_nov");
			if(nov1 == nov) {
			Racunar a  = new Racunar (id, vrsta,cena1, nov1);
			lista.add(a);
		        }
		      }
		close();
		return lista;
	}
	public ArrayList<Racunar> prikazPolovan(boolean nov)throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunar`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_racunara");
			String vrsta = resultSet.getString("vrsta");
			double cena1 = resultSet.getDouble("cena");
			boolean nov1 = resultSet.getBoolean("status_nov");
			if(nov1 == nov) {
			Racunar a  = new Racunar (id, vrsta,cena1, nov1);
			lista.add(a);
		        }
		      }
		close();
		return lista;
	}
	public void deleteRacunarById(int id) throws ClassNotFoundException, SQLException {
		
		connect();
		preparedStatement = connect.prepareStatement("DELETE FROM `racunar`  WHERE id_racunara = ?");

		preparedStatement.setInt(1, id);
		
		preparedStatement.execute();
		
		
		close();
	}
	public void insertRacunar(Racunar a) throws ClassNotFoundException, SQLException {
	
		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO `racunar`( `vrsta`, `cena`, `status_nov`) VALUES (?,?,?)"); 
		
		preparedStatement.setString(1, a.getVrsta());
		preparedStatement.setDouble(2, a.getCena());
		preparedStatement.setBoolean(3, a.isNov());
		
		preparedStatement.execute();
		
		
		close();
	}
	
public void updateRacunar(Racunar a) throws ClassNotFoundException, SQLException {
		
		connect();
		preparedStatement = connect.prepareStatement("UPDATE `racunar` SET `vrsta`=?,`cena`=?,`status_nov`=?  WHERE id_racunara = ?");

		preparedStatement.setString(1, a.getVrsta());
		preparedStatement.setDouble(2, a.getCena());
		preparedStatement.setBoolean(3, a.isNov());
		preparedStatement.setInt(4, a.getId_racunara());
		
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


	public DAO() {
		super();
		
	}


}
