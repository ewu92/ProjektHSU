package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Kunden;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kundenverwaltung extends JFrame {

	private Kunden kunden;
	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	private static KundeHinzufuegen khf;
	
	public Kunden getKunden(){
		return kunden;
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
		khf = new KundeHinzufuegen();
		khf.setVisible(false);
		khf.setKundenverwaltung(this);
		
		setTitle("Kundenverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 11, 764, 338);
		contentPane.add(list);
		
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
		btnKundeLoeschen.setBounds(41, 405, 135, 23);
		contentPane.add(btnKundeLoeschen);
	}
}
