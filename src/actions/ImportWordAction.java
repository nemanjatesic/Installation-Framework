package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import model.Parametar;
import view.Frejm;
import view.ParametarView;

public class ImportWordAction extends AbstractNAction {

	public ImportWordAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/word.png"));
		putValue(NAME, "mImpToWord");
		putValue(SHORT_DESCRIPTION, "Import to Word");
	}

	public void actionPerformed(ActionEvent arg0) {
		Frejm.getInstance().getPanCenterUp().getTabbedPane().addTab("cao", new ParametarView((Parametar)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent()));
	}

}
