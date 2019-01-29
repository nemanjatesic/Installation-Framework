package app;

import java.io.File;

import javax.swing.UIManager;

import instaler.SignIn;

public class Main {

	private static int a;
	private static File file = null;
	private static Boolean save = false;
	private static String korisnik = "";
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new SignIn();
	}

	public static void setA(int a) {
		Main.a = a;
	}
	
	public static int getA() {
		return a;
	}
	
	public static File getFile() {
		return file;
	}
	
	public static void setFile(File file) {
		Main.file = file;
	}
	
	public static Boolean getSave() {
		return save;
	}
	
	public static void setSave(Boolean save) {
		Main.save = save;
	}
	
	public static String getKorisnik() {
		return korisnik;
	}
	
	public static void setKorisnik(String korisnik) {
		Main.korisnik = korisnik;
	}
}
