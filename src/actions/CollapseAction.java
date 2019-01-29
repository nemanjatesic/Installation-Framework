package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import model.Node;
import view.Frejm;

public class CollapseAction extends AbstractNAction{
	private int x;
	private int y;
	
	public CollapseAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/collapse.png"));
		putValue(NAME, "mCollapse");
		putValue(SHORT_DESCRIPTION, "Collapse all");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		expandAll(Frejm.getInstance().getNodeTree(), Frejm.getInstance().getNodeTree().getPathForLocation(x, y));
	}
	
	private void expandAll(JTree tree, TreePath parent) {
	    Node node = (Node) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	      for (int i = 0 ; i < node.getChildCount() ; i++) {
	        Node n = (Node) node.getChildAt(i);
	        TreePath path = parent.pathByAddingChild(n);
	        expandAll(tree, path);
	      }
	    }
	    tree.collapsePath(parent);
	  }
	
	public void setCooridantes(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
