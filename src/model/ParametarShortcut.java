package model;

public class ParametarShortcut extends Parametar{
	private boolean shortcut;
	
	public ParametarShortcut(Parametar parametar) {
		super(parametar);
		this.shortcut = ((ParametarShortcut)parametar).shortcut;
	}

	public ParametarShortcut(String name, Node parent) {
		super(name,parent);
	}

	public boolean isShortcut() {
		return shortcut;
	}

	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}
}
