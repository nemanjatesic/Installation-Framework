package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import app.Main;
import view.Frejm;

public class WindowListen implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (Main.getSave()) {
			int a = JOptionPane.showConfirmDialog(null,"Some changes were made do you want to save?");
			
			if(a == 0) {
				Frejm.getInstance().getNodeTree().clearSelection();
				Frejm.getInstance().getActionManager().getSaveProjectAction().actionPerformed(null);
				((JFrame)e.getSource()).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}else if (a == 1) {
				((JFrame)e.getSource()).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}else if (a == 2 || a == -1) {
				((JFrame)e.getSource()).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
			
		}else {
			((JFrame)e.getSource()).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

}
