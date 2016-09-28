package com.bit2016.network.thread;

public class AlphabetThread extends Thread{

	@Override
	public void run() {
		for(char c = 'a'; c<='z'; c++){
			System.out.println(c);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
