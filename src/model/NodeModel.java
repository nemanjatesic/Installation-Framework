package model;

import javax.swing.tree.DefaultTreeModel;

public class NodeModel extends DefaultTreeModel {

	public NodeModel() {
		super(new Kompanija("Kompanija",null));
	}

	/* Nije potrebno koliko ja znam
	 * 
	 * public void addNode(Node node,String t){
	 * 		((Node)getRoot()).addNode(node);
	 *  	SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	 * }
	 */
}
