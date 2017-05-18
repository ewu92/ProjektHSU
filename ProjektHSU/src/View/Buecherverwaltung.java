package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.Buch;
import Model.Buecher;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class Buecherverwaltung extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private JTable table;
	private Buecher buecher;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bv = new Buecherverwaltung();
					kv = new Kundenverwaltung();
					kv.setVisible(false);
					bv.setVisible(true);
					kv.setBuecherVerwaltung(bv);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showBuecherTable(){
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		//set model into the table object
		table.setModel(dtm);
		
		Object[] o = new Object[] { "Titel", "Autor", "ISBN", "Erscheinungsjahr", "Verlag"};
		dtm.setColumnIdentifiers(o);

		// add row dynamically into the table      
		for(int i = 0; i < dtm.getRowCount(); i++){
			dtm.removeRow(i);
		}
		for(int i = 0; i < buecher.size(); i++){
			Object[] data2 = {buecher.get(i).getTitel(), buecher.get(i).getAutor(), buecher.get(i).getIsbn(), 
					buecher.get(i).getErscheinungsjahr(), buecher.get(i).getVerlag()};
			dtm.addRow(data2);
		}
		TableColumn column = table.getColumnModel().getColumn(3);
	    column.setPreferredWidth(10); 
	}

	/**
	 * Create the frame.
	 */
	public Buecherverwaltung() {
		buecher = new Buecher();
		Buch buch = new Buch("Programmieren für Anfänger", "Jörg Schuster", "345211231-0", 1996, "Springer");
		buecher.add(buch);
		
		setTitle("B\u00FCcherverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		// Macht die einzelnen Zellen uneditierbar
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		this.showBuecherTable();
	}
}
