package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.Main;
import commands.AddCommand;
import controller.MyList;
import model.Node;
import model.Parametar;
import model.ParametarAutor;
import model.ParametarCustom;
import model.ParametarLogo;
import model.ParametarLook;
import model.ParametarNaziv;
import model.ParametarShortcut;
import model.ParametarStart;
import model.ParametarUslovi;
import model.ScrollPane;

public class ParametarView extends JPanel{
	
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfUslovi;
	private JTextField tfLogo;
	private JTextField tfNaziv;
	
	public ParametarView(Parametar p) {
		
		if (p instanceof ParametarAutor) {
			parametarAutor(p);
		}else if (p instanceof ParametarCustom) {
			parametarCustom(p);
		}else if (p instanceof ParametarLogo) {
			parametarLogo(p);
		}else if (p instanceof ParametarLook) {
			parametarLook(p);
		}else if (p instanceof ParametarNaziv) {
			parametarNaziv(p);
		}else if (p instanceof ParametarShortcut) {
			parametarShortcut(p);
		}else if (p instanceof ParametarUslovi) {
			parametarUslovi(p);
		}else if (p instanceof ParametarStart) {
			parametarStart(p);
		}
		
	}
	
	private void parametarLook(Parametar p) {
		UIManager.LookAndFeelInfo[] look = UIManager.getInstalledLookAndFeels();
		Vector<String> c = new Vector<>();
		
		for (int i = 0 ; i < look.length ; i++) {
			c.add(look[i].getName());
		}
        
		JComboBox<String> cb = new JComboBox<>(c);
        JLabel lblSelect = new JLabel("    Select look and feel: ");
        cb.setEditable(false);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(100, 0, 0, 0));
        
        panel.add(lblSelect);
        panel.add(cb);
        
        add(panel);
        
        if(((ParametarLook)p).getLookAndFeel() == null || ((ParametarLook)p).getLookAndFeel().equals("")) {
        	cb.setSelectedItem(c.get(0));
        }else {
        	cb.setSelectedItem(maloIme(((ParametarLook)p).getLookAndFeel()));
        }
        
        for (int i = 0 ; i < UIManager.getInstalledLookAndFeels().length ; i++) {
			if (((String)cb.getSelectedItem()).equals(UIManager.getInstalledLookAndFeels()[i].getName())) {
				((ParametarLook)p).setLookAndFeel(UIManager.getInstalledLookAndFeels()[i].getClassName());
			}
		}
         
        
        cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ParametarLook par = (ParametarLook)((ScrollPane)(Frejm.getInstance().getPanCenterUp().getTabbedPane().getSelectedComponent())).getNode();
				par.setLookAndFeel(punoIme((String)cb.getSelectedItem()));
				try {
					UIManager.setLookAndFeel(punoIme((String)cb.getSelectedItem()));
					SwingUtilities.updateComponentTreeUI(Frejm.getInstance());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
        
	}
	
	private void parametarNaziv(Parametar p) {
		JLabel lblNaziv = new JLabel("    Naziv projekta :  ");
        tfNaziv = new JTextField();
        
        tfNaziv.setText(((ParametarNaziv) p).getNaziv());
        
        tfNaziv.addKeyListener(new MyList());
		
        tfNaziv.setColumns(20);
        
        GridLayout gl = new GridLayout(0,2);
        JPanel panel = new JPanel();
        panel.setLayout(gl);
        
        gl.setHgap(10);
        gl.setVgap(25);
        
        panel.add(lblNaziv);
        panel.add(tfNaziv);

        panel.setBorder(new EmptyBorder(50, 5, 5, 5));
        
        add(panel);
	}
	
	private void parametarShortcut(Parametar p) {
		JCheckBox cbCheck = new JCheckBox("Do you want to have shortcut on desktop? ");
		JPanel panel = new JPanel();
		
		cbCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((ParametarShortcut)p).setShortcut(cbCheck.isSelected());
			}
		});
		
		panel.add(cbCheck);
		
		panel.setBorder(new EmptyBorder(50, 5, 5, 5));
        
        add(panel);
	}
	
	private void parametarStart(Parametar p) {
		JCheckBox cbCheck = new JCheckBox("Do you want to start program after instalation ? ");
		JPanel panel = new JPanel();
		
		cbCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((ParametarStart)p).setStartAfter(cbCheck.isSelected());
			}
		});
		
		panel.add(cbCheck);
		
		panel.setBorder(new EmptyBorder(50, 5, 5, 5));
        
        add(panel);
	}
	
	private void parametarCustom(Parametar p) {
		ParametarCustom par = (ParametarCustom)p;
		JLabel lblNaziv = new JLabel(par.getIme());
		JPanel panel = new JPanel();
		if (par.getVrsta().equals("RadioButtons")) {
			ButtonGroup group = new ButtonGroup();
			JRadioButton btn1 = new JRadioButton(par.getButton1());
			JRadioButton btn2  = new JRadioButton(par.getButton2());
			
			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					par.setSelected(1);
				}
			});
			
			btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					par.setSelected(2);
				}
			});
			if (par.getSelected() == 1) btn1.setSelected(true);
			if (par.getSelected() == 2) btn2.setSelected(true);
			
			group.add(btn1);
			group.add(btn2);
			panel.setLayout(new FlowLayout());
			panel.add(lblNaziv);
			panel.add(btn1);
			panel.add(btn2);
			panel.setBorder(new EmptyBorder(70, 5, 5, 5));
			add(panel);
		}else if (par.getVrsta().equals("CheckBox")) {
			JCheckBox box = new JCheckBox(par.getCheckBoxIme());
			
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (par.isSelectedBox()) par.setSelectedBox(false);
					else par.setSelectedBox(true);
				}
			});
			
			if (par.isSelectedBox()) box.setSelected(true);
			else box.setSelected(false);
			
			panel.setLayout(new FlowLayout());
			panel.add(lblNaziv);
			panel.add(box);
			panel.setBorder(new EmptyBorder(70, 5, 5, 5));
			add(panel);
		}else if (par.getVrsta().equals("TextField")) {
			JTextArea text = new JTextArea();
			text.setColumns(30);
			text.setText(par.getTextFieldText());
			
			text.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					
				}
				@Override
				public void keyReleased(KeyEvent e) {
					par.setTextFieldText(text.getText());
				}
				@Override
				public void keyPressed(KeyEvent e) {
					
				}
			});
			
			panel.setLayout(new FlowLayout());
			panel.add(lblNaziv);
			panel.add(text);
			panel.setBorder(new EmptyBorder(70, 5, 5, 5));
			add(panel);
		}
	}
	
	private void parametarLogo(Parametar p) {
		setBorder(new EmptyBorder(0, 70, 5, 70));
		setLayout(new BorderLayout());
		JLabel lblIme = new JLabel("    Izabrana slika :  ");
        tfLogo = new JTextField();
        JButton btnChoose = new JButton("Choose");
        JButton btnPreview = new JButton("Preview");
        
        tfLogo.setText(((ParametarLogo)p).getLogo());
        
        tfLogo.addKeyListener(new MyList());
		
        tfLogo.setColumns(32);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        panel.add(lblIme);
        panel.add(tfLogo);
        
        btnChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Slike","png","jpg","gif","jpeg");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(Frejm.getInstance()) != 0) return;
				File file = fc.getSelectedFile();
				if (file == null) return;
				String s = file.getPath();
				tfLogo.setText(s);
				((ParametarLogo)p).setLogo(s);
			}
		});
        
        btnPreview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon img = null;
				File tmp = new File(tfLogo.getText());
				
				if (tmp.exists()) {
					if (getExtension(tfLogo.getText()).equals("png") || getExtension(tfLogo.getText()).equals("jpg")
							|| getExtension(tfLogo.getText()).equals("gif") || getExtension(tfLogo.getText()).equals("jpeg")) {
						img = new ImageIcon(tfLogo.getText());
						JFrame frame = new JFrame();
						JLabel imgLabel = new JLabel(img);
						frame.setTitle("Preview");
						frame.add(new JScrollPane(imgLabel));
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				        frame.pack();
				        frame.setSize(300, 300);
				        frame.setLocationRelativeTo(Frejm.getInstance());
				        frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(Frejm.getInstance(), "File isn't .png format.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(Frejm.getInstance(), "File doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        
        JPanel pnlBtn = new JPanel();
        pnlBtn.add(btnChoose);
        pnlBtn.add(btnPreview);
        pnlBtn.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        panel.setBorder(new EmptyBorder(50, 5, 25, 5));
        
        add(panel,BorderLayout.NORTH);
        add(pnlBtn,BorderLayout.CENTER);
	}
	
	private void parametarAutor(Parametar p) {
		JLabel lblIme = new JLabel("    Ime Autora :  ");
        JLabel lblPrezime = new JLabel("    Prezime Autora : ");
        tfIme = new JTextField();
        tfPrezime = new JTextField();
        
        tfIme.setText(((ParametarAutor) p).getIme());
        tfPrezime.setText(((ParametarAutor) p).getPrezime());
        
        tfIme.addKeyListener(new MyList());
        tfPrezime.addKeyListener(new MyList());
		
        tfIme.setColumns(20);
        tfPrezime.setColumns(20);
        
        GridLayout gl = new GridLayout(0,2);
        JPanel panel = new JPanel();
        panel.setLayout(gl);
        
        gl.setHgap(10);
        gl.setVgap(25);
        
        panel.add(lblIme);
        panel.add(tfIme);
        panel.add(lblPrezime);
        panel.add(tfPrezime);

        panel.setBorder(new EmptyBorder(50, 5, 5, 5));
        
        add(panel);
	}
	
	private void parametarUslovi(Parametar p) {
		setBorder(new EmptyBorder(0, 70, 5, 70));
		setLayout(new BorderLayout());
		JLabel lblIme = new JLabel("    Uslovi koriscenja :  ");
        tfUslovi = new JTextField();
        JButton btnChoose = new JButton("Choose");
        JButton btnPreview = new JButton("Preview");
        
        tfUslovi.setText(((ParametarUslovi) p).getUslovi());
        
        tfUslovi.addKeyListener(new MyList());
		
        tfUslovi.setColumns(32);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        panel.add(lblIme);
        panel.add(tfUslovi);
        
        JPanel pnlBtn = new JPanel();
        pnlBtn.add(btnChoose);
        pnlBtn.add(btnPreview);
        pnlBtn.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        btnChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Text", "txt");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(Frejm.getInstance()) != 0) return;
				File file = fc.getSelectedFile();
				if (file == null) return;
				String s = file.getPath();
				tfUslovi.setText(s);
				((ParametarUslovi) p).setUslovi(s);
			}
		});

        btnPreview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					File file = new File(tfUslovi.getText());
					if (file.exists()) {
						if (getExtension(tfUslovi.getText()).equals("txt")) {
							JTextArea jta = new JTextArea(40, 100);
							jta.setEditable(false);
							jta.setLineWrap(true);
					        jta.setWrapStyleWord(true);
					        Scanner s = new Scanner(file);
					        String string = "";
					        while(s.hasNextLine()) {
					        	string += s.nextLine() + "\n";
					        }
					        s.close();
					        jta.setText(string.toString());
							JFrame frame = new JFrame();
							frame.setTitle("Preview");
							frame.add(new JScrollPane(jta));
							frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					        frame.pack();
					        frame.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(Frejm.getInstance(), "File isn't .txt format.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(Frejm.getInstance(), "File doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
        
        panel.setBorder(new EmptyBorder(50, 5, 25, 5));
        
        add(panel,BorderLayout.NORTH);
        add(pnlBtn,BorderLayout.CENTER);
	}
	
	public JTextField getTfIme() {
		return tfIme;
	}
	
	public JTextField getTfPrezime() {
		return tfPrezime;
	}
	
	public JTextField getTfUslovi() {
		return tfUslovi;
	}
	
	private String punoIme(String look) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (int i = 0 ; i < looks.length ; i++) {
			if (looks[i].getName().equals(look))
				return looks[i].getClassName();
		}
		return "";
	}
	
	private String maloIme(String look) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (int i = 0 ; i < looks.length ; i++) {
			if (looks[i].getClassName().equals(look))
				return looks[i].getName();
		}
		return "";
	}
	
	private String getExtension(String path) {
		String tmp = "";
		int i = path.length() - 1;
		while(path.charAt(i) != '.') {
			tmp += path.charAt(i);
			i--;
		}
		tmp = new StringBuilder(tmp).reverse().toString();
		return tmp;
	}
}
