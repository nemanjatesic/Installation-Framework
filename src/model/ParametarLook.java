package model;

public class ParametarLook extends Parametar{
	private String lookAndFeel;

	public ParametarLook(Parametar parametar) {
		super(parametar);
		this.lookAndFeel = ((ParametarLook)parametar).lookAndFeel;
	}

	public ParametarLook(String name, Node parent) {
		super(name,parent);
	}
	
	public String getLookAndFeel() {
		return lookAndFeel;
	}
	
	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}
}
