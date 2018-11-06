package com.gmail.kramarenko104.goldtransfer;

public class GoldHeap {

	private volatile int goldAmount;

	public GoldHeap(int goldAmount) {
		this.goldAmount = goldAmount;
	}

	public int getGoldAmount() {
		return goldAmount;
	}

	public int mininingGold(int miningAmount) {
		int tmp = 0;
		if (goldAmount > 0) {
			if (miningAmount <= goldAmount) {
				goldAmount -= miningAmount;
				return miningAmount;
			}
			else {
				tmp = goldAmount;
				goldAmount = 0;
				return tmp;
			}
		}
		return 0;
			
	}

}
