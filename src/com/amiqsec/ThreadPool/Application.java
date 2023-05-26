package com.amiqsec.ThreadPool;



public class Application {

	public static void main(String[] args) {
		
		ThreadPool pool = new ThreadPool(3);
		 
        for (int i = 0; i < 10; i++) {
            Test task = new Test(i);
            pool.execute(task);
        }
        
        pool.awaitTermination(5000);
	}

}
