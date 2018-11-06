package com.gmail.kramarenko104.goldtransfer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
есть куча денег == 100 кг
есть тележка на 6 кг
есть грузчик loader, перевозчик transporter, разгрузчик unloader

грузчик загружает телегу со скоростью 2 кг/сек
транспортер везет телегу 3 сек
разгрузчик разгружает телегу со скоростью 3 кг/сек
кучу перевезти полностью на другое место

РАБОТАТЬ ДОЛЖЕН ТОЛЬКО один поток, остальные ждут
*/

public class RunApp {

	private static final int WAGON_MAX_VOLUME = 6;

	public RunApp() {

		GoldHeap goldHeap = new GoldHeap(100);
		Wagon wagon = new Wagon();
		int transferredGold = 0;

		ExecutorService ex = Executors.newFixedThreadPool(1);

		Loader loader = new Loader("Loader", goldHeap, wagon, WAGON_MAX_VOLUME);
		Transporter transporter = new Transporter("Transporter", wagon);
		Unloader unloader = new Unloader("Unloader", wagon);

		Future<Boolean> fLoader = null;
		Future<Boolean> fTransporter = null;
		Future<Integer> fUnloader = null;

		System.out.println("=============START===================");
		int loop = 0;
		
		// loader is mining gold while there is gold in GoldHeap
		while (goldHeap.getGoldAmount() > 0) {

			System.out.println("----------- " + ++loop + " loop --------------");
			
			// Loader starts to work
			fLoader = ex.submit(loader);
			try {
				fLoader.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// Transporter starts to work
			fTransporter = ex.submit(transporter);
			try {
				fTransporter.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// Unloader starts to work
			fUnloader = ex.submit(unloader);
			try {
				transferredGold = fUnloader.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("On this iteration was transferred " + transferredGold + " kg of gold.....");
			System.out.println("Remains " + goldHeap.getGoldAmount() + " kg of gold in GoldHeap......");
		}

		ex.shutdown();
		System.out.println("=============END===================");
		System.out.println("There isn't any gold in GoldHeap, ALL WORK is DONE !!!!! ");
		
	}

	public static void main(String[] args) {
		new RunApp();
	}
}
