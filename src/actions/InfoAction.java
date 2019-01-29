package actions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class InfoAction extends AbstractNAction{

	public InfoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/info.png"));
		putValue(NAME, "mInfo");
		putValue(SHORT_DESCRIPTION, "Author info");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame frame = new JFrame("Author Info");
		frame.setSize(330,300);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon("src/resursi/pic.png");
		JLabel labelName = new JLabel("<html><h1>Nemanja Tešić</h1></html>");
		JLabel labelIndex = new JLabel("<html><h1>RN 30/17</h1></html>");
		labelName.setHorizontalAlignment(SwingConstants.CENTER);
		labelIndex.setHorizontalAlignment(SwingConstants.CENTER);

		frame.add(new JLabel(image),BorderLayout.CENTER);
		frame.add(labelName, BorderLayout.NORTH);
		frame.add(labelIndex, BorderLayout.SOUTH);
		
		frame.setBackground(Color.YELLOW);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
