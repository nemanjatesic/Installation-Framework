package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import instaler.ErrorHandling;
import view.Frejm;

public class DeleteTabAction extends AbstractNAction{

	public DeleteTabAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/close.png"));
		putValue(NAME, "mDelete");
		putValue(SHORT_DESCRIPTION, "Delete tab");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Frejm.getInstance().getPanCenterUp().getTabbedPane().removeTabAt(Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedIndex());
		}catch (IndexOutOfBoundsException e) {
			ErrorHandling.handle(this, e);
		}
	}
	
}
