package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import instaler.ErrorHandling;
import model.Parametar;
import model.ParametarSelection;
import view.Frejm;

public class ClipboardCopy extends AbstractNAction{
	private boolean bool = false;

	public ClipboardCopy() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/clipboard_copy.png"));
		putValue(NAME, "mCopy");
		putValue(SHORT_DESCRIPTION, "Copy");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<Parametar> lista = new ArrayList<>();
		TreePath[] nizCvorova = Frejm.getInstance().getNodeTree().getSelectionPaths();
		if (nizCvorova == null || nizCvorova.length == 0) {
			ErrorHandling.handle(this, new NullPointerException());
			return;
		}
		for (int i = 0 ; i < nizCvorova.length ; i++) {
			if (!(nizCvorova[i].getLastPathComponent() instanceof Parametar)) {
				ErrorHandling.handle(this, new ClassCastException());
				return;
			}
			lista.add(Parametar.copyKonstruktor((Parametar)nizCvorova[i].getLastPathComponent()));
		}
		ParametarSelection contents = new ParametarSelection(lista);
		Frejm.getInstance().getClipboard().setContents(contents, Frejm.getInstance());
		bool = true;
	}	
	
	public boolean isBool() {
		return bool;
	}
}
