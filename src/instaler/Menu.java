package instaler;

import javax.swing.JMenu;

import view.Frejm;

public class Menu extends JMenu{
	
	public Menu(String s) {
		super(s);
		if (s.equals("File") || s.equals("View") || s.equals("Pomoc") || s.equals("Parametrizacija")) {
			JMenu jmnExport = new JMenu("mExport");
			JMenu jmnImport = new JMenu("mImport");
			
			jmnExport.add(Frejm.getInstance().getActionManager().getExportPDFAction());
			jmnExport.add(Frejm.getInstance().getActionManager().getExportExcelAction());
			jmnExport.add(Frejm.getInstance().getActionManager().getExportWordAction());
			jmnImport.add(Frejm.getInstance().getActionManager().getImportPDFAction());
			jmnImport.add(Frejm.getInstance().getActionManager().getImportExcelAction());
			jmnImport.add(Frejm.getInstance().getActionManager().getImportWordAction());
			
			add(Frejm.getInstance().getActionManager().getNewProjectAction());
			addSeparator();
			add(Frejm.getInstance().getActionManager().getOpenProjectAction());
			add(Frejm.getInstance().getActionManager().getCloseProjectAction());
			add(Frejm.getInstance().getActionManager().getSwitchProjectAction());
			addSeparator();
			add(Frejm.getInstance().getActionManager().getSaveProjectAction());
			add(Frejm.getInstance().getActionManager().getSaveAsProjectAction());
			addSeparator();
			add(jmnExport);
			add(jmnImport);
			addSeparator();
			add(Frejm.getInstance().getActionManager().getExitProjectAction());
		}else if (s.equals("Edit")) {
			add(Frejm.getInstance().getActionManager().getClipboardCopy());
			addSeparator();
			add(Frejm.getInstance().getActionManager().getClipboardPaste());
			Frejm.getInstance().getActionManager().getClipboardPaste().setEnabled(false);
			addSeparator();
			add(Frejm.getInstance().getActionManager().getClipboardCut());
		}else if (s.equals("About")){
			add(Frejm.getInstance().getActionManager().getInfoAction());
		}
	}
}
