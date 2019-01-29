package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import events.IListener;
import events.IObserver;

public abstract class Node implements TreeNode,IObserver,Serializable,Comparable<Node>{
	protected Node parent;
	protected String name;
	protected String sastav;
	protected ArrayList<Node> deca = new ArrayList<>();
	protected transient ArrayList<IListener> listeners;
	private int broj;
	
	private static final long serialVersionUID = 123l;
	
	public Node() {
	}
	
	public Node(String name, Node parent) {
		this.name = name;
		this.parent = parent;
		lazyLoad();
	}

	public String getSastav() {
		return sastav;
	}
	
	public void setSastav(String sastav) {
		this.sastav = sastav;
		notify(this);
	}
	
	private void lazyLoad() {
		if(deca == null) {
			deca = new ArrayList<>();
		}
		if(listeners == null) {
			listeners = new ArrayList<>();
		}
	}
	
	public ArrayList<Node> getDeca() {
		return deca;
	}
	
	public void addNode(Node node) {
		deca.add(node);
		notify(this);
	}
	
	public void remove(Node node) {
		deca.remove(node);
	}
	
	public boolean contains(Node node) {
		return deca.contains(node);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		notify(this);
	}
	
	public Enumeration<Node> children() {
		return (Enumeration<Node>) deca;
	}

	public String toString() {
		return name;
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public TreeNode getChildAt(int arg0) {
		return deca.get(arg0);
	}

	public int getChildIndex(Node child) {
		for (int i = 0 ; i < this.deca.size() ; i++) {
			if (this.deca.get(i) == child) {
				return i;
			}
		}
		return -1;
	}
	
	public int getChildCount() {
		if (!isLeaf()) return deca.size();
		return 0;
	}

	public int getIndex(TreeNode arg0) {
		Node arg = (Node)arg0; 
		return deca.indexOf(arg);
	}

	public TreeNode getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return deca.size() == 0;
	}

	public ArrayList<IListener> getListeners() {
		lazyLoad();
		return listeners;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	@Override
	public void addListener(IListener listener) {
		lazyLoad();
		listeners.add(listener);
	}

	@Override
	public void removeListener(IListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void notify(Object o) {
		for (int i = 0 ; i < listeners.size() ; i++) {
			listeners.get(i).update(o);
		}
	}
	
	public int getBroj() {
		return broj;
	}
	
	public void setBroj(int broj) {
		this.broj = broj;
	}
	
	@Override
	public int compareTo(Node o) {
		return broj - o.broj;
	}

}
