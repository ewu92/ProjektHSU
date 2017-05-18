package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.Kunde;
import Model.Kunden;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class Kundenverwaltung extends JFrame {

	private Kunden kunden;
	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static KundeHinzufuegen khf;
	private JTable table;
	
	public Kunden getKunden(){
		return kunden;
	}
	
	public void showKundenTable(){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "Vorname", "Nachname", "Geburtsdatum", "Addresse", "E-Mail"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		for(int i = 0; i < kunden.size(); i++){
			Object[] data2 = {kunden.get(i).getVorname(), kunden.get(i).getName(), kunden.get(i).getGeburtsdatum().toString(), 
					kunden.get(i).getPlz() + " " + kunden.get(i).getOrt() + " " + kunden.get(i).getStrasse() + " " + kunden.get(i).getHausnummer(),
					kunden.get(i).getEmail()};
			dtm.addRow(data2);
		}
		TableColumn column = table.getColumnModel().getColumn(2);
	    column.setPreferredWidth(10); 
	    column = table.getColumnModel().getColumn(3);
	    column.setPreferredWidth(120);
	}
	
	public void setBuecherVerwaltung(Buecherverwaltung bvn){
		bv = bvn;
	}

	/**
	 * Create the frame.
	 */
	public Kundenverwaltung() {
		kv = this;
		kunden = new Kunden();
		kunden.add(new Kunde("Mustermann", "Max", "13-12-1992", "Musterstadt", 99543, "Musterstraße", 12, "max.mustermann@web.de"));
		khf = new KundeHinzufuegen();
		khf.setVisible(false);
		khf.setKundenverwaltung(this);
		
		setTitle("Kundenverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuecherverwaltung = new JButton("B\u00FCcherverwaltung");
		btnBuecherverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kv.setVisible(false);
				bv.setVisible(true);
			}
		});
		btnBuecherverwaltung.setBounds(624, 527, 150, 23);
		contentPane.add(btnBuecherverwaltung);
		
		JButton btnNeuerKunde = new JButton("Neuer Kunde");
		btnNeuerKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				khf.setVisible(true);
			}
		});
		btnNeuerKunde.setBounds(42, 371, 134, 23);
		contentPane.add(btnNeuerKunde);
		
		JButton btnKundeLoeschen = new JButton("Kunde l\u00F6schen");
		btnKundeLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				((DefaultTableModel)table.getModel()).removeRow(row);
				kunden.removeAtIndex(row);
			}
		});
		btnKundeLoeschen.setBounds(41, 405, 135, 23);
		contentPane.add(btnKundeLoeschen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 339);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
	}
}
