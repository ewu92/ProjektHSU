package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Kunde;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;

public class KundeHinzufuegen extends JFrame {
	
	private static KundeHinzufuegen khf;
	private static Kundenverwaltung kv;
	
	public void setKundenverwaltung(Kundenverwaltung kvn){
		kv = kvn;
	}

	private JPanel contentPane;
	private JTextField textFieldVorname;
	private JTextField textFieldNachname;
	private JTextField textFieldTag;
	private JTextField textFieldMonat;
	private JTextField textFieldJahr;
	private JTextField textFieldWohnort;
	private JTextField textFieldStrasse;
	private JTextField textFieldHausnummer;
	private JTextField textFieldPlz;
	private JTextField textFieldEmail;
	
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
	public KundeHinzufuegen() {
		khf = this;
		setTitle("Kunde hinzuf\u00FCgen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vorname = "";
				String nachname = "";
				String tag = "";
				String monat = "";
				String jahr = "";
				String ort = "";
				String strasse = "";
				String hausnummer = "";
				String plz = "";
				String email = "";
				
				try{
					vorname = getTextWithException(textFieldVorname);
					nachname = getTextWithException(textFieldNachname);
					tag = getTextWithException(textFieldTag);
					monat = getTextWithException(textFieldMonat);
					jahr = getTextWithException(textFieldJahr);
					ort = getTextWithException(textFieldWohnort);
					strasse = getTextWithException(textFieldStrasse);
					hausnummer = getTextWithException(textFieldHausnummer);
					plz = getTextWithException(textFieldPlz);
					email = getTextWithException(textFieldEmail);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Fehler", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
				
				int tagi = 0;
				int monati = 0;
				int jahri = 0;
				int hausnummeri = 0;
				int plzi = 0;
				
				try{
					
					tagi = Integer.parseInt(tag);
					monati = Integer.parseInt(monat);
					jahri = Integer.parseInt(jahr);
					hausnummeri = Integer.parseInt(hausnummer);
					plzi = Integer.parseInt(plz);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Fehler beim Konvertieren in Integer", "Fehler", JOptionPane.OK_OPTION);
					return;
				}
				
				String date = tagi + "-" + monati + "-" + jahri;
				
				Kunde kunde = new Kunde(kv.getKundenNr(), nachname, vorname, date, ort, plzi, strasse, hausnummeri, email);
				kv.incKundenNr();
				kv.getKunden().add(kunde);
				kv.showKundenTable();
				
				khf.setVisible(false);
			}
		});
		btnOk.setBounds(100, 203, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				khf.setVisible(false);
			}
		});
		btnAbbrechen.setBounds(275, 203, 106, 23);
		contentPane.add(btnAbbrechen);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(10, 11, 101, 14);
		contentPane.add(lblVorname);
		
		textFieldVorname = new JTextField();
		textFieldVorname.setBounds(121, 8, 188, 20);
		contentPane.add(textFieldVorname);
		textFieldVorname.setColumns(10);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setBounds(10, 36, 101, 14);
		contentPane.add(lblNachname);
		
		textFieldNachname = new JTextField();
		textFieldNachname.setBounds(121, 33, 188, 20);
		contentPane.add(textFieldNachname);
		textFieldNachname.setColumns(10);
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		lblGeburtsdatum.setBounds(10, 61, 100, 14);
		contentPane.add(lblGeburtsdatum);
		
		textFieldTag = new JTextField();
		textFieldTag.setBounds(167, 58, 35, 20);
		contentPane.add(textFieldTag);
		textFieldTag.setColumns(10);
		
		JLabel lblTag = new JLabel("Tag:");
		lblTag.setBounds(121, 61, 36, 14);
		contentPane.add(lblTag);
		
		JLabel lblMonat = new JLabel("Monat:");
		lblMonat.setBounds(212, 61, 54, 14);
		contentPane.add(lblMonat);
		
		textFieldMonat = new JTextField();
		textFieldMonat.setColumns(10);
		textFieldMonat.setBounds(264, 58, 41, 20);
		contentPane.add(textFieldMonat);
		
		JLabel lblJahr = new JLabel("Jahr:");
		lblJahr.setBounds(316, 61, 35, 14);
		contentPane.add(lblJahr);
		
		textFieldJahr = new JTextField();
		textFieldJahr.setColumns(10);
		textFieldJahr.setBounds(352, 58, 71, 20);
		contentPane.add(textFieldJahr);
		
		JLabel lblWohnort = new JLabel("Wohnort:");
		lblWohnort.setBounds(10, 86, 101, 14);
		contentPane.add(lblWohnort);
		
		textFieldWohnort = new JTextField();
		textFieldWohnort.setColumns(10);
		textFieldWohnort.setBounds(121, 83, 188, 20);
		contentPane.add(textFieldWohnort);
		
		JLabel lblStrae = new JLabel("Stra\u00DFe:");
		lblStrae.setBounds(10, 111, 46, 14);
		contentPane.add(lblStrae);
		
		textFieldStrasse = new JTextField();
		textFieldStrasse.setColumns(10);
		textFieldStrasse.setBounds(121, 108, 188, 20);
		contentPane.add(textFieldStrasse);
		
		JLabel lblHausnummer = new JLabel("Hausnummer:");
		lblHausnummer.setBounds(316, 111, 86, 14);
		contentPane.add(lblHausnummer);
		
		textFieldHausnummer = new JTextField();
		textFieldHausnummer.setColumns(10);
		textFieldHausnummer.setBounds(412, 108, 54, 20);
		contentPane.add(textFieldHausnummer);
		
		JLabel lblPlz = new JLabel("Posleitzahl:");
		lblPlz.setBounds(316, 86, 86, 14);
		contentPane.add(lblPlz);
		
		textFieldPlz = new JTextField();
		textFieldPlz.setColumns(10);
		textFieldPlz.setBounds(412, 83, 54, 20);
		contentPane.add(textFieldPlz);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 136, 46, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(121, 133, 188, 20);
		contentPane.add(textFieldEmail);
	}
}
