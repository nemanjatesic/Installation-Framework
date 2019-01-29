package model;

import java.util.ArrayList;

public class Proizvod extends Node{
	private String filePath;
	
	public Proizvod(String name, Node parent) {
		super(name, parent);
	}

	@Override
	public void addNode(Node node) {
		super.addNode(node);
		ArrayList<Node> moduli = new ArrayList<>();
		ArrayList<Node> parametri = new ArrayList<>();
		
		for (Node n : this.getDeca()) {
			if (n instanceof Modul) 
				moduli.add(n);
			else if (n instanceof Parametar)
				parametri.add(n);
		}
		
		this.getDeca().clear();
		
		for (Node n : moduli) 
			this.getDeca().add((Modul)n);
		
		for (Node n : parametri)
			this.getDeca().add((Parametar)n);
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
