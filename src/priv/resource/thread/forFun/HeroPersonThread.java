package priv.resource.thread.forFun;

/**
 * 英雄人物线程
 * 
 * @author feizi
 * @time 2014-11-17下午5:41:15
 */
public class HeroPersonThread extends Thread {

	public void run() {
		System.out.println(getName() + "加入了战斗...");

		System.out.println(getName() + "说：哈哈，你程大爷来了，小逗比们，还不快快受降...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 旁白
		String str = "人物介绍：程咬金,济州东阿县人，骁勇善战，善于骑马用槊击刺，主兵器：三板斧（挖眼睛，挖鼻子，掏耳朵）。唐朝开国大将，凌烟阁二十四功臣之一。";
		String[] text = str.split("");

		for (int i = 0; i < text.length; i++) {
			System.out.print(text[i]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 10连击
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + "实在太过生猛，左突右杀，击杀隋军，如入无人之境，杀得隋军闻风丧胆....");
		}

		System.out.println(getName() + "结束了战斗...");
	}
}