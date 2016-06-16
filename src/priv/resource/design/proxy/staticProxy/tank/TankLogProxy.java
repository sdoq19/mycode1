package priv.resource.design.proxy.staticProxy.tank;

public class TankLogProxy implements Moveable {

	private Moveable m;

	public TankLogProxy(Moveable m) {
		super();
		this.m = m;
	}

	public void move() {
		System.out.println("start move ...");
		m.move();
		System.out.println("end move ...");
	}

}
