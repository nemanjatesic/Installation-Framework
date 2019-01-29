package model;

public class Modul extends Node{
	boolean selected = false;
	
	public Modul(String name, Node parent) {
		super(name, parent);
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
