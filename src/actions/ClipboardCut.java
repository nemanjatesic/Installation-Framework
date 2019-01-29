package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import view.Frejm;

public class ClipboardCut extends AbstractNAction{
	
	public ClipboardCut() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/clipboard_cut.png"));
		putValue(NAME, "mCut");
		putValue(SHORT_DESCRIPTION, "Cut");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Frejm.getInstance().getActionManager().getClipboardCopy().actionPerformed(null);
		Frejm.getInstance().getActionManager().getCloseProjectAction().actionPerformed(null);;
	}
}
