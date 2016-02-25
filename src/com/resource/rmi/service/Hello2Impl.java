package com.resource.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.utils.LoggerUtil;

/**
 * 远程的接口的实现 
 * @author zhuzh
 *
 */
public class Hello2Impl extends UnicastRemoteObject implements IHello {

	private static final long serialVersionUID = -5208672414533798662L;

	/**
	 * 因为UnicastRemoteObject的构造方法抛出了RemoteException异常，因此这里默认的构造方法必须写，必须声明抛出RemoteException异常 
	 * @throws RemoteException
	 */
	protected Hello2Impl() throws RemoteException {
		super();
	}

	@Override
	public void sayHello() throws RemoteException {
		LoggerUtil.info(getClass(), "This is RMI test...");
	}
	
}
