package priv.resource.thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ConcurrentCalculator {

	private ExecutorService exec;
	private int cpuCoreNumber;
	private List<Future<Long>> tasks = new ArrayList<Future<Long>>();

	// 内部类
	class SumCalculator implements Callable<Long> {
		private int[] numbers;
		private int start;
		private int end;

		public SumCalculator(final int[] numbers, int start, int end) {
			this.numbers = numbers;
			this.start = start;
			this.end = end;
		}

		public Long call() throws Exception {
			Long sum = 0l;
			for (int i = start; i < end; i++) {
				sum += numbers[i];
			}
			return sum;
		}
	}

	public ConcurrentCalculator() {
		cpuCoreNumber = Runtime.getRuntime().availableProcessors();
		System.out.println("当前CPU核心数：" + cpuCoreNumber);
		exec = Executors.newFixedThreadPool(cpuCoreNumber);
	}

	public Long sum(final int[] numbers) {
		// 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
		for (int i = 0; i < cpuCoreNumber; i++) {
			int increment = numbers.length / cpuCoreNumber + 1;
			int start = increment * i;
			int end = increment * i + increment;
			if (end > numbers.length)
				end = numbers.length;
			SumCalculator subCalc = new SumCalculator(numbers, start, end);
			FutureTask<Long> task = new FutureTask<Long>(subCalc);
			tasks.add(task);
			if (!exec.isShutdown()) {
				exec.submit(task);
			}
		}
		return getResult();
	}

	/**
	 * 迭代每个只任务，获得部分和，相加返回
	 * 
	 * @return
	 */
	public Long getResult() {
		Long result = 0l;
		for (Future<Long> task : tasks) {
			try {
				// 如果计算未完成则阻塞
				Long subSum = task.get();
				result += subSum;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void close() {
		exec.shutdown();
	}

	// 单线程
	public long singleThread(int[] numbers) {
		long sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		int n = 10000000;
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = (int) (Math.random() * 1000);
		}

		ConcurrentCalculator calc = new ConcurrentCalculator();
		long begin = System.currentTimeMillis();
		Long sum = calc.sum(numbers);
		long end = System.currentTimeMillis();
		System.out.println("多线程：" + sum + ",use time:" + (end - begin));
		begin = System.currentTimeMillis();
		sum = calc.singleThread(numbers);
		end = System.currentTimeMillis();
		System.out.println("单线程：" + sum + ",use time:" + (end - begin));

		calc.close();
	}
}