package Model;

public class Buch {

	private int buchnr;
	private String titel;
	private String autor;
	private String isbn;
	private int erscheinungsjahr;
	private String verlag;
	private boolean ausleihstatus;
	
	public Buch(int buchnr, String titel, String autor, String isbn, int erscheinungsjahr, String verlag){
		this.buchnr = buchnr;
		this.titel = titel;
		this.autor = autor;
		this.isbn = isbn;
		this.erscheinungsjahr = erscheinungsjahr;
		this.verlag = verlag;
		this.ausleihstatus = false;
	}
	
	public int getBuchnr() {
		return buchnr;
	}
	public void setBuchnr(int buchnr) {
		this.buchnr = buchnr;
	}
	
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}
	public void setErscheinungsjahr(int erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}
	
	public String getVerlag() {
		return verlag;
	}
	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}

	public boolean getAusleihstatus() {
		return ausleihstatus;
	}
	public void setAusleihstatus(boolean ausleihstatus) {
		this.ausleihstatus = ausleihstatus;
	}

}
