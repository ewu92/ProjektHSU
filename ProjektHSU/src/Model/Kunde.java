package Model;

import java.util.Date;

public class Kunde {

	private String name;
	private String vorname;
	private Date geburtsdatum;
	private String addresse;
	private String email;
	
	public Kunde(String name, String vorname, Date geburtsdatum, String addresse, String email){
		this.name = name;
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.addresse = addresse;
		this.email = email;
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
	
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
