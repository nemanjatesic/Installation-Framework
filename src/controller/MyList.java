package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.Main;
import model.Node;
import model.Parametar;
import model.ParametarAutor;
import model.ParametarCustom;
import model.ParametarLogo;
import model.ParametarNaziv;
import model.ParametarUslovi;
import model.ScrollPane;
import view.Frejm;

public class MyList implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String s = "";
		if (arg0.getSource() instanceof JTextArea)
			s = ((JTextArea)arg0.getSource()).getText();
		if (arg0.getSource() instanceof JTextField)
			s = ((JTextField)arg0.getSource()).getText();
		ScrollPane sp = (ScrollPane) Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedComponent();
		Main.setSave(true);
		Node node = sp.getNode();
		if (!(node instanceof Parametar))
			node.setSastav(s);
		else {
			if (node instanceof ParametarAutor) {
				if (sp.getView().getTfIme() == arg0.getSource()) ((ParametarAutor)node).setIme(s);
				if (sp.getView().getTfPrezime() == arg0.getSource()) ((ParametarAutor)node).setPrezime(s);
			}else if (node instanceof ParametarCustom) {
				
			}else if (node instanceof ParametarNaziv) {
				((ParametarNaziv)node).setNaziv(s);
			}else if (node instanceof ParametarUslovi) {
				((ParametarUslovi)node).setUslovi(s);
			}else if (node instanceof ParametarLogo) {
				((ParametarLogo)node).setLogo(s);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
