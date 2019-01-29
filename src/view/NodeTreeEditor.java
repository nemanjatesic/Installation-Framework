package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import app.Main;
import commands.AddCommand;
import commands.RenameCommand;
import model.Node;

public class NodeTreeEditor extends DefaultTreeCellEditor implements ActionListener {

	private Object stavka = null;
	private JTextField edit = null;

	public NodeTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
		stavka = arg1;
		edit = new JTextField(arg1.toString());
		edit.addActionListener(this);
		return edit;
	}

	public boolean isCellEditable(EventObject arg0) {
		if (arg0 instanceof MouseEvent)
			if (((MouseEvent) arg0).getClickCount() == 3) {
				return true;
			}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (!((Node)stavka).getName().equals(e.getActionCommand())) {
			Frejm.getInstance().getCommandManager().addCommand(new RenameCommand((Node)stavka,((Node)stavka).getName()));
			((Node)stavka).setName(e.getActionCommand());
			Frejm.getInstance().getPanCenterUp().update((Node)stavka);
			Main.setSave(true);
		}
	}
}
