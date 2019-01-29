package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import app.Main;
import commands.RemoveCommand;
import instaler.ErrorHandling;
import model.Kompanija;
import model.Node;
import view.Frejm;

public class CloseProjectAction extends AbstractNAction {

	public CloseProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/close.png"));
		putValue(NAME, "mClose");
		putValue(SHORT_DESCRIPTION, "Close project");
	}

	public void actionPerformed(ActionEvent arg0) {
		ArrayList<Node> lista = new ArrayList<>();
		if (Frejm.getInstance().getNodeTree().getSelectionPaths() != null && Frejm.getInstance().getNodeTree().getSelectionPaths().length == 1) {
			if (arg0 != null) 
				if(JOptionPane.showConfirmDialog(Frejm.getInstance(),"Are you sure you want to delete those nodes ?") != 0) 
					return;
			Object tmp = Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
			if (tmp == null) {
				ErrorHandling.handle(this, new NullPointerException());
				return;
			}
			if (tmp instanceof Kompanija) {
				Frejm.getInstance().getNodeModel().setRoot(null);
				Frejm.getInstance().getPanCenterUp().obrisi((Node)tmp);
				Main.setSave(true);
			}else {
				Frejm.getInstance().getPanCenterUp().obrisi((Node)tmp);
				Node node = (Node)tmp;
				Node par = (Node)node.getParent();
				node.setBroj(par.getChildIndex(node));
				System.out.println(node.getBroj());
				par.remove(node);
				Main.setSave(true);
			}
			lista.add((Node)tmp);
			Frejm.getInstance().getCommandManager().addCommand(new RemoveCommand(lista));
		}else if (Frejm.getInstance().getNodeTree().getSelectionPaths() != null && Frejm.getInstance().getNodeTree().getSelectionPaths().length != 1) {
			TreePath[] paths = Frejm.getInstance().getNodeTree().getSelectionPaths();
			for (int i = 0 ; i < paths.length ; i++) 
				if (((Node)paths[i].getLastPathComponent()).getParent() == null) {
					JOptionPane.showMessageDialog(null, "You can't delete Kompanija and something else in the `same time.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			if (arg0 != null) 
				if(JOptionPane.showConfirmDialog(Frejm.getInstance(),"Are you sure you want to delete those nodes ?") != 0) 
					return;
			for (int i = 0 ; i < paths.length ; i++) {
				Node node = (Node)paths[i].getLastPathComponent();
				Node par = (Node)node.getParent();
				node.setBroj(par.getChildIndex(node));
			}
			for (int i = 0 ; i < paths.length ; i++) {
				Node node = (Node)paths[i].getLastPathComponent();
				lista.add(node);
				Frejm.getInstance().getPanCenterUp().obrisi((Node)node);
				Node par = (Node)node.getParent();
				if (par != null)par.remove(node);
				else Frejm.getInstance().getNodeModel().setRoot(null);
				Main.setSave(true);
			}
			Frejm.getInstance().getCommandManager().addCommand(new RemoveCommand(lista));
		}
		
		Frejm.getInstance().getNodeTree().clearSelection();
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}
	/*
	private void incijalizujBrojeve(ArrayList<Node> nodes) {
		
	}
	*/
}
