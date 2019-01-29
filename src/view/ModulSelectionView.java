package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import app.Main;
import model.Modul;
import model.MojCheckBox;
import model.Parametar;
import model.ParametarAutor;
import model.ParametarCustom;
import model.ParametarLogo;
import model.ParametarLook;
import model.ParametarNaziv;
import model.ParametarShortcut;
import model.ParametarStart;
import model.ParametarUslovi;
import model.Proizvod;

public class ModulSelectionView extends JDialog{
	private JDialog self = this;
	
	public ModulSelectionView() {
		
	}
	
	public void modulView(ArrayList<Modul> moduli) {
		JButton btnBrowse = new JButton("Browse");
		setTitle("Modul");
		setSize(300,200);
		JPanel pnl = new JPanel(new BorderLayout());
		ArrayList<MojCheckBox> buttons = new ArrayList<>();
		
		JPanel panelCenter = new JPanel(new GridLayout(0, 2));
		
		panelCenter.add(new JLabel("     Izaberite Module :"));
		
		if (moduli.size() != 0) {
			MojCheckBox box = new MojCheckBox(moduli.get(0).getName());
			panelCenter.add(box);
			buttons.add(box);
			
			box.setNode(moduli.get(0));
			
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (((Modul)box.getNode()).isSelected()) ((Modul)box.getNode()).setSelected(false);
					else ((Modul)box.getNode()).setSelected(true);
				}
			});
		}
		
		panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		for (int i = 1 ; i < moduli.size() ; i++) {
			MojCheckBox m = new MojCheckBox(moduli.get(i).getName());
			m.setNode(moduli.get(i));
			buttons.add(m);
		}
		
		for (int i = 1 ; i < moduli.size() ; i++) {
			panelCenter.add(new JLabel(""));
			panelCenter.add(buttons.get(i));
			
			MojCheckBox f = buttons.get(i);
			
			f.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (((Modul)f.getNode()).isSelected()) ((Modul)f.getNode()).setSelected(false);
					else ((Modul)f.getNode()).setSelected(true);
				}
			});
		}
		
		JButton btnNext = new JButton("Next >");
		JButton btnBack = new JButton("< Back");
		JButton btnClose = new JButton("Cancel");
		
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frejm.getInstance().getInstallModel().getChosenPath() == null) {
					JOptionPane.showMessageDialog(null, "Where you want to save, press Browse", "You didn't browse", JOptionPane.ERROR_MESSAGE);
					return;
				}
				self.dispose();
				ArrayList<Modul> moduliTmp = new ArrayList<>();
				for (Modul m : moduli) {
					if (m.isSelected()) moduliTmp.add(m);
				}
				Frejm.getInstance().getInstallModel().pomocna(moduliTmp);
			}
		});
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
		btnBrowse.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					JFileChooser chooser = new JFileChooser(); 
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.showSaveDialog(null);
					Frejm.getInstance().getInstallModel().setChosenPath(chooser.getSelectedFile().toPath());
				}
				catch (Exception ex){
					
				}
			}
		});
		JPanel panel = new JPanel(new FlowLayout());
		
		btnBack.setEnabled(false);
		
		panel.add(btnBrowse);
		panel.add(btnNext);
		panel.add(btnClose);
		
		pnl.add(new JScrollPane(panelCenter),BorderLayout.CENTER);
		pnl.add(panel,BorderLayout.SOUTH);
		
		add(pnl);
		
		setLocationRelativeTo(Frejm.getInstance());
	}
	
	public void parametarView(Parametar parametar, ArrayList<Parametar> lista) {
		JButton btnNext = new JButton("Next >");
		JButton btnBack = new JButton("< Back");
		JButton btnClose = new JButton("Cancel");
		JButton btnFinish = new JButton("Finish");
		String htmlB4 = "<html><h4>";
		String htmlE4 = "</html><h4/>";
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}catch (Exception ex) {
				}
				self.dispose();
			}
		});
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Frejm.getInstance().getInstallModel().setBroj(Frejm.getInstance().getInstallModel().getBroj() + 1);
				self.dispose();
				Frejm.getInstance().getInstallModel().update();
			}
		});
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frejm.getInstance().getInstallModel().setBroj(Frejm.getInstance().getInstallModel().getBroj() - 1);
				self.dispose();
				Frejm.getInstance().getInstallModel().update();
			}
		});
		btnFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				try{
					FileSystemView filesys = FileSystemView.getFileSystemView();
					File f = new File(((Proizvod)Frejm.getInstance().getInstallModel().getProizvodGlavni()).getFilePath());
					File desk = new File(filesys.getHomeDirectory().getAbsolutePath() + "\\" + lastThingInPath(((Proizvod)Frejm.getInstance().getInstallModel().getProizvodGlavni()).getFilePath()));
					Files.copy(f.toPath(), desk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					if (Frejm.getInstance().getInstallModel().isDesktopShortcut()) {
						Files.copy(f.toPath(), desk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
					if (Frejm.getInstance().getInstallModel().isStartAfterInstall()) {
						Desktop desktop = Desktop.getDesktop();
						if (desk.exists()) desktop.open(desk);
					}
				}catch (IOException ex){
					ex.printStackTrace();
				}
				self.dispose();
			}
		});
		
		
		if(parametar instanceof ParametarAutor) {
			setTitle("Parametar Autor");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblImeAutora = new JLabel(htmlB4 + "Ime Autora : " + htmlE4);
			JLabel lblPrezimeAutora = new JLabel(htmlB4 + "Prezime Autora : " + htmlE4);
			JLabel lblIme = new JLabel(htmlB4 + ((ParametarAutor)parametar).getIme() + htmlE4);
			JLabel lblPrezime = new JLabel(htmlB4 + ((ParametarAutor)parametar).getPrezime() + htmlE4);
			
			panelCenter.add(lblImeAutora);
			panelCenter.add(lblIme);
			panelCenter.add(lblPrezimeAutora);
			panelCenter.add(lblPrezime);
			
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarNaziv) {
			setTitle("Parametar Naziv");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblNaziv = new JLabel(htmlB4 + "Naziv : " + htmlE4);
			JLabel lblIme = new JLabel(htmlB4 + ((ParametarNaziv)parametar).getNaziv() + htmlE4);
			
			panelCenter.add(lblNaziv);
			panelCenter.add(lblIme);
			
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarUslovi) {
			setTitle("Parametar Naziv");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 1));
			JLabel lblNaziv = new JLabel(htmlB4 + "Uslovi koriscenja : " + htmlE4);
			JButton btnPreview = new JButton("Preview");
			
			btnPreview.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						File file = new File(((ParametarUslovi)parametar).getUslovi());
						if (file.exists()) {
							if (getExtension(((ParametarUslovi)parametar).getUslovi()).equals("txt")) {
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
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}
			});
			
			panelCenter.add(lblNaziv);
			panelCenter.add(btnPreview);
			
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarLogo) {
			setTitle("Parametar Logo");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblLogo = new JLabel(getScaledImage(((ParametarLogo)parametar).getLogo()));
			
			panelCenter.add(lblLogo);
			
			panelCenter.setBorder(new EmptyBorder(15, 100, 15, 15));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarShortcut) {
			setTitle("Parametar Shortcut");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblNaziv = new JLabel(htmlB4 + "Do you want to have shortcut ? : " + htmlE4);
			JCheckBox box = new JCheckBox("");
			
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (box.isSelected()) {
						if (!(Frejm.getInstance().getInstallModel().isDesktopShortcut())) {
							Frejm.getInstance().getInstallModel().setDesktopShortcut(true);
						}
					}
				}
			});
			
			if (((ParametarShortcut)parametar).isShortcut()) {
				panelCenter.add(lblNaziv);
				panelCenter.add(box);
			}else {
				JLabel lblError = new JLabel(htmlB4 + "Desktop shorcut is not allowed" + htmlE4);
				panelCenter.add(lblError);
			}
			
			
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarCustom) {
			setTitle("Parametar Custom");
			setSize(300,200);
			ParametarCustom par = (ParametarCustom)parametar;
			JLabel lblNaziv = new JLabel(par.getIme());
			JPanel panelCenter = new JPanel();
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
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
				panelCenter.setLayout(new FlowLayout());
				panelCenter.add(lblNaziv);
				panelCenter.add(btn1);
				panelCenter.add(btn2);
				panelCenter.setBorder(new EmptyBorder(50, 5, 5, 5));
				add(panelCenter,BorderLayout.CENTER);
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
				
				panelCenter.setLayout(new FlowLayout());
				panelCenter.add(lblNaziv);
				panelCenter.add(box);
				panelCenter.setBorder(new EmptyBorder(50, 5, 5, 5));
				add(panelCenter,BorderLayout.CENTER);
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
				
				panelCenter.setLayout(new FlowLayout());
				panelCenter.add(lblNaziv);
				panelCenter.add(text);
				panelCenter.setBorder(new EmptyBorder(50, 5, 5, 5));
				add(panelCenter,BorderLayout.CENTER);
			}
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			add(panel,BorderLayout.SOUTH);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarStart) {
			setTitle("Parametar Start");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblNaziv = new JLabel(htmlB4 + "Do you want to start program after installation ? : " + htmlE4);
			JCheckBox box = new JCheckBox("");
			
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (box.isSelected()) {
						if (!(Frejm.getInstance().getInstallModel().isStartAfterInstall())) {
							Frejm.getInstance().getInstallModel().setStartAfterInstall(true);;
						}
					}
				}
			});
			
			if (((ParametarStart)parametar).isStartAfter()) {
				panelCenter.add(lblNaziv);
				panelCenter.add(box);
			}else {
				JLabel lblError = new JLabel(htmlB4 + "Starting after installation is not allowed" + htmlE4);
				panelCenter.add(lblError);
			}
			
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnNext);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}else if (parametar instanceof ParametarLook) {
			setTitle("Parametar Start");
			setSize(300,200);
			JPanel panelCenter = new JPanel(new GridLayout(0, 2));
			JLabel lblNaziv = new JLabel(htmlB4 + "Select app look and feel : " + htmlE4);
			JButton btnSledece = new JButton("Next >");
			
			btnSledece.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						UIManager.setLookAndFeel(punoIme(((ParametarLook) parametar).getLookAndFeel()));
					}catch (Exception ex) {
						
					}
					Frejm.getInstance().getInstallModel().setBroj(Frejm.getInstance().getInstallModel().getBroj() + 1);
					self.dispose();
					Frejm.getInstance().getInstallModel().update();
				}
			});

			UIManager.LookAndFeelInfo[] look = UIManager.getInstalledLookAndFeels();
			Vector<String> c = new Vector<>();
			
			for (int i = 0 ; i < look.length ; i++) {
				c.add(look[i].getName());
			}
	        
			JComboBox<String> cb = new JComboBox<>(c);
	        cb.setEditable(false);

	        cb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					((ParametarLook)parametar).setLookAndFeel((String)cb.getSelectedItem());
				}
			});
	        
	        panelCenter.add(lblNaziv);
	        panelCenter.add(cb);
	        
			panelCenter.setBorder(new EmptyBorder(15, 40, 15, 15));
			
			JPanel panel = new JPanel(new FlowLayout());
			
			panel.add(btnBack);
			if (Frejm.getInstance().getInstallModel().getBroj() != lista.size() - 1)
				panel.add(btnSledece);
			else 
				panel.add(btnFinish);
			panel.add(btnClose);
			
			add(panelCenter,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			if (Frejm.getInstance().getInstallModel().getBroj() == 0) btnBack.setEnabled(false);
			setLocationRelativeTo(Frejm.getInstance());
		}
		
		
		
		setVisible(true);
	}
	
	private String punoIme(String look) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (int i = 0 ; i < looks.length ; i++) {
			if (looks[i].getName().equals(look))
				return looks[i].getClassName();
		}
		return "";
	}
	
	private ImageIcon getScaledImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		return imageIcon;
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
	
	private String lastThingInPath(String string) {
		String s = "";
		
		for (int i = string.length() - 1 ; i >= 0 ; i--) {
			if (string.charAt(i) != '\\' && string.charAt(i) != '/') {
				s += string.charAt(i);
			}else break;
		}
		String tmp = "";
		for (int i = s.length() - 1 ; i >= 0 ; i--) {
			tmp += s.charAt(i);
		}
		return tmp;
	}
}
