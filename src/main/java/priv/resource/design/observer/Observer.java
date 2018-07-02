package priv.resource.design.observer;

public interface Observer {
	
	// 所有观察者都需实现该接口
	public void update(Observable o, Object arg);
}
