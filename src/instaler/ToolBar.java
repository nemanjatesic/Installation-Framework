package instaler;

import javax.swing.JToolBar;

import view.Frejm;

public class ToolBar extends JToolBar{

	public ToolBar(Boolean bool) {
	
		if(Frejm.getInstance().getKorisnik().equals("Admin")) {
			add(Frejm.getInstance().getActionManager().getNewProjectAction());
			Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getOpenProjectAction());
			add(Frejm.getInstance().getActionManager().getAddTreeToNodeAction());
			if (bool) add(Frejm.getInstance().getActionManager().getDeleteTabAction());
			else add(Frejm.getInstance().getActionManager().getCloseProjectAction());
			if (bool) add(Frejm.getInstance().getActionManager().getSwitchProjectAction());
			add(Frejm.getInstance().getActionManager().getSaveProjectAction());
			add(Frejm.getInstance().getActionManager().getSaveAsProjectAction());
			if (!bool) add(Frejm.getInstance().getActionManager().getExportAction());
			if (!bool) add(Frejm.getInstance().getActionManager().getInstallAction());
			add(Frejm.getInstance().getActionManager().getUndoAction());
			Frejm.getInstance().getActionManager().getUndoAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getRedoAction());
			Frejm.getInstance().getActionManager().getRedoAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getExitProjectAction());
		}else if (Frejm.getInstance().getKorisnik().equals("User")) {
			add(Frejm.getInstance().getActionManager().getNewProjectAction());
			Frejm.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getOpenProjectAction());
			add(Frejm.getInstance().getActionManager().getAddTreeToNodeAction());
			Frejm.getInstance().getActionManager().getAddTreeToNodeAction().setEnabled(false);
			if (bool) {
				add(Frejm.getInstance().getActionManager().getDeleteTabAction());
				Frejm.getInstance().getActionManager().getDeleteTabAction().setEnabled(false);
			}
			else {
				add(Frejm.getInstance().getActionManager().getCloseProjectAction());
				Frejm.getInstance().getActionManager().getCloseProjectAction().setEnabled(false);
			}
			if (bool) add(Frejm.getInstance().getActionManager().getSwitchProjectAction());
			add(Frejm.getInstance().getActionManager().getSaveProjectAction());
			add(Frejm.getInstance().getActionManager().getSaveAsProjectAction());
			if (!bool) add(Frejm.getInstance().getActionManager().getExportAction());
			if (!bool) add(Frejm.getInstance().getActionManager().getInstallAction());
			add(Frejm.getInstance().getActionManager().getUndoAction());
			Frejm.getInstance().getActionManager().getUndoAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getRedoAction());
			Frejm.getInstance().getActionManager().getRedoAction().setEnabled(false);
			add(Frejm.getInstance().getActionManager().getExitProjectAction());
		}
		setFloatable(false);
	}
}
