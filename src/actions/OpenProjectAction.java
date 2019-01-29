package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.Main;
import model.Kompanija;
import model.Node;
import model.Proizvod;
import view.Frejm;

public class OpenProjectAction extends AbstractNAction {

	public OpenProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/open.png"));
		putValue(NAME, "mOpen");
		putValue(SHORT_DESCRIPTION, "Open project");
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if(Main.getSave()) {
			int a = JOptionPane.showConfirmDialog(null,"Some changes were made do you want to save?");
			if(a == 0) Frejm.getInstance().getActionManager().getSaveProjectAction().actionPerformed(null);
			if(a == 2 || a == -1) return;
		}
		
		final JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == 1) return;
		File file = fc.getSelectedFile();
		if (file == null) {
			JOptionPane.showMessageDialog(null, "You didn't chose any files!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			FileInputStream fileOutputStream = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileOutputStream);
			
			Object obj = in.readObject();
			if (obj instanceof Kompanija) {
				Frejm.getInstance().getNodeModel().setRoot((Kompanija)obj);
				Queue<Node> que = new LinkedList<>();
				
				que.add((Node) Frejm.getInstance().getNodeModel().getRoot());
	
				while (!que.isEmpty()) {
					Node node = que.poll();
					node.addListener(Frejm.getInstance().getPanCenterDown());
					for (Node tmp : node.getDeca()) {
						que.add(tmp);
					}
				}
			}else {
				Frejm.getInstance().getNodeModel().setRoot(new Kompanija("Kompanija", null));
				try {
					
					while(true) {
						((Kompanija)Frejm.getInstance().getNodeModel().getRoot()).addNode((Proizvod)obj);
						obj = in.readObject();
					}
					
				}catch (EOFException e) {
					try {
						in.close();
						fileOutputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					Queue<Node> que = new LinkedList<>();
					
					que.add((Node)Frejm.getInstance().getNodeModel().getRoot());

					while (!que.isEmpty()) {
						Node node = que.poll();
						if (node.getParent() != null) node.setParent((Node)Frejm.getInstance().getNodeModel().getRoot());
						if(node.getListeners().size() == 0)
							node.addListener(Frejm.getInstance().getPanCenterDown());
						for (Node tmp : node.getDeca()) {
							que.add(tmp);
						}
					}
				}catch (FileNotFoundException b) {
					b.printStackTrace();
				}catch (IOException c) {
					c.printStackTrace();
				}catch (ClassNotFoundException d) {
					d.printStackTrace();
				}
				
			}
					
			in.close();
			fileOutputStream.close();
			SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
		}catch (StreamCorruptedException e) {
			e.printStackTrace();
		}catch (Exception e1) {
			
		}
	}
}
