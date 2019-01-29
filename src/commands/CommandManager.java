package commands;

import java.util.ArrayList;

import view.Frejm;

public class CommandManager {
	private ArrayList<AbstractCommand> komande = new ArrayList<>();
	private int currCommand = -1;
	
	public void addCommand(AbstractCommand command){
		komande.subList(currCommand+1, komande.size()).clear();
		currCommand++;
	 	komande.add(command);
	 	Frejm.getInstance().getActionManager().getRedoAction().setEnabled(false);
	 	Frejm.getInstance().getActionManager().getUndoAction().setEnabled(true);
	 }

	public void undoCommand(){
		if(currCommand >= 0){
			komande.get(currCommand--).undoCommand();
			Frejm.getInstance().getActionManager().getRedoAction().setEnabled(true);
		}
		if(currCommand == -1){
			Frejm.getInstance().getActionManager().getUndoAction().setEnabled(false);
	 	}
	}

	public void doCommand(){
		Frejm.getInstance().getActionManager().getRedoAction().setEnabled(true);
		
		if(currCommand < komande.size() ){	
			Frejm.getInstance().getActionManager().getUndoAction().setEnabled(true);
			komande.get(++currCommand).doCommand();
		}
		if (currCommand == komande.size() - 1){
			Frejm.getInstance().getActionManager().getRedoAction().setEnabled(false);
	 	}
	}
	
	public int getCurrCommand() {
		return currCommand;
	}

	public void setCurrCommand(int currCommand) {
		this.currCommand = currCommand;
	}
}
