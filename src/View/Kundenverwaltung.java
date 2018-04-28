package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.Ausleihe;
import Model.Ausleihen;
import Model.Kunde;
import Model.Kunden;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class Kundenverwaltung extends JFrame {

	private Kunden kunden;
	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static KundeHinzufuegen khf;
	private static BuchAusleiheInformation bai;
	private static int kundennr = 1;
	private JTable table;
	
	public Kunden getKunden(){
		return kunden;
	}
	
	public int getKundenNr(){
		return kundennr;
	}
	
	public void incKundenNr(){
		kundennr = kundennr + 1;
	}
	
	public void showKundenTable(){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "Nr", "Vorname", "Nachname", "Geburtsdatum", "Addresse", "E-Mail"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		for(int i = 0; i < kunden.size(); i++){
			Object[] data2 = {kunden.get(i).getKundennr(), kunden.get(i).getVorname(), kunden.get(i).getName(), sdf.format(kunden.get(i).getGeburtsdatum()), 
					kunden.get(i).getPlz() + " " + kunden.get(i).getOrt() + " " + kunden.get(i).getStrasse() + " " + kunden.get(i).getHausnummer(),
					kunden.get(i).getEmail()};
			dtm.addRow(data2);
		}
	    
		// Anpassen der Breite der einzelnen Spalten
	    for (int column = 0; column < table.getColumnCount(); column++)
	    {
	        TableColumn tableColumn = table.getColumnModel().getColumn(column);
	        int preferredWidth = tableColumn.getMinWidth();
	        int maxWidth = tableColumn.getMaxWidth();

	        for (int row = 0; row < table.getRowCount(); row++)
	        {
	            TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
	            Component c = table.prepareRenderer(cellRenderer, row, column);
	            int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
	            preferredWidth = Math.max(preferredWidth, width);

	            //  We've exceeded the maximum width, no need to check other rows

	            if (preferredWidth >= maxWidth)
	            {
	                preferredWidth = maxWidth;
	                break;
	            }
	        }

	        tableColumn.setPreferredWidth( preferredWidth );
	    }
	}
	
	public void setVerwaltungen(Buecherverwaltung bvn, BuchAusleiheInformation bain){
		bv = bvn;
		bai = bain;
	}

	/**
	 * Create the frame.
	 */
	public Kundenverwaltung() {
		kv = this;
		
		Calendar geb = Calendar.getInstance();
		geb.set(Calendar.YEAR, 1992);
		geb.set(Calendar.MONTH, 11);
		geb.set(Calendar.DAY_OF_MONTH, 13);
		geb.set(Calendar.HOUR_OF_DAY, 0);
		geb.set(Calendar.MINUTE, 0);
		geb.set(Calendar.SECOND, 0);
		geb.set(Calendar.MILLISECOND, 0);
		
		kunden = new Kunden();
		kunden.add(new Kunde(kundennr, "Mustermann", "Max", geb.getTime(), "Musterstadt", 99543, "Musterstraﬂe", 12, "max.mustermann@web.de"));
		incKundenNr();
		
		geb = Calendar.getInstance();
		geb.set(Calendar.YEAR, 1996);
		geb.set(Calendar.MONTH, 1);
		geb.set(Calendar.DAY_OF_MONTH, 5);
		geb.set(Calendar.HOUR_OF_DAY, 0);
		geb.set(Calendar.MINUTE, 0);
		geb.set(Calendar.SECOND, 0);
		geb.set(Calendar.MILLISECOND, 0);
		
		kunden.add(new Kunde(kundennr, "Werkmann", "Daniel", geb.getTime(), "Ulm", 88600, "Maliweg", 18, "daniel.werkmann@e-mail.de"));
		incKundenNr();
		
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
				khf.leereTextFelder();
				khf.setVisible(true);
			}
		});
		btnNeuerKunde.setBounds(42, 371, 134, 23);
		contentPane.add(btnNeuerKunde);
		
		JButton btnKundeLoeschen = new JButton("Kunde l\u00F6schen");
		btnKundeLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					String str = table.getValueAt(row, 0).toString();
					int nr = Integer.parseInt(str);
					
					// Suche Ausleihe mit KundenNr
					for(int i = 0; i < bv.getAusleihen().size(); i++){
						if(nr == bv.getAusleihen().get(i).getKunde().getKundennr()){
							JOptionPane.showMessageDialog(null, "Dieser Kunde hat noch ausgeliehene B¸cher. Lˆschen nicht mˆglich", "Fehler", JOptionPane.OK_OPTION);
							return;
						}
					}
					
					((DefaultTableModel)table.getModel()).removeRow(row);
					
					// Suche Kunde mit KundenNr
					for(int i = 0; i < kunden.size(); i++){
						if(nr == kunden.get(i).getKundennr()){
							kunden.removeAtIndex(i);
							return;
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
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
		
		// Nur eine Zeile ausw‰hlbar
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewAusleihenAnzeigen = new JButton("Ausleihen anzeigen");
		btnNewAusleihenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ausleihen tmp = new Ausleihen();
				int row = table.getSelectedRow();
				if(row != -1){
					Ausleihe ausleihe = null;
					String nr = table.getValueAt(row, 0).toString();
					int nri = Integer.parseInt(nr);
					
					// Suche Ausleihen mit KundenNr
					for(int i = 0; i < bv.getAusleihen().size(); i++){
						if(nri == bv.getAusleihen().get(i).getKunde().getKundennr()){
							ausleihe = bv.getAusleihen().get(i);
							tmp.add(ausleihe);
						}
					}
					
					bai.showAusleihenTable(tmp);
					bai.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
				}
				
			}
		});
		btnNewAusleihenAnzeigen.setBounds(624, 371, 150, 23);
		contentPane.add(btnNewAusleihenAnzeigen);
	}
}
