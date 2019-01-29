package instaler;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import view.Frejm;

public class PopUp extends JPopupMenu {
    JMenuItem itemExpand;
    JMenuItem itemCollapse;
    JMenuItem itemCopy;
    JMenuItem itemPaste;
    JMenuItem itemCut;
    
    public PopUp(){
    	itemExpand = new JMenuItem("Expand all");
    	itemExpand.setAction(Frejm.getInstance().getActionManager().getExpandAction());
        add(itemExpand);
        addSeparator();
        itemCollapse = new JMenuItem("Collapse all");
        itemCollapse.setAction(Frejm.getInstance().getActionManager().getCollapseAction());
        add(itemCollapse);
        addSeparator();
        itemCopy = new JMenuItem("Copy");
        itemCopy.setAction(Frejm.getInstance().getActionManager().getClipboardCopy());
        add(itemCopy);
        addSeparator();
        itemPaste = new JMenuItem("Paste");
        itemPaste.setAction(Frejm.getInstance().getActionManager().getClipboardPaste());
        add(itemPaste);
        addSeparator();
        itemCut = new JMenuItem("Cut");
        itemCut.setAction(Frejm.getInstance().getActionManager().getClipboardCut());
        add(itemCut);
    }
}