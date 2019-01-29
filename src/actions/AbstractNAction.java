package actions;

import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public abstract class AbstractNAction extends AbstractAction{

	public ImageIcon loadIcon(String fileName){
		
		ImageIcon img = null;
		File tmp = new File(fileName);
		
		if (tmp.exists()) {                      
			img = new ImageIcon(fileName);
		}else {
			System.err.println("Resource not found: " + fileName);
		}
		return img;
	}

}
