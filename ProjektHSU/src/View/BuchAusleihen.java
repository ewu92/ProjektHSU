package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.Ausleihe;
import Model.Buch;
import Model.Kunde;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BuchAusleihen extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static BuchAusleihen ba;
	private JTable table;
	private JTextField textFieldBuch;
	private JLabel lblFolgendesBuchWird;
	private JLabel lblDauerDerAusleihe;
	private JTextField textFieldTage;
	private JButton buttonOk;
	private JButton buttonAbbrechen;
	private Buch auszuleihen;
	
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
		for(int i = 0; i < kv.getKunden().size(); i++){
			Object[] data2 = {kv.getKunden().get(i).getKundennr(), kv.getKunden().get(i).getVorname(), kv.getKunden().get(i).getName(), kv.getKunden().get(i).getGeburtsdatum().toString(), 
					kv.getKunden().get(i).getPlz() + " " + kv.getKunden().get(i).getOrt() + " " + kv.getKunden().get(i).getStrasse() + " " + kv.getKunden().get(i).getHausnummer(),
					kv.getKunden().get(i).getEmail()};
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

	
	public void setVerwaltungen(Buecherverwaltung bvn, Kundenverwaltung kvn){
		bv = bvn;
		kv = kvn;
	}
	
	public void setBuchAuszuleihen(Buch buch){
		this.textFieldBuch.setText(buch.getBuchnr() + " : " + buch.getTitel());
		auszuleihen = buch;
		this.showKundenTable();
	}

	/**
	 * Create the frame.
	 */
	public BuchAusleihen() {
		ba = this;	
		setTitle("Buch ausleihen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 864, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		
		// Nur eine Zeile auswählbar
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		textFieldBuch = new JTextField();
		textFieldBuch.setEditable(false);
		textFieldBuch.setBounds(234, 25, 342, 20);
		contentPane.add(textFieldBuch);
		textFieldBuch.setColumns(10);
		
		lblFolgendesBuchWird = new JLabel("Folgendes Buch wird ausgeliehen:");
		lblFolgendesBuchWird.setBounds(10, 28, 214, 14);
		contentPane.add(lblFolgendesBuchWird);
		
		lblDauerDerAusleihe = new JLabel("Dauer der Ausleihe (in Tagen):");
		lblDauerDerAusleihe.setBounds(20, 444, 193, 14);
		contentPane.add(lblDauerDerAusleihe);
		
		textFieldTage = new JTextField();
		textFieldTage.setBounds(208, 441, 193, 20);
		contentPane.add(textFieldTage);
		textFieldTage.setColumns(10);
		
		buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kunde kunde = null;
				int row = table.getSelectedRow();
				if(row != -1){
					String str = table.getValueAt(row, 0).toString();
					int nr = Integer.parseInt(str);
					
					// Textfeld auslesen
					String tagestr = textFieldTage.getText();
					int tage = -1;
					if(tagestr.isEmpty()){
						JOptionPane.showMessageDialog(null, "Bitte Dauer der Ausleihe angeben", "Fehler", JOptionPane.OK_OPTION);
						return;
					}
					
					try{
						tage = Integer.parseInt(tagestr);
					}catch(Exception arg0){
						JOptionPane.showMessageDialog(null, "Kein Zahl", "Fehler", JOptionPane.OK_OPTION);
						return;
					}
					
					// Suche Kunde mit KundenNr
					for(int i = 0; i < kv.getKunden().size(); i++){
						if(nr == kv.getKunden().get(i).getKundennr()){
							kunde = kv.getKunden().get(i);
							break;
						}
					}
					
					// Ändere Status des Buches
					// Zunächst Suche des aktuellen Indexes
					int index = -1;
					for(int i = 0; i < bv.getBuecher().size(); i++){
						if(auszuleihen.getBuchnr() == bv.getBuecher().get(i).getBuchnr()){
							index = i;
							break;
						}
					}
					
					// Aktuelles Datum
					Calendar now = Calendar.getInstance();
					Calendar cal = Calendar.getInstance();
			        cal.add(Calendar.DATE, tage);
					
			        // Ausleihe erzeugen
					Ausleihe ausleihe = new Ausleihe(auszuleihen, kunde, now.getTime(), cal.getTime(), 0);
					
					// In Liste aufnehmen
					bv.getAusleihen().add(ausleihe);
					
					// Löschen des Buches
					bv.getBuecher().removeAtIndex(index);
					
					// Status ändern
					auszuleihen.setAusleihstatus(true);
					
					// Und wieder einfügen
					bv.getBuecher().addAtIndex(auszuleihen, index);
					
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
				}
				
				ba.setVisible(false);
				bv.showBuecherTable(bv.getBuecher());
			}
		});
		buttonOk.setBounds(295, 527, 89, 23);
		contentPane.add(buttonOk);
		
		buttonAbbrechen = new JButton("Abbrechen");
		buttonAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ba.setVisible(false);
			}
		});
		buttonAbbrechen.setBounds(470, 527, 106, 23);
		contentPane.add(buttonAbbrechen);
	}
}
