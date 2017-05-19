package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Buch;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuchHinzufuegen extends JFrame {

	private JPanel contentPane;
	private static Buecherverwaltung bv;
	private static BuchHinzufuegen bhf;
	private JTextField textFieldTitel;
	private JTextField textFieldAutor;
	private JTextField textFieldIsbn;
	private JTextField textFieldJahr;
	private JTextField textFieldVerlag;
	
	public void setBuecherverwaltung(Buecherverwaltung bvn){
		bv = bvn;
	}
	
	public String getTextWithException(JTextField field) throws Exception{
		String str = field.getText();
		if(str.isEmpty()){
			throw new Exception();
		}
		return str;
	}

	/**
	 * Create the frame.
	 */
	public BuchHinzufuegen() {
		setTitle("Buch hinzuf\u00FCgen");
		bhf = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setBounds(10, 11, 63, 14);
		contentPane.add(lblTitel);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 36, 63, 14);
		contentPane.add(lblAutor);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 61, 63, 14);
		contentPane.add(lblIsbn);
		
		JLabel lblErscheinungjahr = new JLabel("Erscheinungjahr:");
		lblErscheinungjahr.setBounds(10, 86, 99, 14);
		contentPane.add(lblErscheinungjahr);
		
		JLabel lblVerlag = new JLabel("Verlag:");
		lblVerlag.setBounds(10, 111, 63, 14);
		contentPane.add(lblVerlag);
		
		textFieldTitel = new JTextField();
		textFieldTitel.setBounds(83, 8, 310, 20);
		contentPane.add(textFieldTitel);
		textFieldTitel.setColumns(10);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(83, 33, 310, 20);
		contentPane.add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(83, 58, 310, 20);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		textFieldJahr = new JTextField();
		textFieldJahr.setBounds(119, 83, 99, 20);
		contentPane.add(textFieldJahr);
		textFieldJahr.setColumns(10);
		
		textFieldVerlag = new JTextField();
		textFieldVerlag.setBounds(83, 108, 310, 20);
		contentPane.add(textFieldVerlag);
		textFieldVerlag.setColumns(10);
		
		JButton buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titel = null;
				String autor = null;
				String isbn = null;
				String erscheinungsjahr = null;
				String verlag = null;
				
				try{
					titel = getTextWithException(textFieldTitel);
					autor = getTextWithException(textFieldAutor);
					isbn = getTextWithException(textFieldIsbn);
					erscheinungsjahr = getTextWithException(textFieldJahr);
					verlag = getTextWithException(textFieldVerlag);
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Fehler", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
				
				int jahri = 0;
				
				try{
					
					jahri = Integer.parseInt(erscheinungsjahr);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Fehler beim Konvertieren in Integer", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
				
				Buch buch = new Buch(bv.getBuchNr(), titel, autor, isbn, jahri, verlag);
				bv.incBuchNr();
				bv.getBuecher().add(buch);
				bv.showBuecherTable(bv.getBuecher());
				
				bhf.setVisible(false);
			}
		});
		buttonOk.setBounds(138, 227, 89, 23);
		contentPane.add(buttonOk);
		
		JButton buttonAbbrechen = new JButton("Abbrechen");
		buttonAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bhf.setVisible(false);
			}
		});
		buttonAbbrechen.setBounds(313, 227, 106, 23);
		contentPane.add(buttonAbbrechen);
	}
}
