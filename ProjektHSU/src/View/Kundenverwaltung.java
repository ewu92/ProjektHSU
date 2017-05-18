package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kundenverwaltung extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	
	public void setVerwaltung(Buecherverwaltung bvn, Kundenverwaltung kvn){
		bv = bvn;
		kv = kvn;
	}

	/**
	 * Create the frame.
	 */
	public Kundenverwaltung() {
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
		
		JButton btnBcherverwaltung = new JButton("B\u00FCcherverwaltung");
		btnBcherverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kv.setVisible(false);
				bv.setVisible(true);
			}
		});
		btnBcherverwaltung.setBounds(624, 527, 150, 23);
		contentPane.add(btnBcherverwaltung);
	}
}
