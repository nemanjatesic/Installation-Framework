package instaler;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import app.Main;
import controller.WindowListen;
import view.Frejm;

@SuppressWarnings("serial")
public class SignIn extends JFrame{
	private JTextField tfAccount;
	private JPasswordField pfPassword;
		
	public SignIn() {
		addWindowListener(new WindowListen());
		setLayout(new BorderLayout(5,5));
		setSize(285,143);
		setTitle("Sign in");

		pfPassword = new JPasswordField();
		tfAccount = new JTextField();
		
		pfPassword.setText("1");
		tfAccount.setText("1");
		
		JButton btnSignIn = new JButton("Sign in");
		JButton btnRegister = new JButton("Register");
		
		JLabel lblAccount = new JLabel("   Account name: ");
		JLabel lblPassword = new JLabel("   Password: ");
		
		GridLayout gl = new GridLayout(0,2);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(gl);
        panel2.setLayout(new FlowLayout());
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        gl.setHgap(5);
        gl.setVgap(10);
		
        panel1.add(lblAccount);
        panel1.add(tfAccount);
        panel1.add(lblPassword);
        panel1.add(pfPassword);
        
        panel2.add(btnSignIn);
        panel2.add(btnRegister);
		
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signInFunction();
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerFunction();
			}
		});
		
		getContentPane().add(panel1,BorderLayout.NORTH);
        getContentPane().add(panel2, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void registerFunction() {
        String[] roles = {"User","Admin"};
        JComboBox<String> cb = new JComboBox<>(roles);

        cb.setEditable(false);

        JButton btnOK = new JButton("Sign Up");
        
        JPasswordField pfPass = new JPasswordField();
        JPasswordField pfConfirmPass = new JPasswordField();
		JTextField tfAcc = new JTextField();
		
		JLabel lblAccount = new JLabel("    Account name: ");
		JLabel lblPassword = new JLabel("    Password: ");
		JLabel lblRole = new JLabel("    Select role: ");
		JLabel lblConfirmPass = new JLabel("    Confirm Password: ");
		
        
        JFrame frame = new JFrame("Register");
        GridLayout gl = new GridLayout(0,2);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(gl);
        panel2.setLayout(new FlowLayout());
        
        gl.setHgap(5);
        gl.setVgap(10);
        
        panel1.add(lblAccount);
        panel1.add(tfAcc);
        panel1.add(lblPassword);
        panel1.add(pfPass);
        panel1.add(lblConfirmPass);
        panel1.add(pfConfirmPass);
        panel1.add(lblRole);
        panel1.add(cb);
        
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        panel2.add(btnOK);
        
        frame.getContentPane().add(panel1,BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.CENTER);
        
        frame.setSize(280, 193);
        frame.setVisible(true);
        frame.setLocationRelativeTo(this);
        
        btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tfAcc.getText().equals("")) {
					if(pfPass.getPassword().length == 0) {
						JOptionPane.showMessageDialog(frame, "Please enter Username and Password.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}else {
						JOptionPane.showMessageDialog(frame, "Please enter the Username.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else if (pfPass.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "Please enter the Password.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String[] string;
				Scanner s;
				File file = new File("users.txt");
				
				try{
					s = new Scanner(file);
					while(s.hasNextLine()) {
						String tmp = s.nextLine();
						string = tmp.split("'");
						if (string[0].equals(tfAcc.getText())) {
							JOptionPane.showMessageDialog(frame, "Some one already used that username.", "Error", JOptionPane.ERROR_MESSAGE);
							s.close();
							return;
						}
					}
					s.close();
				}catch (Exception err) {
					err.printStackTrace();
				}
				
				String user = "";
				
				String p1 = getHashed(String.valueOf(pfPass.getPassword()),"SHA-512");
				String p2 = getHashed(String.valueOf(pfConfirmPass.getPassword()),"SHA-512");
				if(p1.equals(p2)) {
					user += tfAcc.getText() + "'";
					user += p1 + "'";
					user += cb.getSelectedItem() + "\n";
				}else {
					JOptionPane.showMessageDialog(frame, "Your passwords don't match.", "Password", JOptionPane.ERROR_MESSAGE);
					pfConfirmPass.setText("");
					pfPass.setText("");
					return;
				}
				
				try {
					FileWriter fw = new FileWriter(file,true);
					PrintWriter pw = new PrintWriter(fw);
					pw.print(user);
					pw.close();
					fw.close();
					JOptionPane.showMessageDialog(frame, "Account successfully created.", "Account", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.dispose();
			}
		});
	}
	
	private void signInFunction() {
		File file = new File("users.txt");
		Scanner s;
		String[] string;
		
		try{
			s = new Scanner(file);
			while(s.hasNextLine()) {
				String tmp = s.nextLine();
				string = tmp.split("'");
				String account = tfAccount.getText();
				String password = getHashed(String.valueOf(pfPassword.getPassword()),"SHA-512");
				String userClass = string[2];
				
				
				if(account.equals("")) {
					if(pfPassword.getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "Please enter Username and Password.", "Error", JOptionPane.ERROR_MESSAGE);
						s.close();
						return;
					}else {
						JOptionPane.showMessageDialog(null, "Please enter the Username.", "Error", JOptionPane.ERROR_MESSAGE);
						s.close();
						return;
					}
				}else if (pfPassword.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Please enter the Password.", "Error", JOptionPane.ERROR_MESSAGE);
					s.close();
					return;
				}
				if(account.equals(string[0]) && password.equals(string[1])) {
					
					this.setVisible(false);
					
					int a = JOptionPane.showConfirmDialog(null,"Da li zelite da izaberete workspace ?");
					
					if(a == 0) {
						Frejm mainFrame = Frejm.getInstance(userClass);
						mainFrame.inicijalizuj();
						Frejm.getInstance().getActionManager().getOpenProjectAction().actionPerformed(null);
						mainFrame.setVisible(true);
					}else if (a == 1) {
						Frejm mainFrame = Frejm.getInstance(userClass);
						mainFrame.inicijalizuj();
						mainFrame.setVisible(true);
					}else if (a == 2 || a == -1) {
						Frejm.getInstance().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						System.exit(0);
					}
					
					Main.setA(a);
					
					this.dispose();
					s.close();
					return;
				}
				
			}
			JOptionPane.showMessageDialog(null, "Username or Password are incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
			pfPassword.setText("");
			s.close();
		}catch(Exception a) {
			a.printStackTrace();
		}
	}
	
	private String getHashed(String pass, String lvl) {
		String generatedPassword = "";
		try {
			StringBuilder sb = new StringBuilder();
			MessageDigest md = MessageDigest.getInstance(lvl);
			byte[] byteArray = md.digest(pass.getBytes());
			
			for (int i = 0 ; i < byteArray.length ; i++) {
				sb.append(Integer.toString((byteArray[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}