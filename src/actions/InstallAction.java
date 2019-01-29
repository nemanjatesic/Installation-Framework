package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.Main;
import instaler.ErrorHandling;
import model.Proizvod;
import view.Frejm;
import view.ModulSelectionView;
import model.Node;
import model.Parametar;
import model.ParametarLogo;
import model.ParametarUslovi;
import model.InstallModel;
import model.Kompanija;
import model.Modul;

public class InstallAction extends AbstractNAction {

	public InstallAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/installer-24.png"));
		putValue(NAME, "mInstall");
		putValue(SHORT_DESCRIPTION, "Install");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		int chosen = fileChooser.showOpenDialog(null);
		File file = null;
		
		if (chosen != 1) {
			try {
				while (!fileChooser.getSelectedFile().exists()) {
					ErrorHandling.handle("File not found",null);
					fileChooser.setSelectedFile(null);
					chosen = fileChooser.showOpenDialog(null);
				}
				if (chosen == 1)
					fileChooser.setSelectedFile(null);

				file = fileChooser.getSelectedFile();
				FileInputStream fileInputStream = null;
				ObjectInputStream in = null;
				try {
					fileInputStream = new FileInputStream(file);
					in = new ObjectInputStream(fileInputStream);

					Object curr = in.readObject();
					
					in.close();
					fileInputStream.close();

					if (!(curr instanceof Proizvod)) {
						in.close();
						fileInputStream.close();
						return;
					}
					/*
					((Kompanija) Frejm.getInstance().getNodeModel().getRoot()).addNode((Node) curr);
					((Node) curr).setParent((Kompanija) Frejm.getInstance().getNodeModel().getRoot());
					*/
					String s = fileChooser.getSelectedFile().getAbsolutePath();
					String tmp = s;
					int cnt = s.length() - 1;
					
					while (cnt > 0) {
						if (s.charAt(cnt) == '\\' || s.charAt(cnt) == '/')
							break;
						cnt--;
					}
					
					s = s.substring(0, cnt);
					((Proizvod) curr).setFilePath(s + "/" + ((Proizvod) curr).getFilePath());
					ArrayList<Parametar> list = new ArrayList<>();
					for (int i = 0; i < ((Proizvod) curr).getDeca().size(); i++) {
						if (((Proizvod) curr).getDeca().get(i) instanceof ParametarLogo || ((Proizvod) curr).getDeca().get(i) instanceof ParametarUslovi)
							list.add((Parametar) ((Proizvod) curr).getDeca().get(i));
						else {
							if (((Proizvod) curr).getDeca().get(i) instanceof Modul) {
								Modul mm = (Modul) ((Proizvod) curr).getDeca().get(i);
								for (int j = 0; j < mm.getDeca().size(); j++) {
									if (mm.getDeca().get(j) instanceof ParametarLogo || mm.getDeca().get(j) instanceof ParametarUslovi)
										list.add((Parametar) mm.getDeca().get(j));
								}
							}
						}
					}
					for (Parametar p : list) {
						if (p instanceof ParametarUslovi)
							((ParametarUslovi) p).setUslovi(s + "/" + ((ParametarUslovi) p).getUslovi());
						else
							((ParametarLogo) p).setLogo(s + "/" + ((ParametarLogo) p).getLogo());
					}
					
					
					Proizvod proizvodd = (Proizvod) curr;
					
					InstallModel installModel = new InstallModel();
					
					Frejm.getInstance().setInstallModel(installModel);
					installModel.setSelectedPath(file);
					installModel.inicijalizuj(proizvodd);
					
					
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
				}catch (ClassCastException e) {

				}catch (IOException e) {
					e.printStackTrace();
				}
			}catch (NullPointerException e) {
				
			}catch (Exception e) {
				
			}
		}
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}
	
}
