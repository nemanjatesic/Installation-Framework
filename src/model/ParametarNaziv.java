package model;

public class ParametarNaziv extends Parametar{
	private String naziv;

	public ParametarNaziv(Parametar parametar) {
		super(parametar);
		this.naziv = ((ParametarNaziv)parametar).naziv;
	}

	public ParametarNaziv(String name, Node parent) {
		super(name,parent);
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
