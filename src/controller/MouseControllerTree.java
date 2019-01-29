package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;

import gui.NodeTree;
import instaler.PopUp;
import model.Node;
import model.Parametar;
import model.ScrollPane;
import view.Frejm;

public class MouseControllerTree implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isPopupTrigger()) {
			doPop(e);
		}    
        if (e.getClickCount() == 1) {
        	if(e.getSource() instanceof JTabbedPane) {
        		JTabbedPane tp = (JTabbedPane)e.getSource();
        		ScrollPane sp = null;
        		if (tp.getSelectedComponent() instanceof ScrollPane) {
        			sp = (ScrollPane)tp.getSelectedComponent();
        		}
        		if (sp == null) return;
        		Frejm.getInstance().getPanCenterUp().update(sp);
        	}else {
        		Node node = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
        		if (node == null) return;
        		Frejm.getInstance().getPanCenterUp().dodajTab(node);
        		Frejm.getInstance().getPanCenterDown().update(((ScrollPane)Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedComponent()));
        	}
        }
    }

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof NodeTree){
			if (Frejm.getInstance().getNodeTree().getPathForLocation(e.getX(), e.getY()) != null) {
				Frejm.getInstance().getActionManager().getCloseProjectAction().setEnabled(true);
				Frejm.getInstance().getActionManager().getAddTreeToNodeAction().setEnabled(true);
				if (Frejm.getInstance().getNodeTree().getPathForLocation(e.getX(), e.getY()).getLastPathComponent() instanceof Parametar) {
					Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
				}else {
					if(!Frejm.getInstance().getKorisnik().equals("User"))
						Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(true);
				}
			}else if (Frejm.getInstance().getNodeModel().getRoot() != null) {
				Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
				Frejm.getInstance().getActionManager().getCloseProjectAction().setEnabled(false);
				Frejm.getInstance().getActionManager().getAddTreeToNodeAction().setEnabled(false);
				Frejm.getInstance().getNodeTree().clearSelection();
			}
		}else {
			Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
			Frejm.getInstance().getNodeTree().clearSelection();
		}
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			if (Frejm.getInstance().getNodeTree().getSelectionPaths() == null || Frejm.getInstance().getNodeTree().getSelectionPaths().length == 1)
				Frejm.getInstance().getNodeTree().setSelectionPath(Frejm.getInstance().getNodeTree().getPathForLocation(e.getX(), e.getY()));
            doPop(e);
		}
		
	}
	
	private void doPop(MouseEvent e){
		if (Frejm.getInstance().getNodeTree().getPathForLocation(e.getX(), e.getY()) != null) {
			Frejm.getInstance().getActionManager().getExpandAction().setCooridantes(e.getX(), e.getY());
			Frejm.getInstance().getActionManager().getCollapseAction().setCooridantes(e.getX(), e.getY());
			PopUp menu = new PopUp();
	        menu.show(e.getComponent(), e.getX(), e.getY());
	        if(Frejm.getInstance().getNodeTree().getPathForLocation(e.getX(), e.getY()).getLastPathComponent() instanceof Parametar) {
	        	Frejm.getInstance().getActionManager().getClipboardCopy().setEnabled(true);
	        	Frejm.getInstance().getActionManager().getClipboardCut().setEnabled(true);
	        	Frejm.getInstance().getActionManager().getClipboardPaste().setEnabled(false);
	        }else {
	        	Frejm.getInstance().getActionManager().getClipboardCopy().setEnabled(false);
	        	Frejm.getInstance().getActionManager().getClipboardCut().setEnabled(false);
	        	if (Frejm.getInstance().getActionManager().getClipboardCopy().isBool())
	        		Frejm.getInstance().getActionManager().getClipboardPaste().setEnabled(true);
	        }
		}
    }
}
