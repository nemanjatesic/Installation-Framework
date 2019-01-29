package view;

import java.awt.Component;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Kompanija;
import model.Modul;
import model.Parametar;
import model.Proizvod;

public class NodeTreeCellRendered extends DefaultTreeCellRenderer {

	public NodeTreeCellRendered() {

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof Kompanija) {
			ImageIcon img = null;
			File tmp = new File("src/resursi/workspace12.png");

			if (tmp.exists()) {
				img = new ImageIcon("src/resursi/workspace12.png");
				setIcon(img);
			} else {
				System.err.println("Resource not found: " + "src/resursi/workspace12.png");
			}
		}else if (value instanceof Proizvod){
			ImageIcon img = null;
			File tmp = new File("src/resursi/product12.png");

			if (tmp.exists()) {
				img = new ImageIcon("src/resursi/product12.png");
				setIcon(img);
			} else {
				System.err.println("Resource not found: " + "src/resursi/product12.png");
			}
		}else if (value instanceof Modul){
			ImageIcon img = null;
			File tmp = new File("src/resursi/module12.png");

			if (tmp.exists()) {
				img = new ImageIcon("src/resursi/module12.png");
				setIcon(img);
			} else {
				System.err.println("Resource not found: " + "src/resursi/module12.png");
			}
		}else if (value instanceof Parametar){
			ImageIcon img = null;
			File tmp = new File("src/resursi/dot.png");

			if (tmp.exists()) {
				img = new ImageIcon("src/resursi/dot.png");
				setIcon(img);
			} else {
				System.err.println("Resource not found: " + "src/resursi/dot.png");
			}
		}
		return this;
	}
}
