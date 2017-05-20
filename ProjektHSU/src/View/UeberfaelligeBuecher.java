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

import Model.Ausleihen;
import Model.Buecher;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class UeberfaelligeBuecher extends JFrame {

	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static UeberfaelligeBuecher ub;
	private JPanel contentPane;
	private JTable table;

	public void setVerwaltungen(Buecherverwaltung bvn, Kundenverwaltung kvn) {
		bv = bvn;
		kv = kvn;
	}
	
	public void showUeberfaelligeBuecherTable(Ausleihen tmp){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "BuchNr", "Titel", "KundenNr", "Vorname", "Nachname", "Ausgeliehen", "Rueckgabe"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		
		Date now = new Date();
		
		for(int i = 0; i < tmp.size(); i++){
			
			if(now.after(tmp.get(i).getRueckgabe())){
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				Object[] data2 = {tmp.get(i).getBuch().getBuchnr(), tmp.get(i).getBuch().getTitel(), tmp.get(i).getKunde().getKundennr(), 
						tmp.get(i).getKunde().getVorname(), tmp.get(i).getKunde().getName(), 
						sdf.format(tmp.get(i).getAusgeliehen()), sdf.format(tmp.get(i).getRueckgabe())};
				dtm.addRow(data2);
			}
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
	public UeberfaelligeBuecher() {
		setTitle("\u00DCberf\u00E4llige B\u00FCcher");
		ub = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		
		// Nur eine Zeile auswählbar
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnSchlieen = new JButton("Schlie\u00DFen");
		btnSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ub.setVisible(false);
			}
		});
		btnSchlieen.setBounds(377, 327, 132, 23);
		contentPane.add(btnSchlieen);
	}
}
