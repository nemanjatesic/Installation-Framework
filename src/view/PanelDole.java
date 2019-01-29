package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.TimeListen;
import events.IListener;
import model.TimeObs;

public class PanelDole extends JPanel implements IListener{
	
	private Timer timer = new Timer(1000, new TimeListen());
	private TimeObs currentTime = new TimeObs();
	private JLabel label2;
	private JLabel label1;
	private JLabel label3;
	private JLabel label4;
	
	public PanelDole() {
		setLayout(new BorderLayout(5,5));
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		panel1.setPreferredSize(new Dimension(200, 30));
		panel2.setPreferredSize(new Dimension(220, 30));
		panel3.setPreferredSize(new Dimension(200, 30));
		
		JSplitPane splitLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel1,panel2);
		JSplitPane splitRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel3,panel4);
		JSplitPane splitCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitLeft,splitRight);
		
		splitLeft.setDividerSize(3);
		splitLeft.setEnabled(false);

		splitRight.setDividerSize(3);
		splitRight.setEnabled(false);
		
		splitCenter.setDividerSize(3);
		splitCenter.setEnabled(false);
		
		Date now = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 
	    String time = sdf.format(now);
		
		label1 = new JLabel("Datu i vreme: " + time);
		String userName = Frejm.getInstance().getKorisnik();
		label2 = new JLabel("Korisnik: < " + userName + " >");
		label3 = new JLabel("Akcija: < Nazvi komandne akcije >");
		label4 = new JLabel("Status: < Ready >");
		
		label1.setFont(new Font("", 3, 11));
		label2.setFont(new Font("", 1, 11));
		label3.setFont(new Font("", 1, 11));
		label4.setFont(new Font("", 3, 11));
		
		panel4.setBackground(new Color(65, 242, 87));
		panel3.setBackground(new Color(227, 172, 101));
		
		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);
		panel4.add(label4);
		
		add(splitCenter);
		currentTime.addListener(this);
		timer.start();
	}
	
	@Override
	public void update(Object o) {
		label1.setText("Datum i vreme: " + (String)o);
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public TimeObs getCurrentTime() {
		return currentTime;
	}
	
	public JLabel getLabel1() {
		return label1;
	}
	
	public JLabel getLabel2() {
		return label2;
	}
	
	public JLabel getLabel3() {
		return label3;
	}
	
	public JLabel getLabel4() {
		return label4;
	}

}
