package View;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.Ausleihen;
import Model.Buch;
import Model.Buecher;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Buecherverwaltung extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static BuchHinzufuegen bhf;
	private static BuchAusleihen ba;
	private static int buchnr = 1;
	private JTable table;
	private Buecher buecher;
	private Ausleihen ausleihen;
	private JTextField textFieldTitel;
	private JTextField textFieldAutor;
	private JTextField textFieldISBN;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kv = new Kundenverwaltung();
					bv = new Buecherverwaltung();
					kv.setVisible(false);
					bv.setVisible(true);
					kv.setBuecherVerwaltung(bv);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Buecher getBuecher(){
		return buecher;
	}
	
	public Ausleihen getAusleihen(){
		return ausleihen;
	}
	
	public int getBuchNr(){
		return buchnr;
	}
	
	public void incBuchNr(){
		buchnr = buchnr + 1;
	}
	
	public void showBuecherTable(Buecher tmp){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "Nr", "Titel", "Autor", "ISBN", "Erscheinungsjahr", "Verlag", "Ausgeliehen"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		for(int i = 0; i < tmp.size(); i++){
			String str = null;
			if(tmp.get(i).getAusleihstatus()){str = "Ja";}else{str = "Nein";};
			Object[] data2 = {tmp.get(i).getBuchnr(), tmp.get(i).getTitel(), tmp.get(i).getAutor(), tmp.get(i).getIsbn(), 
					tmp.get(i).getErscheinungsjahr(), tmp.get(i).getVerlag(), str};
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

	/**
	 * Create the frame.
	 */
	public Buecherverwaltung() {
		
		ausleihen = new Ausleihen();
		
		//***************************** ZWEI TESTBÜCHER ********************************//
		buecher = new Buecher();
		Buch buch = new Buch(buchnr, "Programmieren für Anfänger", "Jörg Schuster", "345211231-0", 1996, "Springer");
		incBuchNr();
		buecher.add(buch);
		
		buch = new Buch(buchnr, "Java Programmierung", "Michael Schuhmacher", "54736-112", 1994, "Springer");
		incBuchNr();
		buecher.add(buch);
		
		
		//***************************** INITIALISIERUNG WEITERER GUIS ********************************//
		bhf = new BuchHinzufuegen();
		bhf.setVisible(false);
		bhf.setBuecherverwaltung(this);
		
		ba = new BuchAusleihen();
		ba.setVisible(false);
		ba.setVerwaltungen(this, kv);
		
		setTitle("B\u00FCcherverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//***************************** KUNDENVERWALTUNG BUTTON ********************************//
		JButton btnKundenverwaltung = new JButton("Kundenverwaltung");
		btnKundenverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bv.setVisible(false);
				kv.setVisible(true);
				kv.showKundenTable();
			}
		});
		btnKundenverwaltung.setBounds(624, 527, 150, 23);
		contentPane.add(btnKundenverwaltung);
		
		
		//***************************** WEITERE GUI ELEMENTE ********************************//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		
		// Nur eine Zeile auswählbar
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		
		//***************************** NEUES BUCH BUTTON ********************************//
		JButton btnNeuesBuch = new JButton("Neues Buch");
		btnNeuesBuch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bhf.setVisible(true);
			}
		});
		btnNeuesBuch.setBounds(39, 355, 134, 23);
		contentPane.add(btnNeuesBuch);
		
		
		//***************************** LÖSCHEN BUTTON ********************************//
		JButton btnBuchLoeschen = new JButton("Buch l\u00F6schen");
		btnBuchLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					String str = table.getValueAt(row, 0).toString();
					int nr = Integer.parseInt(str);
					((DefaultTableModel)table.getModel()).removeRow(row);
					
					// Suche Buch mit BuchNr
					for(int i = 0; i < buecher.size(); i++){
						if(nr == buecher.get(i).getBuchnr()){
							buecher.removeAtIndex(i);
							return;
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
				}
			}
		});
		btnBuchLoeschen.setBounds(38, 389, 135, 23);
		contentPane.add(btnBuchLoeschen);
		
		JButton btnAusleiheanzeigen = new JButton("Ausleihe anzeigen");
		btnAusleiheanzeigen.setBounds(624, 355, 150, 23);
		contentPane.add(btnAusleiheanzeigen);
		
		
		//***************************** AUSLEIHEN BUTTON ******************************//
		JButton btnAusleihen = new JButton("Ausleihen");
		btnAusleihen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					Buch buch = null;
					String nr = table.getValueAt(row, 0).toString();
					int nri = Integer.parseInt(nr);
					
					// Suche Buch mit BuchNr
					for(int i = 0; i < buecher.size(); i++){
						if(nri == buecher.get(i).getBuchnr()){
							buch = buecher.get(i);
						}
					}
					
					ba.setBuchAuszuleihen(buch);
					ba.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
				}
			}
		});
		btnAusleihen.setBounds(39, 423, 134, 23);
		contentPane.add(btnAusleihen);
		
		
		//***************************** SUCHE BUTTON ********************************//
		JButton btnSuche = new JButton("Suche ...");
		btnSuche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buecher sBuecher = new Buecher();
				String titel = null;
				String autor = null;
				String isbn = null;
				
				titel = textFieldTitel.getText();
				autor = textFieldAutor.getText();
				isbn = textFieldISBN.getText();
				
				// Alle Bücher mit richtigem Titel hinzufügen
				// -> Falls Textfeld leer -> Werden alle Bücher hinzugefügt
				for(int i = 0; i < buecher.size(); i++){
					if(buecher.get(i).getTitel().contains(titel)){
						sBuecher.add(buecher.get(i));
					}
				}
				
				// Alle Bücher mit falschen Autoren wegfiltern
				if(!autor.isEmpty()){
					for(int i = 0; i < sBuecher.size(); i++){
						if(!(sBuecher.get(i).getAutor().contains(autor))){
							sBuecher.removeAtIndex(i);
						}
					}
				}
				
				// Alle Bücher mit falschen ISBN wegfiltern
				if(!isbn.isEmpty()){
					for(int i = 0; i < sBuecher.size(); i++){
						if(!(sBuecher.get(i).getIsbn().contains(isbn))){
							sBuecher.removeAtIndex(i);
						}
					}
				}
				
				if(titel.isEmpty() && autor.isEmpty() && isbn.isEmpty()){
					JOptionPane.showMessageDialog(null, "Fuck you!!", "Fuck you!!", JOptionPane.OK_OPTION);
				}
				
				// Tabelle aktualisieren
				showBuecherTable(sBuecher);
			}
		});
		btnSuche.setBounds(227, 355, 134, 23);
		contentPane.add(btnSuche);
		
		
		//***************************** Weitere Elemente ********************************//
		textFieldTitel = new JTextField();
		textFieldTitel.setBounds(283, 390, 174, 20);
		contentPane.add(textFieldTitel);
		textFieldTitel.setColumns(10);
		
		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setBounds(227, 393, 46, 14);
		contentPane.add(lblTitel);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(227, 427, 46, 14);
		contentPane.add(lblAutor);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(283, 424, 174, 20);
		contentPane.add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(227, 463, 46, 14);
		contentPane.add(lblIsbn);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(283, 460, 174, 20);
		contentPane.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		
		//***************************** ZÜRÜCKSETZEN BUTTON ********************************//
		JButton btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");
		btnZuruecksetzen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBuecherTable(buecher);
			}
		});
		btnZuruecksetzen.setBounds(227, 501, 134, 23);
		contentPane.add(btnZuruecksetzen);
		
		
		// Tabelle laden
		this.showBuecherTable(buecher);
	}
}
