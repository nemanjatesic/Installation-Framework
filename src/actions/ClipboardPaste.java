package actions;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import commands.AddCommand;
import instaler.ErrorHandling;
import model.Kompanija;
import model.Node;
import model.Parametar;
import model.ParametarSelection;
import view.Frejm;

public class ClipboardPaste extends AbstractNAction{

	public ClipboardPaste() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/clipboard_paste.png"));
		putValue(NAME, "mPaste");
		putValue(SHORT_DESCRIPTION, "Paste");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Parametar> par = (ArrayList<Parametar>) Frejm.getInstance().getClipboard().getContents(Frejm.getInstance().getActionManager()).getTransferData(ParametarSelection.elementFlavor);
			ArrayList<Parametar> tmp = new ArrayList<>();
			for (Parametar p : par) tmp.add(Parametar.copyKonstruktor(p));
			if (Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Kompanija || Frejm.getInstance().getNodeTree().getLastSelectedPathComponent() instanceof Parametar) {
				ErrorHandling.handle(this, new ClassCastException());
				return;
			}
			Node node = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
			ArrayList<Node> lista = new ArrayList<>();
			for (Parametar p : tmp) {
				p.setParent(node);
				node.addNode(p);
				lista.add((Node)p);
			}
			Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
			SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
		} catch (UnsupportedFlavorException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
