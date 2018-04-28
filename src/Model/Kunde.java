package Model;

import java.util.Date;

public class Kunde {

	private int kundennr;
	private String name;
	private String vorname;
	private Date geburtsdatum;
	private String ort;
	private int plz;
	private String strasse;
	private int hausnummer;
	private String email;
	
	public Kunde(int kundennr, String name, String vorname, Date geburtsdatum, String ort, int plz, String strasse, int hausnummer, String email){
		this.kundennr = kundennr;
		this.name = name;
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.ort = ort;
		this.plz = plz;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.email = email;
	}
	
	public int getKundennr() {
		return kundennr;
	}
	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
