package model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class Parametar extends Node implements Transferable,ClipboardOwner{
	static public DataFlavor elementFlavor;
	private DataFlavor [] supportedFlavors = {elementFlavor};
	
	public Parametar(String name, Node parent) {
		super(name, parent);
	}
	
	public Parametar(Parametar parametar) {
		this.name = parametar.name;
		this.listeners = new ArrayList<>();
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}
	
	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	@Override
	public void addNode(Node node) {
		return;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		
	}

	@Override
	public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
		return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor arg0) {
		return (arg0.equals(elementFlavor)); 
	}
	
	public static Parametar copyKonstruktor(Parametar parametar){
		Parametar p = null;
		if (parametar instanceof ParametarAutor) {
			p = new ParametarAutor(parametar);
		}
		if (parametar instanceof ParametarCustom) {
			p = new ParametarCustom(parametar);
		}
		if (parametar instanceof ParametarLogo) {
			p = new ParametarLogo(parametar);
		}
		if (parametar instanceof ParametarLook) {
			p = new ParametarLook(parametar);
		}
		if (parametar instanceof ParametarNaziv) {
			p = new ParametarNaziv(parametar);
		}
		if (parametar instanceof ParametarShortcut) {
			p = new ParametarShortcut(parametar);
		}
		if (parametar instanceof ParametarUslovi) {
			p = new ParametarUslovi(parametar);
		}
		if (parametar instanceof ParametarStart) {
			p = new ParametarStart(parametar);
		}
		return p;
	}
	
}
