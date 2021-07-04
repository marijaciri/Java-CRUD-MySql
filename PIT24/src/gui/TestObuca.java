package gui;

import java.sql.SQLException;

import baza.DAOobuca;
import domen.Obuca;

public class TestObuca {

	public static void main(String[] args) {
		DAOobuca dao = new DAOobuca();
		Obuca a = new Obuca(0, 4600,25,"Cizme", false);
		try {
			System.out.println(dao.insertObucaLastInsertID(a));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
