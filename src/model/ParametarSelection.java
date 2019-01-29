package model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class ParametarSelection implements Transferable,ClipboardOwner{

	public static DataFlavor elementFlavor;
	
	private DataFlavor[] supportedFlavors = new DataFlavor[1];
	public ArrayList<Parametar> parametarElements = new ArrayList<>();
	
	public ParametarSelection(ArrayList<Parametar> elements) {
		elementFlavor = new DataFlavor(parametarElements.getClass(),"Elements");
		supportedFlavors[0] = elementFlavor;
		parametarElements = elements;
	}
	
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals (elementFlavor))
			 return (parametarElements);
		else
			 throw new UnsupportedFlavorException (elementFlavor);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.equals (elementFlavor));
	}

}
