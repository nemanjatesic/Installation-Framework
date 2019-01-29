package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import view.Frejm;


public class UndoAction extends AbstractNAction{

	public UndoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/icons8-undo-20.png"));
		putValue(NAME, "mUndo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Frejm.getInstance().getCommandManager().undoCommand();
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}

}
