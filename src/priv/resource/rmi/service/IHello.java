package priv.resource.rmi.service;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
* 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常 
 * @author zhuzh
 */
public interface IHello extends Remote {

	public void sayHello() throws RemoteException;
}
