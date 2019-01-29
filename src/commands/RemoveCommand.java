package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.Node;
import view.Frejm;

public class RemoveCommand implements AbstractCommand{

	private ArrayList<Node> nodes = new ArrayList<>();
	
	public RemoveCommand(ArrayList<Node> selected) {
		nodes = selected;
	}
	
	@Override
	public void undoCommand() {
		/*for(int i = 0 ; i < nodes.size() ; i++){
			if (Frejm.getInstance().getNodeModel().getRoot() == null) {
				Frejm.getInstance().getNodeModel().setRoot(nodes.get((i))); 
				break;
			}
			String ime = nodes.get(i).getName();
			if (((Node)nodes.get(i).getParent()) != null)
				((Node)nodes.get(i).getParent()).addNode(nodes.get(i));
			else Frejm.getInstance().getNodeModel().setRoot(null);
			nodes.get(i).setName(ime);
		}*/
		Collections.sort(nodes);
		for(int i = 0 ; i < nodes.size() ; i++){
			if (Frejm.getInstance().getNodeModel().getRoot() == null) {
				Frejm.getInstance().getNodeModel().setRoot(nodes.get((i))); 
				break;
			}
			String ime = nodes.get(i).getName();
			if (((Node)nodes.get(i).getParent()) != null) {
				((Node)nodes.get(i).getParent()).getDeca().add(nodes.get(i).getBroj(), nodes.get(i));
			}
				
			else Frejm.getInstance().getNodeModel().setRoot(null);
			nodes.get(i).setName(ime);
		}
	}

	@Override
	public void doCommand() {
		for (Node n : nodes) {
			if ((Node)n.getParent() == null){
				Frejm.getInstance().getNodeModel().setRoot(null);
				break;
			}
			((Node)n.getParent()).remove(n);
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

}
