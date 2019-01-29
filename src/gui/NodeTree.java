package gui;


import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import controller.MouseControllerTree;
import controller.NodeTreeController;
import view.NodeTreeCellRendered;
import view.NodeTreeEditor;

public class NodeTree extends JTree {

	public NodeTree() {
		addMouseListener(new MouseControllerTree()); 
		addTreeSelectionListener(new NodeTreeController());
	    setCellEditor(new NodeTreeEditor(this,new DefaultTreeCellRenderer()));
	    setCellRenderer(new NodeTreeCellRendered());
	    setEditable(true);
	}

	/*
	public void addNode(Node node, String t){
		((NodeModel)getModel()).addNode(node,"cao");
		SwingUtilities.updateComponentTreeUI(this);
	}
	*/
	/*
	public Boolean daLiSuIsti(Node node) {

		Queue<Node> que = new LinkedList<>();

		que.add((Node) Frejm.getInstance().getNodeModel().getRoot());

		while (!que.isEmpty()) {
			Node n = que.poll();
			if (n == node) return true;
			for (int i = 0; i < n.getChildCount(); i++) {
				que.add((Node) n.getChildAt(i));
			}
		}
		return false;
	}
	*/
}
