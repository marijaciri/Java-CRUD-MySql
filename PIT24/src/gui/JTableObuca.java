package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import baza.DAOobuca;
import domen.Obuca;

public class JTableObuca extends AbstractTableModel{
ArrayList<Obuca> lista;
	
	
	public JTableObuca(ArrayList<Obuca> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		Obuca pom = lista.get(r);
		switch (c) {
		case 0:
			return pom.getId_obuce();
		case 1:
			return pom.getCena();
		case 2:
			return pom.getBroj();
		case 3:
			return pom.getVrsta();
		case 4:
			return pom.isMuska();
		
		default:
			return "Greska!";
		}
	}

	@Override
	public String getColumnName(int c) {
		switch (c) {
		case 0:
		  return "ID Obuce";
		case 1:
			return "Cena";
		case 2:
			return "Broj_komada";
		case 3:
			return "Vrsta";
		case 4:
			return "Muska";
		
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
		case 3:return true;
		case 4:return true;
		default:return false;
		}
	}
	@Override
public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
		
		try{
		Obuca pom = lista.get(rowIndex);
		switch (columnIndex) {
		case 1: pom.setCena(Integer.parseInt(aValue.toString())); break;
		case 2: pom.setBroj(Integer.parseInt(aValue.toString())); break;
		case 3: pom.setVrsta(aValue.toString()); break;
		case 4: pom.setMuska(Boolean.parseBoolean(aValue.toString()));
		}
		
		DAOobuca dao = new DAOobuca();
		dao.updateObuca(pom);
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Greska u Jtabeli setValueAt" +e.getMessage());
		}
		
		
		fireTableRowsUpdated(rowIndex, columnIndex);
	}
	public ArrayList<Obuca> getLista() {
		return lista;
	}
	
	
}
