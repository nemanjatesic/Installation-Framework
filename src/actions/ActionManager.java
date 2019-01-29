package actions;

import actions.AddTreeToNodeAction;
import actions.ClipboardCopy;
import actions.ClipboardCut;
import actions.ClipboardPaste;
import actions.CloseProjectAction;
import actions.CollapseAction;
import actions.DeleteTabAction;
import actions.ExitProjectAction;
import actions.ExpandAction;
import actions.ExportAction;
import actions.ExportExcelAction;
import actions.ExportPDFAction;
import actions.ExportWordAction;
import actions.ImportExcelAction;
import actions.ImportPDFAction;
import actions.ImportWordAction;
import actions.InfoAction;
import actions.InstallAction;
import actions.NewProjectAction;
import actions.OpenProjectAction;
import actions.RedoAction;
import actions.SaveAsProjectAction;
import actions.SaveProjectAction;
import actions.SwitchProjectAction;
import actions.UndoAction;

public class ActionManager {
	private CloseProjectAction closeProjectAction;
	private ExitProjectAction exitProjectAction;
	private NewProjectAction newProjectAction;
	private OpenProjectAction openProjectAction;
	private SaveAsProjectAction saveAsProjectAction;
	private SaveProjectAction saveProjectAction;
	private SwitchProjectAction switchProjectAction;
	private ImportExcelAction importExcelAction;
	private ImportPDFAction importPDFAction;
	private ImportWordAction importWordAction;
	private ExportExcelAction exportExcelAction;
	private ExportPDFAction exportPDFAction;
	private ExportWordAction exportWordAction;
	private AddTreeToNodeAction addTreeToNodeAction;
	private InfoAction infoAction;
	private DeleteTabAction deleteTabAction;
	private ExpandAction expandAction;
	private CollapseAction collapseAction;
	private ClipboardCopy clipboardCopy;
	private ClipboardCut clipboardCut;
	private ClipboardPaste clipboardPaste;
	private UndoAction undoAction;
	private RedoAction redoAction;
	private InstallAction installAction;
	private ExportAction exportAction;
	
	public ActionManager() {
		super();
		closeProjectAction = new CloseProjectAction();
		exitProjectAction = new ExitProjectAction();
		newProjectAction = new NewProjectAction();
		openProjectAction = new OpenProjectAction();
		saveAsProjectAction = new SaveAsProjectAction();
		saveProjectAction = new SaveProjectAction();
		switchProjectAction = new SwitchProjectAction();
		importExcelAction = new ImportExcelAction();
		importPDFAction = new ImportPDFAction();
		importWordAction = new ImportWordAction();
		exportExcelAction = new ExportExcelAction();
		exportPDFAction = new ExportPDFAction();
		exportWordAction = new ExportWordAction();
		addTreeToNodeAction = new AddTreeToNodeAction();
		infoAction = new InfoAction();
		deleteTabAction = new DeleteTabAction();
		expandAction = new ExpandAction();
		collapseAction = new CollapseAction();
		clipboardCopy = new ClipboardCopy();
		clipboardCut = new ClipboardCut();
		clipboardPaste = new ClipboardPaste();
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		installAction = new InstallAction();
		exportAction = new ExportAction();
	}
	
	public ExportAction getExportAction() {
		return exportAction;
	}
	
	public InstallAction getInstallAction() {
		return installAction;
	}
	
	public CloseProjectAction getCloseProjectAction() {
		return closeProjectAction;
	}

	public ExitProjectAction getExitProjectAction() {
		return exitProjectAction;
	}

	public NewProjectAction getNewProjectAction() {
		return newProjectAction;
	}

	public OpenProjectAction getOpenProjectAction() {
		return openProjectAction;
	}

	public SaveAsProjectAction getSaveAsProjectAction() {
		return saveAsProjectAction;
	}

	public SaveProjectAction getSaveProjectAction() {
		return saveProjectAction;
	}

	public SwitchProjectAction getSwitchProjectAction() {
		return switchProjectAction;
	}

	public ImportExcelAction getImportExcelAction() {
		return importExcelAction;
	}

	public ImportPDFAction getImportPDFAction() {
		return importPDFAction;
	}

	public ImportWordAction getImportWordAction() {
		return importWordAction;
	}

	public ExportExcelAction getExportExcelAction() {
		return exportExcelAction;
	}

	public ExportPDFAction getExportPDFAction() {
		return exportPDFAction;
	}

	public ExportWordAction getExportWordAction() {
		return exportWordAction;
	}
	
	public AddTreeToNodeAction getAddTreeToNodeAction() {
		return addTreeToNodeAction;
	}
	
	public InfoAction getInfoAction() {
		return infoAction;
	}
	
	public DeleteTabAction getDeleteTabAction() {
		return deleteTabAction;
	}
	
	public ExpandAction getExpandAction() {
		return expandAction;
	}
	
	public CollapseAction getCollapseAction() {
		return collapseAction;
	}
	
	public ClipboardCopy getClipboardCopy() {
		return clipboardCopy;
	}
	
	public ClipboardCut getClipboardCut() {
		return clipboardCut;
	}
	
	public ClipboardPaste getClipboardPaste() {
		return clipboardPaste;
	}
	
	public UndoAction getUndoAction() {
		return undoAction;
	}
	
	public RedoAction getRedoAction() {
		return redoAction;
	}
}
