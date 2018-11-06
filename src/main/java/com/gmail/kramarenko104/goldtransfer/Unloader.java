package com.gmail.kramarenko104.goldtransfer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Unloader implements Callable<Integer> {

	private String name;
	private Wagon wagon;
	private final static int WORK_SPEED = 3;

	public Unloader(String name, Wagon wagon) {
		this.name = name;
		this.wagon = wagon;
	}

	public Integer call() {
		int workTime = 0;
		
		// unloader gets gold from the wagon with the speed 3 kg/sec
		workTime = wagon.getGoldCarried() / WORK_SPEED;
		System.out.println(name + " gets the wagon with " + wagon.getGoldCarried() + " kg of gold and starts to unload it...it will take " + workTime + " sec");

		try {
			TimeUnit.SECONDS.sleep(workTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + " finishes to unload the wagon, transfer wagon to Loader back =======> ");
		return wagon.getGoldCarried();
	}
}
