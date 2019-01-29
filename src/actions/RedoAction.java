package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import view.Frejm;

public class RedoAction extends AbstractNAction{

	public RedoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/icons8-redo-20.png"));
		putValue(NAME, "mRedo");
		putValue(SHORT_DESCRIPTION, "Redo");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Frejm.getInstance().getCommandManager().doCommand();
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}

}
