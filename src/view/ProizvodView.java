package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.MyList;
import model.ParametarLogo;
import model.Proizvod;

public class ProizvodView extends JPanel{
	private JTextField tfLogo;

	public ProizvodView(Proizvod proizvod) {
		setBorder(new EmptyBorder(0, 70, 5, 70));
		setLayout(new BorderLayout());
		JLabel lblIme = new JLabel("    Izabrana putanja :  ");
        tfLogo = new JTextField();
        JButton btnChoose = new JButton("Choose");
        
        tfLogo.setText(proizvod.getFilePath());
        
        tfLogo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				proizvod.setFilePath(tfLogo.getText());
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
        tfLogo.setColumns(32);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        panel.add(lblIme);
        panel.add(tfLogo);
        
        btnChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(Frejm.getInstance()) != 0) return;
				File file = fc.getSelectedFile();
				if (file == null) return;
				String s = file.getPath();
				tfLogo.setText(s);
				proizvod.setFilePath(s);;
			}
		});
        
        JPanel pnlBtn = new JPanel();
        pnlBtn.add(btnChoose);
        pnlBtn.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        panel.setBorder(new EmptyBorder(50, 5, 25, 5));
        
        add(panel,BorderLayout.NORTH);
        add(pnlBtn,BorderLayout.CENTER);
	}
}
