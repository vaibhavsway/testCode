package com.amiqsec.printer;

import java.util.LinkedList;

public class Application {
	
	public static void main(String[] args) {
		
		LinkedList<Integer> output = new LinkedList<>();
		
		Thread producer1 = new Thread(new Producer(3,3,output));
		Thread producer2 = new Thread(new Producer(5,5,output));
		
		Thread printer = new Thread(new Writer(output)) ; 
		
		producer1.start(); 
		producer2.start();
		
		printer.start(); 
	}

}
