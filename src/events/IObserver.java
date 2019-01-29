package events;

public interface IObserver {
	void addListener(IListener listener);
	void removeListener(IListener listener);
	void notify(Object o);
}
