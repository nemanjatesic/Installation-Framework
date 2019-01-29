package commands;

import java.util.ArrayList;

import model.Kompanija;
import model.Node;
import view.Frejm;

public class AddCommand implements AbstractCommand{

	private ArrayList<Node> nodes = new ArrayList<>();
	
	public AddCommand(ArrayList<Node> selected) {
		nodes = selected;
	}
	
	@Override
	public void undoCommand() {
		for (Node n : nodes) {
			if (n instanceof Kompanija) {
				Frejm.getInstance().getNodeModel().setRoot(null);
			}else {
				((Node)n.getParent()).remove(n);
				Frejm.getInstance().getPanCenterUp().obrisi(n);
			}
		}
	}

	@Override
	public void doCommand() {
		for(int i = 0 ; i < nodes.size() ; i++){
			if (nodes.get(i).getParent() == null) {
				Frejm.getInstance().getNodeModel().setRoot(nodes.get(i));
			}else {
				String ime = nodes.get(i).getName();
				((Node)nodes.get(i).getParent()).addNode(nodes.get(i));
				nodes.get(i).setName(ime);
			}
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
}