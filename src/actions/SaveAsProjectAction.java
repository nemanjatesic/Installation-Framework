package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import app.Main;
import model.Kompanija;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import view.Frejm;

public class SaveAsProjectAction extends AbstractNAction {

	public SaveAsProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/save_as.png"));
		putValue(NAME, "mSaveAs");
		putValue(SHORT_DESCRIPTION, "Save project as");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (Frejm.getInstance().getNodeModel().getRoot() == null)
			return;
		
		if (Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Parametar || Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Modul) {
			JOptionPane.showMessageDialog(null, "Choose either Proizvod or Komapnija to save.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		TreePath[] nizCvorova = Frejm.getInstance().getNodeTree().getSelectionPaths();
		
		if (nizCvorova == null || nizCvorova.length == 1) {
			final JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null) == 1) return;
			File file = fc.getSelectedFile();
			if (file == null) {
				JOptionPane.showMessageDialog(null, "You didn't chose any files!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				
				if (Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() == null || Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Kompanija) 
					out.writeObject(Frejm.getInstance().getNodeModel().getRoot());
				else if (Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Proizvod)
					out.writeObject(Frejm.getInstance().getNodeTree().getLastSelectedPathComponent());
				
				out.close();
				fileOutputStream.close();
				Main.setFile(file);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			for (int i = 0 ; i < nizCvorova.length ; i++) {
				Node node = (Node)nizCvorova[i].getLastPathComponent();
				if (!(node instanceof Proizvod)) {
					JOptionPane.showMessageDialog(null, "Choose only Proizvods or Komapnija to save.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			final JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null) == 1) return;
			File file = fc.getSelectedFile();
			if (file == null) {
				JOptionPane.showMessageDialog(null, "You didn't chose any files!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				
				for (int i = 0 ; i < nizCvorova.length ; i++) {
					Node node = (Node)nizCvorova[i].getLastPathComponent();
					out.writeObject(node);
				}
				
				out.close();
				fileOutputStream.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
