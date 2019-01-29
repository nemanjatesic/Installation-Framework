package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelLeft extends JPanel{
	
	public PanelLeft() {
		setLayout(new BorderLayout(5,5));
		setPreferredSize(new Dimension(230,100));
		setBackground(Color.LIGHT_GRAY);

	    JScrollPane scrollPane = new JScrollPane(Frejm.getInstance().getNodeTree());

	    add(scrollPane);

	}
}
