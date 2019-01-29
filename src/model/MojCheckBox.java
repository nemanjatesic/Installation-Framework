package model;

import javax.swing.JCheckBox;

public class MojCheckBox extends JCheckBox{
	private Node node;
	
	public MojCheckBox(String string) {
		super(string);
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
}
