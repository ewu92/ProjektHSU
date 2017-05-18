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

public class Buecherverwaltung extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static Kundenverwaltung kv;
	

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

	/**
	 * Create the frame.
	 */
	public Buecherverwaltung() {
		setTitle("B\u00FCcherverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 11, 764, 338);
		contentPane.add(list);
		
		JButton btnKundenverwaltung = new JButton("Kundenverwaltung");
		btnKundenverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bv.setVisible(false);
				kv.setVisible(true);
			}
		});
		btnKundenverwaltung.setBounds(624, 527, 150, 23);
		contentPane.add(btnKundenverwaltung);
	}
}
