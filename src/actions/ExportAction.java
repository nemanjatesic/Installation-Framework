package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import instaler.ErrorHandling;
import model.Modul;
import model.Parametar;
import model.ParametarLogo;
import model.ParametarUslovi;
import model.Proizvod;
import view.Frejm;

public class ExportAction extends AbstractNAction{

	public ExportAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("src/resursi/export-24.png"));
		putValue(NAME, "mExport");
		putValue(SHORT_DESCRIPTION, "Export");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object curr = Frejm.getInstance().getNodeTree().getLastSelectedPathComponent();
		if (curr == null || curr instanceof Proizvod == false) {
			ErrorHandling.handle(this, new NullPointerException());
			return;
		}
		
		String s = "src/Proizvodi za Instalaciju/" + ((Proizvod)curr).getName();
		File file = new File(s);
		file.mkdirs();
		s += "/resursi";
		File resursi = new File(s);
		resursi.mkdirs();
		String destination = s + "/";
		if (((Proizvod)curr).getFilePath() == null || ((Proizvod)curr).getFilePath().equals("")) {
			ErrorHandling.handle(this, new NullPointerException("Putanja"));
			return;
		}
			
		File source = new File(((Proizvod)curr).getFilePath());
		s += "/" + source.getName();
		File dest = new File(s);
		
		try{
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e){
		
		}
		s = "resursi/" + source.getName();
		((Proizvod) curr).setFilePath(s);
		ArrayList<Parametar> list = new ArrayList<>();
		
		for (int i = 0 ; i < ((Proizvod) curr).getDeca().size() ; i++){
			if (((Proizvod)curr).getDeca().get(i) instanceof ParametarLogo || ((Proizvod)curr).getDeca().get(i) instanceof ParametarUslovi)
				list.add((Parametar)((Proizvod)curr).getDeca().get(i));
			else{
				if (((Proizvod)curr).getDeca().get(i) instanceof Modul){
					Modul mm = (Modul)((Proizvod)curr).getDeca().get(i);
					for (int j = 0; j < mm.getDeca().size(); j++){
						if (mm.getDeca().get(j) instanceof ParametarLogo|| mm.getDeca().get(j) instanceof ParametarUslovi) {
							list.add((Parametar)mm.getDeca().get(j));
						}
					}
				}
			}
		}
		
		for(int i = 0 ; i < list.size() ; i++){
			if (list.get(i) instanceof ParametarLogo){
				try{
					String path = ((ParametarLogo) list.get(i)).getLogo();
					source = new File(path);
					s = "resursi/" + source.getName();
					((ParametarLogo) list.get(i)).setLogo(s);
					String ss = destination + source.getName();
					dest = new File(ss);
					try{
						Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}catch (IOException e){
						e.printStackTrace();
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			if (list.get(i) instanceof ParametarUslovi){
				try{
					String path = ((ParametarUslovi)list.get(i)).getUslovi();
					source = new File(path);
					s = "resursi/" + source.getName();
					((ParametarUslovi) list.get(i)).setUslovi(s);
					String ss = destination + source.getName();
					dest = new File(ss);
					try{
						Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		File path = new File(file.getAbsolutePath() + "/app");
		try {
			if (!path.createNewFile()) {
				path.delete();
				path.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			
			out.writeObject(curr);
			
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ErrorHandling.handle(this, null);
	}

}
