package com.amiqsec.ThreadPool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadpoolThread extends Thread {

	private AtomicBoolean execute ; 
	private ConcurrentLinkedQueue<Runnable> runnables ;
	 
	
	public ThreadpoolThread(AtomicBoolean execute, ConcurrentLinkedQueue<Runnable> runnables) {
		this.execute = execute ;
		this.runnables = runnables ; 
		
	}
	
	@Override
	public void run() {
		try {
		while(execute.get() || !runnables.isEmpty()) {
			
			Runnable runnable ;
			while((runnable = runnables.poll()) != null) {
				runnable.run(); 
			}
		}
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
	}

}
