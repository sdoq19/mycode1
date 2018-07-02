package priv.resource.thread.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是线程安全的。 
 * 比如一个 ArrayList 类，在添加一个元素的时候，它可能会有两步来完成：1. 在 Items[Size] 的位置存放此元素；2. 增大 Size 的值。 
 * 在单线程运行的情况下，如果 Size = 0，添加一个元素后，此元素在位置 0，而且 Size=1； 
 * 而如果是在多线程情况下，比如有两个线程，线程 A 先将元素存放在位置 0。但是此时 CPU 调度线程A暂停，线程 B 得到运行的机会。线程B也向此 ArrayList 添加元素，因为此时 Size 仍然等于 0 （注意哦，我们假设的是添加一个元素是要两个步骤哦，而线程A仅仅完成了步骤1），所以线程B也将元素存放在位置0。然后线程A和线程B都继续运行，都增加 Size 的值。 
 * 那好，现在我们来看看 ArrayList 的情况，元素实际上只有一个，存放在位置 0，而 Size 却等于 2。这就是“线程不安全”了
 * @author lenovo
 *
 */
public class SynchronizedCollectionTest {

	//static List<Integer> al = Collections.synchronizedList(new ArrayList<Integer>(20)); // thread safe
	static List<Integer> al = new ArrayList<Integer>(20);
	static List<Integer> vt = new Vector<Integer>();

	public static void main(String[] args) throws Exception {

		Thread thread1 = new Thread() {

			public void run() {
				for (int i = 0; i < 10; i++) {
					al.add(al.size(), new Integer(i));
					vt.add(vt.size(), new Integer(i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread thread2 = new Thread() {

			public void run() {
				for (int i = 0; i < 10; i++) {
					al.add(al.size(), new Integer(i));
					vt.add(vt.size(), new Integer(i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		
		//Thread.sleep(1000);
		System.out.println(al);
		System.out.println(vt);
		
		// result:
		// [0, 0, 1, null, 2, 2, 3, 3, 4, 5, null, 6, 6, 7, 7, 8, null, 9, 9]
		// [0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9]
	}

}