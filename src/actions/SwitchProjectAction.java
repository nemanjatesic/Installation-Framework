package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import model.ScrollPane;
import view.Frejm;

public class SwitchProjectAction extends AbstractNAction {

	public SwitchProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/switch.png"));
		putValue(NAME, "mSwitch");
		putValue(SHORT_DESCRIPTION, "Switch project");
	}

	public void actionPerformed(ActionEvent arg0) {
		int maxBroj = Frejm.getInstance().getPanCenterUp().getTabbedPane().getTabCount();
		if (maxBroj == 0) return;
		int broj = Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedIndex();
		if (++broj >= maxBroj) broj = 0;
		ScrollPane sp = (ScrollPane)Frejm.getInstance().getPanCenterUp().getTabbedPane().getComponentAt(broj);
		Frejm.getInstance().getPanCenterUp().update(sp);
	}

}


