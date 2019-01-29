package actions;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import app.Main;
import commands.AddCommand;
import instaler.ErrorHandling;
import model.Kompanija;
import model.Modul;
import model.Node;
import model.ParametarAutor;
import model.ParametarCustom;
import model.ParametarLogo;
import model.ParametarLook;
import model.ParametarNaziv;
import model.ParametarShortcut;
import model.ParametarStart;
import model.ParametarUslovi;
import model.Proizvod;
import view.Frejm;

public class NewProjectAction extends AbstractNAction {

	public NewProjectAction() {	
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/new.png"));
		putValue(NAME, "mNew");
		putValue(SHORT_DESCRIPTION, "New project");
	}

	public void actionPerformed(ActionEvent arg0) {

		Node tmp = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
		
		if (Frejm.getInstance().getNodeModel().getRoot() == null) {
			Main.setSave(true);
			tmp = new Kompanija("Kompanija", null);
			Frejm.getInstance().getNodeModel().setRoot(tmp);
			ArrayList<Node> lista = new ArrayList<>();
			lista.add(tmp);
			Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
		}else if (tmp != null){
			if (tmp instanceof Kompanija) {
				Main.setSave(true);
				Proizvod proizvod = new Proizvod("Proizvod - " + tmp.getChildCount(),tmp);
				((Kompanija) tmp).addNode(proizvod);
				proizvod.addListener(Frejm.getInstance().getPanCenterDown());
				ArrayList<Node> lista = new ArrayList<>();
				lista.add(proizvod);
				Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
			}else if (tmp instanceof Proizvod) {
				modulIliParametar();
			}else if (tmp instanceof Modul) {
				vrstaParametra(tmp);
			}
		}else {
			ErrorHandling.handle(this, new NullPointerException());
		}
		
		
		SwingUtilities.updateComponentTreeUI(Frejm.getInstance().getNodeTree());
	}
	
	private void modulIliParametar() {
		JButton btnOk = new JButton("Choose");
		String[] vrste = {"Modul","Parametar"};
        JComboBox<String> cb = new JComboBox<>(vrste);
        JLabel lblIzaberite = new JLabel("    Choose component: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(270, 110);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
		panel.add(lblIzaberite);
		panel.add(cb);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Node node = (Node)Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
				if (cb.getSelectedItem().equals("Modul")) {
					Node n = new Modul("Modul - " + node.getChildCount(),node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Parametar")) {
					frame.setVisible(false);
					vrstaParametra(node);
				}
				frame.dispose();
			}
		});
		
		frame.add(panel,BorderLayout.NORTH);
		frame.add(panel1,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private void vrstaParametra(Node node) {
		JButton btnOk = new JButton("Choose");
		String[] vrste = {"Autor","Logo","Look and feel","Start After","Naziv","Shortcut","Uslovi koriscenja","CUSTOM"};
        JComboBox<String> cb = new JComboBox<>(vrste);
        JLabel lblIzaberite = new JLabel("    Choose parametar type: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(320, 110);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
		panel.add(lblIzaberite);
		panel.add(cb);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb.getSelectedItem().equals("Autor")) {
					Node n = new ParametarAutor("Parametar-Autor",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Logo")) {
					Node n = new ParametarLogo("Parametar-Logo",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Look and feel")) {
					Node n = new ParametarLook("Parametar-Look and feel",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Naziv")) {
					Node n = new ParametarNaziv("Parametar-Naziv",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Shortcut")) {
					Node n = new ParametarShortcut("Parametar-Shortcut",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("Uslovi koriscenja")) {
					Node n = new ParametarUslovi("Parametar-Uslovi koriscenja",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}else if (cb.getSelectedItem().equals("CUSTOM")) {
					frame.setVisible(false);
					vrstaCustomParametra(node);
				}else if (cb.getSelectedItem().equals("Start After")) {
					Node n = new ParametarStart("Parametar-Start After",node);
					n.addListener(Frejm.getInstance().getPanCenterDown());
					node.addNode(n);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(n);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}
				frame.dispose();
			}
		});
		
		frame.add(panel,BorderLayout.NORTH);
		frame.add(panel1,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private void vrstaCustomParametra(Node node) {
		JButton btnOk = new JButton("Choose");
		String[] vrste = {"TextField","CheckBox","RadioButtons"};
        JComboBox<String> cb = new JComboBox<>(vrste);
        JLabel lblIzaberite = new JLabel("    Custom parametar type: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(320, 110);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
		panel.add(lblIzaberite);
		panel.add(cb);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Node n = new ParametarCustom("Parametar-CUSTOM",node);
				nazivCustoma(n,(String)cb.getSelectedItem());
				frame.dispose();
			}
		});
		frame.add(panel,BorderLayout.NORTH);
		frame.add(panel1,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private void nazivCustoma(Node node,String s) {
		JButton btnOk = new JButton("Choose");
		JTextArea taIme = new JTextArea();
        JLabel lblIzaberite = new JLabel("    Choose the message: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(320, 110);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
		panel.add(lblIzaberite);
		panel.add(taIme);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				((ParametarCustom)node).setIme(taIme.getText());
				((ParametarCustom)node).setVrsta(s);
				if (s.equals("RadioButtons")) {
					nazivButtona(node);
				}else if (s.equals("CheckBox")) {
					nazivCheckBoxa(node);
				}else {
					node.addListener(Frejm.getInstance().getPanCenterDown());
					((Node)(node.getParent())).addNode(node);
					Main.setSave(true);
					ArrayList<Node> lista = new ArrayList<>();
					lista.add(node);
					Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				}
				
				frame.dispose();
			}
		});
		frame.add(panel,BorderLayout.NORTH);
		frame.add(panel1,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	private void nazivButtona(Node node) {
		JButton btnOk = new JButton("Choose");
		JTextArea taIme1 = new JTextArea();
		JTextArea taIme2 = new JTextArea();
        JLabel lblIzaberite1 = new JLabel("    First button message: ");
        JLabel lblIzaberite2 = new JLabel("    Second button message: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(320, 135);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		
		JPanel panel1 = new JPanel(new GridLayout(0, 2, 5, 10));
		panel1.add(lblIzaberite1);
		panel1.add(taIme1);
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel2 = new JPanel(new GridLayout(0, 2, 5, 10));
		panel2.add(lblIzaberite2);
		panel2.add(taIme2);
		panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				((ParametarCustom)node).setButton1(taIme1.getText());
				((ParametarCustom)node).setButton2(taIme2.getText());
				node.addListener(Frejm.getInstance().getPanCenterDown());
				((Node)(node.getParent())).addNode(node);
				Main.setSave(true);
				ArrayList<Node> lista = new ArrayList<>();
				lista.add(node);
				Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				frame.dispose();
			}
		});
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel2,BorderLayout.CENTER);
		frame.add(panel3,BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	private void nazivCheckBoxa(Node node) {
		JButton btnOk = new JButton("Choose");
		JTextArea taIme1 = new JTextArea();
        JLabel lblIzaberite1 = new JLabel("    CheckBox message: ");
		JDialog frame = new JDialog();
		frame.setLocationRelativeTo(Frejm.getInstance());
		frame.setSize(320, 110);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select");
		
		JPanel panel1 = new JPanel(new GridLayout(0, 2, 5, 10));
		panel1.add(lblIzaberite1);
		panel1.add(taIme1);
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				((ParametarCustom)node).setCheckBoxIme(taIme1.getText());
				node.addListener(Frejm.getInstance().getPanCenterDown());
				((Node)(node.getParent())).addNode(node);
				Main.setSave(true);
				ArrayList<Node> lista = new ArrayList<>();
				lista.add(node);
				Frejm.getInstance().getCommandManager().addCommand(new AddCommand(lista));
				frame.dispose();
			}
		});
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel3,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
}
