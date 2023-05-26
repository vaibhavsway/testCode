package com.amiqsec.printer;

import java.util.LinkedList;
import java.util.Random;

public class Producer implements Runnable {

	private int initial;
	private int diff;
	private LinkedList<Integer> output = null;

	public Producer(int initial, int diff, LinkedList<Integer> output) {
		super();
		this.initial = initial;
		this.diff = diff;
		this.output = output;
	}

	@Override
	public void run() {

		Random r = new Random();
		int current_value = initial;
		
		while (true) {
			try {
				Thread.sleep(r.nextInt(5000));

				synchronized (output) {
					output.push(current_value);
					current_value += diff;
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
