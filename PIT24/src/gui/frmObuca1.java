package gui;
import domen.Obuca;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baza.DAOobuca;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

public class frmObuca1 extends JFrame {
	private JPanel contentPane;
	private JTextField txtBroj;
	private ButtonGroup grupa;
	private ArrayList<Obuca> lista;
	private JTextField txtCena;
	private JTable tableUnos;
	private DAOobuca dao;
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	public void run() {
	try {
	frmObuca1 frame = new frmObuca1();
	frame.setVisible(true);
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	});
	}

	 /**
	* Create the frame.
	*/
	public frmObuca1() {
	 	setForeground(Color.ORANGE);
		dao = new DAOobuca();
		setBackground(UIManager.getColor("info"));
		setTitle("OBUCA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.BELOW_BOTTOM, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "UNOS PODATAKA", TitledBorder.LEADING, TitledBorder.BOTTOM, null, SystemColor.menu));
		panel.setBounds(10, 11, 329, 376);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox cmbVrsta = new JComboBox();
		cmbVrsta.setModel(new DefaultComboBoxModel(new String[] {"Cipele", "Patike", "Sandale", "Cizme"}));
		cmbVrsta.setBounds(127, 25, 124, 22);
		panel.add(cmbVrsta);
		JLabel lblNewLabel = new JLabel("VRSTA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(33, 29, 73, 14);
		panel.add(lblNewLabel);
		
		txtBroj = new JTextField();
		txtBroj.setColumns(10);
		txtBroj.setBounds(127, 99, 124, 20);
		panel.add(txtBroj);
		JLabel lblNewLabel_1 = new JLabel("CENA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 71, 97, 14);
		panel.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("BROJ KOMADA");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 102, 97, 14);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "IZBOR OBUCE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(124, 140, 195, 76);
		panel.add(panel_1);
		panel_1.setLayout(null);
		JRadioButton rdbtnMuske = new JRadioButton("MUSKE");
		rdbtnMuske.setBounds(6, 17, 109, 23);
		panel_1.add(rdbtnMuske);
		JRadioButton rdbtnZenske = new JRadioButton("ZENSKE");
		rdbtnZenske.setBounds(6, 46, 109, 23);
		panel_1.add(rdbtnZenske);
		grupa = new ButtonGroup();
		grupa.add(rdbtnZenske);
		grupa.add(rdbtnMuske);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(349, 60, 400, 323);
		contentPane.add(scrollPane);
		
		tableUnos = new JTable();
		tableUnos.setBackground(new Color(173, 216, 230));
		tableUnos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID_obuce", "Cena", "Broj komada", "Vrsta obuce", "Muska"
			}
		));
		scrollPane.setViewportView(tableUnos);

		JButton btnDodaj = new JButton("DODAJ");
		btnDodaj.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
		     try {
		       Obuca o = new Obuca(
				0,
				Integer.parseInt(txtCena.getText().trim()),
				Integer.parseInt(txtBroj.getText().trim()),
				cmbVrsta.getSelectedItem().toString(),
				rdbtnMuske.isSelected()
				);
	
		      int a = dao.insertObucaLastInsertID(o);
		      o.setId_obuce(a);
		      
		       ((JTableObuca)tableUnos.getModel()).getLista().add(o);
		       updateJTableObuca(((JTableObuca)tableUnos.getModel()).getLista());
		       JOptionPane.showMessageDialog(contentPane, "USPESNO STE UNELI PODATAK!" );
		       txtCena.grabFocus();
		       ocisti_kontrole();
				
		       } catch (ClassNotFoundException e1) {
			   JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());		
		      } catch (SQLException e1) {
			  JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());	
		  }	
		}
	});
		btnDodaj.setBounds(127, 227, 89, 23);
		panel.add(btnDodaj);
		txtCena = new JTextField();
		txtCena.setColumns(10);
		txtCena.setBounds(127, 68, 124, 20);
		panel.add(txtCena);
		JButton btnIspisMuske = new JButton("ISPIS MUSKE OBUCE");
		btnIspisMuske.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJTableObuca(dao.vratiObucuPSTM());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Obuca> o = ((JTableObuca)tableUnos.getModel()).getLista();
				ArrayList<Obuca> a = new ArrayList<Obuca>();
				for(Obuca o1 : o) {
					if(o1.isMuska())
						a.add(o1);
				}
				updateJTableObuca(a);
			}
		});
		btnIspisMuske.setBounds(127, 273, 166, 23);
		panel.add(btnIspisMuske);
		
		JButton btnZenske = new JButton("ISPIS ZENSKE OBUCE");
		btnZenske.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJTableObuca(dao.vratiObucuPSTM());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Obuca> o = ((JTableObuca)tableUnos.getModel()).getLista();
				ArrayList<Obuca> a = new ArrayList<Obuca>();
				for(Obuca o1 : o) {
					if(!(o1.isMuska()))
						a.add(o1);
				}
				updateJTableObuca(a);
			}
		});
		btnZenske.setBounds(127, 303, 166, 23);
		panel.add(btnZenske);
		
		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableObuca model = (JTableObuca)tableUnos.getModel();
				int selectedID= tableUnos.getSelectedRow();
				if(selectedID >= 0)  {
				int id = model.getLista().get(selectedID).getId_obuce();
				
		         try {
					dao.deleteObucaById(id);;
		         } catch (ClassNotFoundException | SQLException e1){
		 			JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
		 		}
	      		model.getLista().remove(selectedID);
	      		updateJTableObuca(model.getLista());
				}
				else{
					JOptionPane.showMessageDialog(getContentPane(), "Morate selektovati obucu za brisanje");
				}
			}
		});
		btnObrisi.setBounds(230, 227, 89, 23);
		panel.add(btnObrisi);
		
		JButton btnOsvezi = new JButton("OSVEZI PODATKE");
		btnOsvezi.setBounds(555, 26, 166, 23);
		contentPane.add(btnOsvezi);
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJTableObuca(dao.vratiObucuPSTM());
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
				}
			}
		});
		
		try {
			updateJTableObuca(dao.vratiObucuPSTM());
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e1.getMessage());
		      }
		}
	private void ocisti_kontrole() {
		txtBroj.setText("");
		txtCena.setText("");
		grupa.clearSelection();
		}
	private void updateJTableObuca(ArrayList<Obuca> l) {
		try {
			JTableObuca model = new JTableObuca(l);
		     tableUnos.setModel(model);
			
		} catch (HeadlessException e) {
	JOptionPane.showMessageDialog(getContentPane(), "Doslo je do greske!!!"+e.getMessage());

		      }
		
	        }
		}