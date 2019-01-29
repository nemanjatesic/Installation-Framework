package model;

import java.util.ArrayList;

import events.IListener;
import events.IObserver;

public class TimeObs implements IObserver {

	private String text = "";
	ArrayList<IListener> listeners = new ArrayList<>();

	public void change(String text) {
		this.text = text;
		notify(text);
	}

	@Override
	public void addListener(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void notify(Object o) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).update(o);
		}
	}

	public String getText() {
		return text;
	}

}
