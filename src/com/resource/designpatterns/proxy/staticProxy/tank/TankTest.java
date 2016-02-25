package com.resource.designpatterns.proxy.staticProxy.tank;

public class TankTest {

	public static void main(String[] args) {
		TankMove tankMove = new TankMove();
		Moveable move = new TankTimeProxy(tankMove);
		Moveable movet = new TankLogProxy(move);
		movet.move();
	}
}
