package com.gmail.kramarenko104.goldtransfer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Transporter implements Callable<Boolean> {

	private String name;
	private Wagon wagon;
	private final static int WORK_TIME = 3;

	public Transporter(String name, Wagon wagon) {
		this.name = name;
		this.wagon = wagon;
	}

	public Boolean call() {
			// Transporter carries the wagon during 3 sec
			System.out.println(name + " is going to carry the wagon with " + wagon.getGoldCarried() + " kg of gold during " + WORK_TIME + " sec");
			try {
				TimeUnit.SECONDS.sleep(WORK_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(name + " finishes to carry the wagon, transfer wagon to Unloader =======> ");
			return true;

	}
}
