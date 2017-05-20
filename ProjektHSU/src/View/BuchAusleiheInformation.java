package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.Ausleihe;
import Model.Ausleihen;
import Model.Buecher;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BuchAusleiheInformation extends JFrame {

	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static BuchAusleiheInformation bai;
	private JPanel contentPane;
	private JTable table;
	private JButton button;
	private JButton btnVerlängern;
	private JTextField textFieldTage;

	public void setVerwaltungen(Buecherverwaltung bvn, Kundenverwaltung kvn) {
		bv = bvn;
		kv = kvn;
	}
	
	
	public void showAusleihenTable(Ausleihen tmp){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "BuchNr", "Titel", "KundenNr", "Vorname", "Nachname", "Ausgeliehen", "Rueckgabe", "Verlängerungen"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		for(int i = 0; i < tmp.size(); i++){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Object[] data2 = {tmp.get(i).getBuch().getBuchnr(), tmp.get(i).getBuch().getTitel(), tmp.get(i).getKunde().getKundennr(), 
					tmp.get(i).getKunde().getVorname(), tmp.get(i).getKunde().getName(), 
					sdf.format(tmp.get(i).getAusgeliehen()), sdf.format(tmp.get(i).getRueckgabe()), tmp.get(i).getVerlaengerungen()};
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
	public BuchAusleiheInformation() {
		bai = this;
		setTitle("Informationen Buchleihgabe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		
		// Nur eine Zeile auswählbar
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		button = new JButton("Schlie\u00DFen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bai.setVisible(false);
			}
		});
		button.setBounds(373, 327, 132, 23);
		contentPane.add(button);
		
		btnVerlängern = new JButton("Verl\u00E4ngern ...");
		btnVerlängern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ausleihe ausleihe = null;
				int tage = -1;
				int index = -1;
				int row = table.getSelectedRow();
				if(row != -1){
					String nr = table.getValueAt(row, 0).toString();
					int nri = Integer.parseInt(nr);
					
					// Suche Ausleihe und Index mit BuchNr
					for(int i = 0; i < bv.getAusleihen().size(); i++){
						if(nri == bv.getAusleihen().get(i).getBuch().getBuchnr()){
							ausleihe = bv.getAusleihen().get(i);
							index = i;
							break;
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Treffen Sie zuerst eine Wahl", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
				
				// Falls bereits verlängert -> FEHLER
				if(ausleihe.getVerlaengerungen() == 1){
					JOptionPane.showMessageDialog(null, "Keine weiteren Verlängerungen möglich", "Fehler", JOptionPane.OK_OPTION);
					return;
					
				// Noch nicht verlängert
				}else{
					
					// Tage holen
					String str = textFieldTage.getText();
					
					// Textfeld leer -> FEHLER
					if(str.isEmpty()){
						JOptionPane.showMessageDialog(null, "Tage bei Verlängerung nicht angegeben", "Fehler", JOptionPane.OK_OPTION);
						return;
					// Textfeld gefüllt
					}else{
						
						// Versuch nach int zu casten. -> Falls nicht möglich -> FEHLER
						try{
							tage = Integer.parseInt(str);
						}catch(Exception arg0){
							JOptionPane.showMessageDialog(null, "Kein Zahl", "Fehler", JOptionPane.OK_OPTION);
							return;
						}
						
						// ALLES OK -> Addiere Tage auf Rueckgabedatum
						Calendar c = Calendar.getInstance();
						c.setTime(ausleihe.getRueckgabe()); 
						c.add(Calendar.DATE, tage);
						
						ausleihe.setRueckgabe(c.getTime());
						ausleihe.setVerlaengerungen(1);
						
						// Veränderte Ausleihe in Liste einfügen
						bv.getAusleihen().removeAtIndex(index);
						bv.getAusleihen().addAtIndex(ausleihe, index);
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						
						// Ändere Daten von Spalte 6 + 7
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						model.setValueAt(sdf.format(ausleihe.getRueckgabe()), row, 6);
						model.setValueAt("1", row, 7);
					}
				}
				
			}
		});
		btnVerlängern.setBounds(373, 293, 132, 23);
		contentPane.add(btnVerlängern);
		
		JLabel lblTage = new JLabel("Tage:");
		lblTage.setBounds(525, 297, 46, 14);
		contentPane.add(lblTage);
		
		textFieldTage = new JTextField();
		textFieldTage.setBounds(582, 294, 69, 20);
		contentPane.add(textFieldTage);
		textFieldTage.setColumns(10);
	}
}
