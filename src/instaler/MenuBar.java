package instaler;

import javax.swing.Box;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar{
	
	public MenuBar() {
		Menu jMFile = new Menu("File");
		Menu jMView = new Menu("View");
		
		Menu ParametrizacijaMenu = new Menu("Parametrizacija");
		Menu PomocMenu = new Menu("Pomoc");
		Menu AboutMenu = new Menu("About");
		Menu EditMenu = new Menu("Edit");
		
		add(jMFile);
		add(jMView);
		add(EditMenu);
		
		add(Box.createHorizontalGlue());
		
		add(ParametrizacijaMenu);
		add(PomocMenu);
		add(AboutMenu);
		
	}
	
}