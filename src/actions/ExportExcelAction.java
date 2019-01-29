package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ExportExcelAction extends AbstractNAction {

	public ExportExcelAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/excel.png"));
		putValue(NAME, "mExpToExcel");
		putValue(SHORT_DESCRIPTION, "Export to Excel");
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}

}
