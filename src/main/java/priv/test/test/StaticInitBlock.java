package priv.test.test;

public class StaticInitBlock {

	public static void main(String[] args) {
		new StaticInitBlock();
	}
	
	//自定义变量  
    public int num=0;  
    
	//非静态初始化块  
    {  
        System.out.println("我是非静态初始化块..");  
        this.num=1;  
    }  
	
    //构造函数  
    public StaticInitBlock() {  
        System.out.println("我是构造方法..");  
    }  
    
	//静态初始化块 
	static {
		System.out.println("我是静态初始化块...");
		
		
		new Thread(new Runnable() {
			
			int times = 0;
			
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println(times++);
						Thread.sleep(1000);
						
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}
}
