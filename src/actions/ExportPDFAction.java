package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ExportPDFAction extends AbstractNAction {

	public ExportPDFAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/pdf.png"));
		putValue(NAME, "mExpToPDF");
		putValue(SHORT_DESCRIPTION, "Export to PDF");
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}

}
