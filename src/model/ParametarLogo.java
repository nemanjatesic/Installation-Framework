package model;

public class ParametarLogo extends Parametar{
	private String logo;

	public ParametarLogo(Parametar parametar) {
		super(parametar);
		this.logo = ((ParametarLogo)parametar).logo;
	}

	public ParametarLogo(String name, Node parent) {
		super(name,parent);
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
