package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import events.IListener;
import model.Node;
import model.ScrollPane;

public class PanelCenterDown extends JPanel implements IListener{
	
	private JLabel label;
	
	public PanelCenterDown() {
		setLayout(new BorderLayout(5,5));
		setBackground(Color.LIGHT_GRAY);
		
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(new JScrollPane(label),BorderLayout.CENTER);
	}

	@Override
	public void update(Object o) {
		if (o == null) {
			label.setText("");
			return;
		}
			
		if (o instanceof Node) {
			Node node = (Node)o;
			String htmlB = "<html><h3>";
			String htmlE = "</html><h3/>";
			String br = "<br/>";
			String string = htmlB + "Naziv cvora :  " + node.getName() + br;
			string += "Sadrzaj cvora : " + node.getSastav() + br;
			if (((Node)node.getParent()) != null) string += "Naziv roditlja cvora :  " + ((Node)node.getParent()).getName() + br;
			string += "Broj dece cvora :  " + node.getChildCount() + br;// + "\nCode : " + node.getCode() + htmlE;
			string += "Broj listova medju potomcima : " + brojLisca(node) + htmlE;
			string = string.replace("\n", br);
			label.setText(string);
		}
		if (o instanceof ScrollPane) {
			Node node = ((ScrollPane)o).getNode();
			String htmlB = "<html><h3>";
			String htmlE = "</html><h3/>";
			String br = "<br/>";
			String string = htmlB + "Naziv cvora :  " + node.getName() + br;
			string += "Sadrzaj cvora : " + ((ScrollPane)o).getNode().getSastav() + br;
			if (((Node)node.getParent()) != null) string += "Naziv roditlja cvora :  " + ((Node)node.getParent()).getName() + br;
			string += "Broj dece cvora :  " + node.getChildCount() + br;// + "\nCode : " + node.getCode() + htmlE;
			string += "Broj listova medju potomcima : " + brojLisca(node) + htmlE;
			string = string.replace("\n", br);
			label.setText(string);
		}
	}
	
	private int brojLisca(Node node) {
		Queue<Node> que = new LinkedList<>();
		que.add(node);
		int broj = 0;
		if(node.isLeaf()) broj--;
		
		while(!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0 ; i < n.getChildCount() ; i++) {
				que.add((Node)n.getChildAt(i));
			}
			if (n.isLeaf()) broj++;
		}
		return broj;
	}
	
}
