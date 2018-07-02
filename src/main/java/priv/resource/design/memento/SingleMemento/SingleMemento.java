package priv.resource.design.memento.SingleMemento;

/**
 * 发起人：记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
 * 备忘录：负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 * 管理角色：对备忘录进行管理，保存和提供备忘录。
 */


/**
 * 发起人
 * @author zhuzh
 *
 */
class Originator {
	private String state = "";
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Memento createMemento(){
		return new Memento(this.state);
	}
	public void restoreMemento(Memento memento){
		this.setState(memento.getState());
	}
}

/**
 * 备忘录
 * @author zhuzh
 *
 */
class Memento {
	private String state = "";
	public Memento(String state){
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

/**
 * 管理角色
 * @author zhuzh
 *
 */
class Caretaker {
	private Memento memento;
	public Memento getMemento(){
		return memento;
	}
	public void setMemento(Memento memento){
		this.memento = memento;
	}
}


public class SingleMemento {
	
	public static void main(String[] args){
		Originator originator = new Originator();
		originator.setState("状态1");
		System.out.println("初始状态:"+originator.getState());
		Caretaker caretaker = new Caretaker();
		caretaker.setMemento(originator.createMemento());
		originator.setState("状态2");
		System.out.println("改变后状态:"+originator.getState());
		originator.restoreMemento(caretaker.getMemento());
		System.out.println("恢复后状态:"+originator.getState());
	}
}
