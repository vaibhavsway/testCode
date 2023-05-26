package com.amiqsec.ThreadPool;

public class Test implements Runnable {
	
	private int value ; 
	
	
	
	public Test(int value) {
		super();
		this.value = value;
	}



	public void run() {
		System.out.println("Task " + value + " is running.");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Task " + value + " Completed");
	}

}
