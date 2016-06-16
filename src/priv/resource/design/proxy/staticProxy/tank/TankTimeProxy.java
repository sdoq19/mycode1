package priv.resource.design.proxy.staticProxy.tank;

public class TankTimeProxy implements Moveable {

	private Moveable m;

	public TankTimeProxy(Moveable m) {
		super();
		this.m = m;
	}

	public void move() {
		long time1 = System.currentTimeMillis();
		System.out.println("time1:" + time1);

		m.move();

		long time2 = System.currentTimeMillis();
		System.out.println("time2:" + time2);
		System.out.println("运行时间为：" + (time2 - time1));
	}
}
