package com.amiqsec.ThreadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {

	private ConcurrentLinkedQueue<Runnable> runnables;
	private List<ThreadpoolThread> threads;
	private AtomicBoolean execute;

	private class ThreadpoolException extends RuntimeException {
		public ThreadpoolException(Throwable cause) {
			super(cause);
		}

		public ThreadpoolException(String s) {
			super(s);
		}
	}

	public ThreadPool(int threadLimit) {
		this.runnables = new ConcurrentLinkedQueue<Runnable>();
		this.threads = new LinkedList<ThreadpoolThread>();
		this.execute = new AtomicBoolean(true);
		for (int i = 0; i < threadLimit; i++) {
			ThreadpoolThread thread = new ThreadpoolThread(this.execute, this.runnables);
			thread.start();
			this.threads.add(thread);
		}

	}

	public void execute(Runnable runnable) {
		if (this.execute.get()) {
			runnables.add(runnable);
		} else {
			throw new ThreadpoolException("Threadpool Terminate ");
		}
	}

	public void terminate() {
		runnables.clear();
		this.execute.set(false);
	}

	public void awaitTermination(long time) {
		long starttime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - starttime) <= time) {
			boolean flag = true;
			for (ThreadpoolThread thread : threads) {
				if (thread.isAlive()) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return;
			}
		}
		
		terminate();
		System.out.println("Thread runtime exceeded await time , Terminating Thread ");

	}
	
//	public void forceshutdown() {
//		for (ThreadpoolThread thread : threads) {
//			if (thread.isAlive()) {
//				thread.interrupt(); 
//			}
//		}
//		
//	}

}
