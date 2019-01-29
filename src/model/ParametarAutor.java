package model;

public class ParametarAutor extends Parametar{
	private String ime;
	private String prezime;
	
	public ParametarAutor(Parametar parametar) {
		super(parametar);
		this.ime = ((ParametarAutor)parametar).ime;
		this.prezime = ((ParametarAutor)parametar).prezime;
	}
	
	public ParametarAutor(String name, Node parent) {
		super(name,parent);
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
