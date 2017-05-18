package Model;

public class Buch {

	private String titel;
	private String autor;
	private String isbn;
	private int erscheinungsjahr;
	private String verlag;
	
	public Buch(String titel, String autor, String isbn, int erscheinungsjahr, String verlag){
		this.titel = titel;
		this.autor = autor;
		this.isbn = isbn;
		this.erscheinungsjahr = erscheinungsjahr;
		this.verlag = verlag;
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
	
}
