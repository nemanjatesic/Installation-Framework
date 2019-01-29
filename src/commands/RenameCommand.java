package commands;

import model.Node;
import model.ScrollPane;
import view.Frejm;

public class RenameCommand implements AbstractCommand{

	private Node node;
	private String ime;
	
	public RenameCommand(Node n, String name) {
		node = n;
		ime = name;
	}
	
	@Override
	public void doCommand() {
		String s = node.getName();
		node.setName(ime);
		ime = s;
		Node n = ((ScrollPane)Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedComponent()).getNode();
		Frejm.getInstance().getPanCenterUp().update(node);
		Frejm.getInstance().getPanCenterUp().update(n);
		Frejm.getInstance().getPanCenterDown().update(n);
	}

	@Override
	public void undoCommand() {
		String s = node.getName();
		node.setName(ime);
		ime = s;
		Node n = ((ScrollPane)Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedComponent()).getNode();
		Frejm.getInstance().getPanCenterUp().update(node);
		Frejm.getInstance().getPanCenterUp().update(n);
		Frejm.getInstance().getPanCenterDown().update(n);
	}

}
