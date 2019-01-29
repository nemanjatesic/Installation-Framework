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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.Main;
import commands.AddCommand;
import instaler.ErrorHandling;
import model.Kompanija;
import model.Node;
import model.Proizvod;
import view.Frejm;

public class AddTreeToNodeAction extends AbstractNAction{

	public AddTreeToNodeAction() {
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.ALT_MASK));
			putValue(SMALL_ICON, loadIcon("src/resursi/loadToTreeNode.png"));
			putValue(NAME, "mLoad");
			putValue(SHORT_DESCRIPTION, "Load to tree node");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		final JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == 1) return;
		File file = fc.getSelectedFile();
		if (file == null) {
			ErrorHandling.handle(this, new NullPointerException());
			return;
		}
		
		FileInputStream fileOutputStream = null;
		ObjectInputStream in = null;
		ArrayList<Node> lista = new ArrayList<>();
		
		try {
			fileOutputStream = new FileInputStream(file);
			in = new ObjectInputStream(fileOutputStream);
			
			while(true) {
				Object obj = in.readObject();
				lista.add((Node)obj);
				((Kompanija)Frejm.getInstance().getNodeModel().getRoot()).addNode((Proizvod)obj);
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
			Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
		}catch (FileNotFoundException b) {
			b.printStackTrace();
		}catch (IOException c) {
			c.printStackTrace();
		}catch (ClassNotFoundException d) {
			d.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}

}
