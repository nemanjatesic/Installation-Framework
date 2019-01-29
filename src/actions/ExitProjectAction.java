package actions;

import java.awt.CardLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.Modul;
import model.Node;
import model.Parametar;
import model.ParametarAutor;
import model.Proizvod;
import view.Frejm;
import view.ModulSelectionView;

public class ExitProjectAction extends AbstractNAction {
	
	private ArrayList<Parametar> parametri = new ArrayList<>();
	private int broj = 0;
	
	public ExitProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/exit.png"));
		putValue(NAME, "mExit");
		putValue(SHORT_DESCRIPTION, "Exit project");
	}

	public void actionPerformed(ActionEvent arg0) {
		Frejm.getInstance().dispose();
		System.exit(0);
		/*ModulSelectionView m = new ModulSelectionView();
		Node n = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
		ArrayList<Modul> modul = new ArrayList<>();
		if (n instanceof Proizvod) {
			for (Node mod : n.getDeca()) {
				if (mod instanceof Modul) {
					modul.add((Modul)mod);
				}
			}
			m.modulView(modul);
			m.setVisible(true);
		}else {
			return;
		}*/
	}
	/*
	public void update() {
		if (broj != parametri.size()) {
			ModulSelectionView mm = new ModulSelectionView();
			mm.parametarView(parametri.get(broj),parametri);
			mm.setVisible(true);
		}else {
			parametri = new ArrayList<>();
			broj = 0;
		}
	}
	 
	public void pomocna(ArrayList<Modul> selektovani) {
		
		broj = 0;
		parametri = new ArrayList<>();
		Node tmp = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
		if (tmp instanceof Proizvod) {
			Proizvod proizvod = (Proizvod)tmp;
			for (Node node : proizvod.getDeca()) {
				if (node instanceof Parametar) {
					parametri.add((Parametar)node);
				}
			}
		}else return;
		
		for (int i = 0 ; i < selektovani.size() ; i++) {
			selektovani.get(i).setSelected(false);
			for (int j = 0 ; j < selektovani.get(i).getDeca().size() ; j++) {
				parametri.add((Parametar)selektovani.get(i).getDeca().get(j));
			}
		}
		update();
	}
	
	public int getBroj() {
		return broj;
	}
	
	public void setBroj(int broj) {
		this.broj = broj;
	}
	*/
}
