package view;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import actions.ActionManager;
import commands.CommandManager;
import controller.WindowListen;
import gui.NodeTree;
import instaler.MenuBar;
import instaler.ToolBar;
import model.InstallModel;
import model.Node;
import model.NodeModel;

public class Frejm extends JFrame implements ClipboardOwner{
	private MenuBar jmb;
	private PanelCenterDown panCenterDown;
	private PanelDole panBottom;
	private PanelCenterUp panCenterUp;
	private PanelLeft panLeft;
	private ToolBar jtb;
	private ActionManager actionManager;
	private NodeTree nodeTree;
	private NodeModel nodeModel;
	private static Frejm instance = null;
	private String korisnik;
	private Clipboard clipboard = new Clipboard("Instafram Clipboard");
	private CommandManager commandManager = new CommandManager();
	private InstallModel installModel;
	
	private Frejm(){
		actionManager = new ActionManager();
		this.addWindowListener(new WindowListen());
	}
	
	public void inicijalizuj() {
		zapocniDrvo();
		setTitle("InstaFram");
		setSize(800,650);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		jmb = new MenuBar();

		jtb = new ToolBar(false);

		this.setJMenuBar(jmb);
		this.add(jtb , BorderLayout.NORTH);
		 
		panLeft = new PanelLeft();
		
		panCenterDown = new PanelCenterDown();
		 
		panCenterUp = new PanelCenterUp();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panCenterUp,panCenterDown);

		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panLeft,splitPane);
		 
		splitPane.setDividerSize(2);
		splitPane1.setDividerSize(2);

		panBottom = new PanelDole();
		
		((Node)nodeModel.getRoot()).removeListener(null);
		((Node)nodeModel.getRoot()).addListener(panCenterDown);
		 
		add(splitPane1,BorderLayout.CENTER);
		add(panBottom,BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
	}
	
	private void zapocniDrvo() {
		nodeTree = new NodeTree();
		nodeModel = new NodeModel();
		nodeTree.setModel(nodeModel);
	}
	
	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public static Frejm getInstance() { 
        if (instance == null) {
        	instance = new Frejm(); 
        }
        return instance; 
    }
	
	public static Frejm getInstance(String kor) { 
        if (instance == null) {
        	instance = new Frejm();
        	instance.korisnik = kor;
        }
        return instance; 
    }
	
	public NodeTree getNodeTree() {
		return nodeTree;
	}
	
	
	public NodeModel getNodeModel() {
		return nodeModel;
	}

	public PanelCenterDown getPanCenterDown() {
		return panCenterDown;
	}

	public void setPanCenterDown(PanelCenterDown panCenterDown) {
		this.panCenterDown = panCenterDown;
	}

	public MenuBar getJmb() {
		return jmb;
	}

	public PanelDole getPanBottom() {
		return panBottom;
	}

	public PanelCenterUp getPanCenterUp() {
		return panCenterUp;
	}

	public PanelLeft getPanLeft() {
		return panLeft;
	}

	public ToolBar getJtb() {
		return jtb;
	}
	
	public String getKorisnik() {
		return korisnik;
	}
	
	public Clipboard getClipboard() {
		return clipboard;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public InstallModel getInstallModel() {
		return installModel;
	}
	
	public void setInstallModel(InstallModel installModel) {
		this.installModel = installModel;
	}
	
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		
	}
}
