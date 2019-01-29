package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import controller.MouseControllerTree;
import events.IListener;
import instaler.ToolBar;
import model.Node;
import model.ScrollPane;

public class PanelCenterUp extends JPanel implements IListener{

	private JTabbedPane tabbedPane;
	private ArrayList<IListener> listeners = new ArrayList<>();
	
	public PanelCenterUp() {
		tabbedPane = new JTabbedPane();
		tabbedPane.addMouseListener(new MouseControllerTree());
		ToolBar toolbar = new ToolBar(true);
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(200, 270));
		
		add(toolbar,BorderLayout.NORTH);
		add(tabbedPane,BorderLayout.CENTER);
	}
	
	public void dodajTab(Node node) {
		/*JTextArea ta = new JTextArea();
		ta.setText(node.getSastav());
		ta.addKeyListener(new MyList());
		ScrollPane js = new ScrollPane(ta,node);*/
		
		Boolean bool = true;
		int index = 0;
		
		for (int i = 0 ; i < tabbedPane.getTabCount() ; i++) {
			if (node == ((ScrollPane)tabbedPane.getComponentAt(i)).getNode()) {
				bool = false;
				index = i;
				break;
			}
		}
		
		if (bool) {
			ScrollPane js = new ScrollPane(node);
			tabbedPane.addTab(node.getName(), js);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		}else {
			tabbedPane.setSelectedIndex(index);
		}
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	@Override
	public void update(Object o) {
		if (o instanceof Node) {
			Node node = (Node)o;
			int index = 0;
			for (int i = 0 ; i < tabbedPane.getTabCount() ; i++) {
				if (node == ((ScrollPane)tabbedPane.getComponentAt(i)).getNode()) {
					index = i;
					break;
				}
			}
			if (tabbedPane.getTabCount() != 0) {
				tabbedPane.setSelectedIndex(index);
				tabbedPane.setTitleAt(index, node.getName());
			}
		}else {
			ScrollPane sp = (ScrollPane)o;
			if (sp.getTextArea() != null) {
				sp.getTextArea().setText(sp.getNode().getSastav());
				Node node = sp.getNode();
				int index = 0;
				for (int i = 0 ; i < tabbedPane.getTabCount() ; i++) {
					if (node == ((ScrollPane)tabbedPane.getComponentAt(i)).getNode()) {
						index = i;
						break;
					}
				}
				
				tabbedPane.setSelectedIndex(index);
				Frejm.getInstance().getPanCenterDown().update(sp);
			}
		}
	}
	
	public void obrisi(Node node) {
		int index;
		if (node == null) return;
		Queue<Node> que = new LinkedList<>();
		que.add(node);
		
		while(!que.isEmpty()) {
			index = -1;
			Node n = que.poll();
			for (int i = 0 ; i < n.getChildCount() ; i++) {
				que.add((Node)n.getChildAt(i));
			}
			
			for (int i = 0 ; i < tabbedPane.getTabCount() ; i++) {
				if (n == ((ScrollPane)tabbedPane.getComponentAt(i)).getNode()) {
					index = i;
					break;
				}
			}
			if (index != -1) tabbedPane.remove(index);
		}
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		Frejm.getInstance().getPanCenterDown().update(tabbedPane.getSelectedComponent());
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}
	
}
