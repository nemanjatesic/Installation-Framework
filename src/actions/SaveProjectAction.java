package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.KeyStroke;

import app.Main;
import view.Frejm;

public class SaveProjectAction extends AbstractNAction {

	public SaveProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/save.png"));
		putValue(NAME, "mSave");
		putValue(SHORT_DESCRIPTION, "Save project");
	}

	public void actionPerformed(ActionEvent arg0) {
		if (Frejm.getInstance().getNodeModel().getRoot() == null)
			return;
		if (Main.getFile() == null) Frejm.getInstance().getActionManager().getSaveAsProjectAction().actionPerformed(null);
		if (Main.getFile() == null) return;
		try {
			File file = Main.getFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			
			out.writeObject(Frejm.getInstance().getNodeModel().getRoot());
			
			out.close();
			fileOutputStream.close();
			Main.setFile(file);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
