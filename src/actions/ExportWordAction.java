package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ExportWordAction extends AbstractNAction {

	public ExportWordAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/word.png"));
		putValue(NAME, "mExpToWord");
		putValue(SHORT_DESCRIPTION, "Export to Word");
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}

}
