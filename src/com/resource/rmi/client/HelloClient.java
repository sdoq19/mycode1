package com.resource.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.resource.rmi.service.IHello;

public class HelloClient {
	
	public static void main(String[] args) {
		try {
			// 在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
			IHello rhello = (IHello) Naming.lookup("rmi://localhost:8888/RHello");
			rhello.sayHello();
		} catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
		
	}
}
