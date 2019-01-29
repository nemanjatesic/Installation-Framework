package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ImportExcelAction extends AbstractNAction {

	public ImportExcelAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/excel.png"));
		putValue(NAME, "mImpToExcel");
		putValue(SHORT_DESCRIPTION, "Import to Excel");
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}

}
