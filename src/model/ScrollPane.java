package model;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.MyList;
import model.Kompanija;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import view.ProizvodView;
import view.ParametarView;

public class ScrollPane extends JScrollPane{
	private Node node;
	private JTextArea textArea;
	private String text;
	private ParametarView view;
	private ProizvodView proizvodView;
	
	/*public ScrollPane(JTextArea textArea, Node node) {
		super();
		this.textArea = textArea;
		this.node = node;
		this.text = node.getSastav();
		setViewportView(textArea);
	}*/
	
	public ScrollPane(Node node) {
		if (node instanceof Modul || node instanceof Kompanija) {
			textArea = new JTextArea();
			textArea.setText(node.getSastav());
			textArea.addKeyListener(new MyList());
			this.node = node;
			this.text = node.getSastav();
			setViewportView(textArea);
		}else if (node instanceof Parametar){
			this.node = node;
			this.text = node.getSastav();
			Parametar p = (Parametar)node;
			view = new ParametarView(p);
			setViewportView(view);
		}else if (node instanceof Proizvod) {
			this.node = node;
			this.text = node.getSastav();
			Proizvod p = (Proizvod)node;
			proizvodView = new ProizvodView(p);
			setViewportView(proizvodView);
		}
	}
	
	public ProizvodView getProizvodView() {
		return proizvodView;
	}
	
	public Node getNode() {
		return node;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public ParametarView getView() {
		return view;
	}
}