package priv.resource.thread.callable;

import java.util.concurrent.*;

public class CallableAndFuture2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for (int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for (int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
} 