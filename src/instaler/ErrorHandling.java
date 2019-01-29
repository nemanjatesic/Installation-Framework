package instaler;

import javax.swing.JOptionPane;

import actions.AddTreeToNodeAction;
import actions.ClipboardCopy;
import actions.ClipboardPaste;
import actions.CloseProjectAction;
import actions.DeleteTabAction;
import actions.ExportAction;
import actions.NewProjectAction;

public class ErrorHandling {
	
	public static void handle(Object o, Exception e) {
		if (o instanceof AddTreeToNodeAction) {
			if (e instanceof NullPointerException) {
				JOptionPane.showMessageDialog(null, "You didn't chose any files!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof ClipboardCopy) {
			if (e instanceof NullPointerException) {
				JOptionPane.showMessageDialog(null, "You didn't select any nodes!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if (e instanceof ClassCastException) {
				JOptionPane.showMessageDialog(null, "You can only select parametars.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof ClipboardPaste) {
			if (e instanceof ClassCastException) {
				JOptionPane.showMessageDialog(null, "Please selecet Modul or Proizvod.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof CloseProjectAction) {
			if (e instanceof NullPointerException) {
				JOptionPane.showMessageDialog(null, "You didn't select any nodes!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof DeleteTabAction) {
			if (e instanceof IndexOutOfBoundsException) {
				JOptionPane.showMessageDialog(null, "There aren't any tabs in tabbed pane!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof NewProjectAction) {
			if (e instanceof NullPointerException) {
				JOptionPane.showMessageDialog(null, "You didn't select any nodes!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (o instanceof ExportAction) {
			if (e instanceof NullPointerException) {
				if (e.getMessage().equals("Putanja")) {
					JOptionPane.showMessageDialog(null, "Please select path for Proizvod!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Please select proizvod to export!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else if (e == null) {
				JOptionPane.showMessageDialog(null, "Proizovd exported successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
