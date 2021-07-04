package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import baza.DAO;
import domen.Racunar;



public class JTableRacunar extends AbstractTableModel{

	ArrayList<Racunar> lista;
	
	
	public JTableRacunar(ArrayList<Racunar> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		Racunar pom = lista.get(r);
		switch (c) {
		case 0:
			return pom.getId_racunara();
		case 1:
			return pom.getVrsta();
		case 2:
			return pom.getCena();
		case 3:
			return pom.isNov();
		
		default:
			return "Greska!";
		}
	}

	@Override
	public String getColumnName(int c) {
		switch (c) {
		case 0:
		  return "ID racunara";
		case 1:
			return "Vrsta";
		case 2:
			return "Cena";
		case 3:
			return "Status";
		
		default:
			return "Greska!";
		}
	}
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:return false;
		case 1:return true;
		case 2:return true;
		case 3:return false;
		default:return false;
		}
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
		
		try{
			Racunar pom = lista.get(rowIndex);
		switch (columnIndex) {
		case 1: pom.setVrsta(aValue.toString()); break;
		case 2: pom.setCena(Integer.parseInt(aValue.toString())); break;
		case 3: pom.setNov(Boolean.parseBoolean(aValue.toString())); 
		
		}
		
		DAO dao = new DAO();
		dao.updateRacunar(pom);
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Greska "+e.getMessage());
		}
		
		
		fireTableRowsUpdated(rowIndex, columnIndex);
	}
	public ArrayList<Racunar> getLista() {
		return lista;
	}
	
	
}

