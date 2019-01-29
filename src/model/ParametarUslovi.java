package model;

public class ParametarUslovi extends Parametar{
	private String uslovi;
	
	public ParametarUslovi(Parametar parametar) {
		super(parametar);
		this.uslovi = ((ParametarUslovi)parametar).uslovi;
	}

	public ParametarUslovi(String name, Node parent) {
		super(name,parent);
	}
	
	public String getUslovi() {
		return uslovi;
	}
	
	public void setUslovi(String uslovi) {
		this.uslovi = uslovi;
	}
}
