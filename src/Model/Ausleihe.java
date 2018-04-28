package Model;

import java.util.Date;

public class Ausleihe {
	private Buch buch;
	private Kunde kunde;
	private Date ausgeliehen;
	private Date rueckgabe;
	private int verlaengerungen;
	
	public Ausleihe(Buch buch, Kunde kunde, Date ausgeliehen, Date rueckgabe, int verlaengerungen){
		this.setBuch(buch);
		this.setKunde(kunde);
		this.setAusgeliehen(ausgeliehen);
		this.setRueckgabe(rueckgabe);
		this.setVerlaengerungen(verlaengerungen);
	}

	public Buch getBuch() {
		return buch;
	}
	public void setBuch(Buch buch) {
		this.buch = buch;
	}

	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Date getAusgeliehen() {
		return ausgeliehen;
	}
	public void setAusgeliehen(Date ausgeliehen) {
		this.ausgeliehen = ausgeliehen;
	}

	public Date getRueckgabe() {
		return rueckgabe;
	}
	public void setRueckgabe(Date rueckgabe) {
		this.rueckgabe = rueckgabe;
	}

	public int getVerlaengerungen() {
		return verlaengerungen;
	}
	public void setVerlaengerungen(int verlaengerungen) {
		this.verlaengerungen = verlaengerungen;
	}

}
