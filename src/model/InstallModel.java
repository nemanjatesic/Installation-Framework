package model;

import java.awt.Dialog.ModalityType;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import view.Frejm;
import view.ModulSelectionView;

public class InstallModel {

	private ArrayList<Parametar> parametri = new ArrayList<>();
	private int broj = 0;
	private Node proizvodGlavni;
	private boolean startAfterInstall = false;
	private boolean desktopShortcut = false;
	private Path chosenPath;
	private File selectedPath;
	
	public InstallModel() {
		super();
	}
	
	public void inicijalizuj(Proizvod n) {
		proizvodGlavni = n;
		ModulSelectionView m = new ModulSelectionView();
		m.setModalityType(ModalityType.APPLICATION_MODAL);
		ArrayList<Modul> modul = new ArrayList<>();
		if (n instanceof Proizvod) {
			for (Node mod : n.getDeca()) {
				if (mod instanceof Modul) {
					modul.add((Modul)mod);
					((Modul) mod).setSelected(false);
				}
			}
			m.modulView(modul);
			m.setVisible(true);
		}else {
			return;
		}
	}
	
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
		
		Node tmp = proizvodGlavni;
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
	
	public boolean isStartAfterInstall() {
		return startAfterInstall;
	}

	public void setStartAfterInstall(boolean startAfterInstall) {
		this.startAfterInstall = startAfterInstall;
	}

	public boolean isDesktopShortcut() {
		return desktopShortcut;
	}

	public void setDesktopShortcut(boolean desktopShortcut) {
		this.desktopShortcut = desktopShortcut;
	}

	public Path getChosenPath() {
		return chosenPath;
	}

	public void setChosenPath(Path chosenPath) {
		this.chosenPath = chosenPath;
	}

	public File getSelectedPath() {
		return selectedPath;
	}

	public void setSelectedPath(File selectedPath) {
		this.selectedPath = selectedPath;
	}
	
	public Node getProizvodGlavni() {
		return proizvodGlavni;
	}
	
}
