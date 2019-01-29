package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ImportPDFAction extends AbstractNAction {

	public ImportPDFAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/pdf.png"));
		putValue(NAME, "mImpToPDF");
		putValue(SHORT_DESCRIPTION, "Import to PDF");
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}

}
