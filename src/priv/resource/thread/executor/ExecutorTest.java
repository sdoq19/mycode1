package priv.resource.thread.executor;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
	
	public static void main(String[] args) {
		Executor executor  = Executors.newFixedThreadPool(10);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.print("task over ");
			}
		};
		
		executor.execute(task);
		
		executor = Executors.newScheduledThreadPool(10);  
		ScheduledExecutorService scheduler = (ScheduledExecutorService) executor;  
		scheduler.scheduleAtFixedRate(task, 10, 10, TimeUnit.SECONDS);  
		
		Callable<Integer> func = new Callable<Integer>(){  
		    public Integer call() throws Exception {  
		        System.out.println("inside callable");  
		        Thread.sleep(1000);  
		        return new Integer(8);  
		    }         
		};        
		FutureTask<Integer> futureTask  = new FutureTask<Integer>(func);  
		Thread newThread = new Thread(futureTask);  
		newThread.start();  
		  
		try {  
		    System.out.println("blocking here");  
		    Integer result = futureTask.get();  
		    System.out.println(result);  
		} catch (InterruptedException ignored) {  
		} catch (ExecutionException ignored) {  
		}  
	}
	
	
}
