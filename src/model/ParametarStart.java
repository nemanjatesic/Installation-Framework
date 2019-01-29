package model;

public class ParametarStart extends Parametar{
	private boolean startAfter;
	
	public ParametarStart(Parametar parametar) {
		super(parametar);
		this.startAfter = ((ParametarStart)parametar).startAfter;
	}

	public ParametarStart(String name, Node parent) {
		super(name,parent);
	}
	
	public boolean isStartAfter() {
		return startAfter;
	}
	
	public void setStartAfter(boolean startAfter) {
		this.startAfter = startAfter;
	}
}
