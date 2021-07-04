package gui;

import domen.Racunar;
import baza.DAO;
import gui.JTableRacunar;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class frmRacunar extends JFrame {

	private JPanel contentPane;
	private JTextField textCena;
	private ButtonGroup grupa;
	private JTable tableUnos;
	private DAO dao;
	private JTextField txtUnsesiteCenu;
	private JList listPrikaz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRacunar frame = new frmRacunar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public frmRacunar() {
		
		dao = new DAO();
		setTitle("RACUNARI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(10, 0, 303, 449);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Racunari", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VRSTA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(35, 21, 46, 21);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(94, 21, 89, 28);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Desktop", "Laptop"}));
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("CENA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(24, 66, 57, 17);
		panel.add(lblNewLabel_1);
		
		textCena = new JTextField();
		textCena.setBounds(94, 66, 86, 20);
		panel.add(textCena);
		textCena.setColumns(10);
		
		JRadioButton rdbtnNovi = new JRadioButton("Novi");
		rdbtnNovi.setBounds(6, 93, 67, 23);
		panel.add(rdbtnNovi);
		
		JRadioButton rdbtnPolovni = new JRadioButton("Polovni");
		rdbtnPolovni.setBounds(6, 116, 67, 23);
		panel.add(rdbtnPolovni);
		grupa = new ButtonGroup();
		grupa.add(rdbtnPolovni);
		grupa.add(rdbtnNovi);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 11, 429, 325);
		contentPane.add(scrollPane);
		
		tableUnos = new JTable();
		tableUnos.setBackground(new Color(135, 206, 250));
		tableUnos.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID racunara", "Vrsta", "Cena", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableUnos);
		JButton btnUnos = new JButton("UNOS");
		btnUnos.setBounds(94, 104, 89, 23);
		btnUnos.addActionListener(new  ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Racunar r = new Racunar (
							0,
							comboBox.getSelectedItem().toString(),
							Double.parseDouble(textCena.getText().trim()),
							rdbtnNovi.isSelected()
							);
					int a= dao.insertRacunarLastInsertID(r);
					r.setId_racunara(a);
					((JTableRacunar)tableUnos.getModel()).getLista().add(r);
					updateJTableRacunar(((JTableRacunar)tableUnos.getModel()).getLista());
					
					JOptionPane.showMessageDialog(contentPane, "USPESNO STE UNELI PODATAK!");
					ocisti_kontrole();
						
					}  catch (ClassNotFoundException | SQLException e1){
						JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
					}
				}
			});
		panel.add(btnUnos);
		
		JButton btnIspis = new JButton("OSVEZI \r\nPODATKE");
		btnIspis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJTableRacunar(dao.vratiRacunarPSTM());
				} catch (ClassNotFoundException | SQLException e1){
			JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
				}
			}
			
		});
			
		btnIspis.setHorizontalAlignment(SwingConstants.LEFT);
		btnIspis.setBounds(6, 410, 117, 28);
		panel.add(btnIspis);
		
		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTableRacunar model = (JTableRacunar)tableUnos.getModel();
				int selectedID= tableUnos.getSelectedRow();
				
				if(selectedID != -1)  {
				int id = model.getLista().get(selectedID).getId_racunara();
				
		         try {
					dao.deleteRacunarById(id);;
		         } catch (ClassNotFoundException | SQLException e1){
		 			JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
		 		}
	      		model.getLista().remove(selectedID);
	      		updateJTableRacunar(model.getLista());
				}
				else{
					JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati racunar za brisanje");
				}
			
			}
		});
		btnObrisi.setHorizontalAlignment(SwingConstants.LEFT);
		btnObrisi.setBounds(6, 378, 89, 23);
		panel.add(btnObrisi);
		
		JComboBox comboSortiranje = new JComboBox();
		comboSortiranje.setModel(new DefaultComboBoxModel(new String[] {"Opadajuce", "Rastuce"}));
		comboSortiranje.setBounds(166, 175, 96, 22);
		panel.add(comboSortiranje);
		
		JButton btnSortiranje = new JButton("Prikazi sortirano");
		btnSortiranje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sort = comboSortiranje.getSelectedItem().toString();
				if(sort.equals("Opadajuce")) {
				ArrayList<Racunar> a = ((JTableRacunar)tableUnos.getModel()).getLista();
				for(int i =0; i<a.size()-1; i++) {
					for(int j=i+1; j<a.size(); j++) {
						if(a.get(i).getCena()> a.get(j).getCena()) {
							Racunar pom = a.get(i);
							a.set(i, a.get(j));
							a.set(j, pom);
						}
					}
				}
				updateJTableRacunar(a);
				}
				
				else {
					ArrayList<Racunar> a = ((JTableRacunar)tableUnos.getModel()).getLista();
					for(int i =0; i<a.size()-1; i++) {
						for(int j=i+1; j<a.size(); j++) {
							if(a.get(i).getCena()< a.get(j).getCena()) {
								Racunar pom = a.get(i);
								a.set(i, a.get(j));
								a.set(j, pom);
							}
						}
					}
					updateJTableRacunar(a);
					
				}
				// sortiranje iz baze
				/*if(sort.equals("Opadajuce")) {
					try {
						updateJTableRacunar(dao.vratiRacunarSortiranePoCeniOpadajuce());
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
					}
				}
				else 
					 {
						try {
							updateJTableRacunar(dao.vratiRacunarSortiranePoCeniRasuce());
						} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
						}
					}*/
					
						
			}
		});
		btnSortiranje.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSortiranje.setBounds(6, 175, 146, 23);
		panel.add(btnSortiranje);
		
		txtUnsesiteCenu = new JTextField();
		txtUnsesiteCenu.setBounds(166, 210, 96, 20);
		panel.add(txtUnsesiteCenu);
		txtUnsesiteCenu.setColumns(10);
		
		JButton btnSkuplje = new JButton("Prikazi skuplje od");
		btnSkuplje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String unos = txtUnsesiteCenu.getText().trim();
				ArrayList<Racunar> a = ((JTableRacunar)tableUnos.getModel()).getLista();
				ArrayList<Racunar> b = new  ArrayList<Racunar>();
				int br =0;
				if(unos.isEmpty()) {
					JOptionPane.showMessageDialog(getContentPane(), "Morate uneti cenu za prikaz skupljih uredjaja.");	
				}
				else {
				for(int i =0; i<a.size(); i++) {
					if (a.get(i).getCena()>= Double.parseDouble(txtUnsesiteCenu.getText().trim())) {
						b.add(a.get(i));
						br++;
					}
					
				}
				if(br>0) {
				updateJTableRacunar(b);
				}
				else {
					JOptionPane.showMessageDialog(getContentPane(), "Ne postoji  skuplji uredjaji.");	
				}
				txtUnsesiteCenu.setText("");
				}
				/*  prikaz iz baze
				 double cena = Double.parseDouble(textField.getText().trim());
				 try {
					updateJTableRacunar(dao.skupljiRacunari(cena));
					textField.setText("");
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
				}*/
			}
		});
		btnSkuplje.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSkuplje.setBounds(6, 209, 146, 23);
		panel.add(btnSkuplje);
		
	    listPrikaz = new JList();
		listPrikaz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPrikaz.setModel(new AbstractListModel() {
			String[] values = new String[] {"Nove uredjaje", "Polovne uredjaje"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listPrikaz.setBounds(119, 292, 96, 50);
		panel.add(listPrikaz);
		
		JButton btnPrikaz = new JButton("Prikazi");
		btnPrikaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listPrikaz.clearSelection();
				int a = listPrikaz.getSelectionModel().getAnchorSelectionIndex();
				try {
					updateJTableRacunar(dao.vratiRacunarPSTM());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Racunar> z = ((JTableRacunar)tableUnos.getModel()).getLista();
				ArrayList<Racunar> b = new  ArrayList<Racunar>();
				    if(a == -1) {
				     JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati izbor za prikaz.");
				    
					   }
				      else {
					for(int i =0; i<z.size(); i++) {
						if (z.get(i).isNov() && a == 0 ){
							b.add(z.get(i));
							
						         }
						
						if(!(z.get(i).isNov()) && a == 1){
							b.add(z.get(i));
							
					           }
						
					}
					 updateJTableRacunar(b);
					 
					 
					}	
				    
					/*
					 prikaz iz baze
				 if( a == 0) {
					 *try {
						updateJTableRacunar(dao.prikazNov(true));
				        } catch (ClassNotFoundException | SQLException e1) {
		           JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());	
					     }
				    }
				 
				if(a == 1) {
					try {
						updateJTableRacunar(dao.prikazNov(false));
					  } catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());	
					       }
				        }
				else
				    {
				JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati izbor za prikaz.");
				  }*/
				}
			
		});
		btnPrikaz.setBounds(6, 285, 89, 57);
		panel.add(btnPrikaz);
		try {
			updateJTableRacunar(dao.vratiRacunarPSTM());
		} catch (ClassNotFoundException | SQLException e1){
			JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
		}
		
	}
	private void ocisti_kontrole() {
		textCena.setText("");
		txtUnsesiteCenu.setText("");
		grupa.clearSelection();
		listPrikaz.clearSelection();
	}
	private void updateJTableRacunar(ArrayList<Racunar> l) {
		try {
			JTableRacunar model = new JTableRacunar(l);
		     tableUnos.setModel(model);
			
		} catch (HeadlessException e) {
	JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e.getMessage());

		}
		
		
	}
}
