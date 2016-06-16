package priv.resource.thread.forFun;

/**
 * 创建一个大舞台，舞台线程
 * 
 * @author feizi
 * @time 2014-11-14下午2:38:21
 */
public class StageThread extends Thread {

	// 覆写run方法
	public void run() {

		// 旁边
		System.out.println("欢迎大家观看隋唐演义...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		System.out.println("大幕徐徐拉开，精彩大戏隋唐演义即将上演！");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		String str = "话说隋朝末年，隋炀帝当政,朝廷昏庸无道，百姓民不聊生。此时天下义军蜂起,英雄辈出，各地纷纷揭竿起义..." + "为了推翻腐朽的隋王朝的统治，农民起义军与隋军先后爆发了大大小小数十场战役，此时的瓦岗寨，隋军正与起义军奋血厮杀，杀得那是叫一个昏天黑地....";

		String[] text = str.split("");
		for (int i = 0; i < text.length; i++) {
			System.out.print(text[i]);
			if (i > 50) {
				try {
					// 使用sleep方法让线程暂停执行
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// 刚开始前面的字幕出现的比较慢，后面的出现就快些
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		// 创建一支隋朝军队线程
		ArmyRunnable armyTaskOfSuiDanasty = new ArmyRunnable();
		// 创建一支农民起义军线程
		ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

		Thread armySuiThread = new Thread(armyTaskOfSuiDanasty, "【隋军】");
		Thread armyRevolt = new Thread(armyTaskOfRevolt, "【农民起义军】");

		armySuiThread.start();
		armyRevolt.start();

		try {
			// 让舞台线程休眠，观众专心看两军对垒厮杀.
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("正当双方激战正酣之际，半路杀出了个程咬金...妈蛋！");
		Thread mrCheng = new HeroPersonThread();
		mrCheng.setName("【程咬金】");

		String string = "话说程咬金的理想就是希望早点结束战争，让百姓过上太平的日子...";
		String[] content = string.split("");

		for (int i = 0; i < content.length; i++) {
			System.out.print(content[i]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 程咬金出场，双方军队停止进攻，等待大人物的出场
		armyTaskOfSuiDanasty.keepRunning = false;
		armyTaskOfRevolt.keepRunning = false;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/**
		 * 历史大舞台留给英雄人物
		 */
		mrCheng.start();
		try {
			// join表示等待当前线程终止，其他线程才能继续执行，表明让其他线程等待当前线程执行完毕
			mrCheng.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("战争结束，人民安居乐业!程咬金实现了自己的人生理想，为百姓做出了贡献...");
		// 谢幕
		System.out.println("感谢大家的观看，再见...");

	}

	public static void main(String[] args) {
		new StageThread().start();
	}
}