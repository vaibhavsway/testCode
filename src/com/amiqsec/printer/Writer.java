package com.amiqsec.printer;

import java.util.LinkedList;
import java.util.Random;

public class Writer implements Runnable {
	
	private LinkedList<Integer> output = null ;

	public Writer(LinkedList<Integer> output) {
		super();
		this.output = output;
	}

	@Override
	public void run() {
		
		Random r = new Random() ;
		
		while(true) {
			
			try {
				Thread.sleep(r.nextInt(4000));
				
				synchronized(output) {
					if(!output.isEmpty()) {
						System.out.println(output.pop());
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	

}
