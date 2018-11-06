package com.gmail.kramarenko104.goldtransfer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Loader implements Callable<Boolean> {

	private String name;
	private static int maxAmountToCarry;
	private GoldHeap goldHeap;
	private Wagon wagon;
	private final static int WORK_SPEED = 2;

	public Loader(String name, GoldHeap goldHeap, Wagon wagon, int maxAmountToCarry) {
		this.name = name;
		this.goldHeap = goldHeap;
		this.wagon = wagon;
		Loader.maxAmountToCarry = maxAmountToCarry;
	}

	public Boolean call() {
		int minedGold = 0;
		int workTime = 0;

		// loader is mining the gold
		minedGold = goldHeap.mininingGold(maxAmountToCarry);
		wagon.setGoldCarried(minedGold);
		System.out.println(name + " got " + minedGold + " kg of gold");

		// loader puts gold into the wagon with the speed 2 kg/sec
		workTime = minedGold / WORK_SPEED;
		System.out.println(name + " start to load gold into wagon, it will took " + workTime + " sec");

		try {
			TimeUnit.SECONDS.sleep(workTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + " finishes to load " + minedGold + " kg of gold, transfer wagon to Transporter =======> ");
		return true;

	}
}
