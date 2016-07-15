package com.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleMicroBenchMark {
	
	public static void main(String[] args) {
		long synchTime = test(new SynTest());
		long lockTime = test(new LockingTest());
		System.out.println("syn:"+synchTime);
		System.out.println("lock:"+lockTime);
		System.out.printf("lock/syn = %1$.3f",(double)lockTime/(double)synchTime);
	}
	
	static long test(Incrementable incr){
		long start = System.nanoTime();
		for(int i=0; i< 10000000L; i++){
			incr.increment();
		}
		return System.nanoTime() - start;
	}

}

abstract class Incrementable{
	protected long counter = 0;
	public abstract void increment();
}
class SynTest extends Incrementable{

	@Override
	public synchronized void increment() {
          counter++;		
	}
	
}

class LockingTest extends Incrementable{
   private Lock  lock = new ReentrantLock();
	@Override
	public void increment() {
		lock.lock();
		try{
			counter++;
		}finally{
			lock.unlock();
		}
		
	}
	
}