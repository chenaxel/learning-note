package com.axel.concurrency.thread.atomic;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/29
 */
public class CriticalSections {

	public static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator
				pm1 = new PairManipulator(pman1),
				pm2 = new PairManipulator(pman2);

		PairChecker
				pcheck1 = new PairChecker(pman1),
				pcheck2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}

	public static void main(String[] args) {
		PairManager
				pman1 = new PairManager1(),
				pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}
}

@Data
class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public Pair() {
		this(0, 0);
	}

	@Override
	public String toString() {
		return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	public class PairValuesNotEqualException extends RuntimeException {

		public PairValuesNotEqualException() {
			super("pair values not equla:" + Pair.this);
		}
	}

	public void checkState() {
		if (x != y) {
			throw new PairValuesNotEqualException();
		}
	}
}

abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair pair = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

	public synchronized Pair getPair() {
		return new Pair(pair.getX(), pair.getY());
	}

	protected void store(Pair pair) {
		storage.add(pair);
		try {
			TimeUnit.MICROSECONDS.sleep(50);
		} catch (InterruptedException ignore) {
		}
	}

	public abstract void increment();
}

class PairManager1 extends PairManager {
	@Override
	public synchronized void increment() {
		pair.incrementX();
		pair.incrementY();
		store(getPair());
	}
}

class PairManager2 extends PairManager {
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

class PairManipulator implements Runnable {

	private PairManager pairManager;

	public PairManipulator(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while (true) {
			pairManager.increment();
		}
	}

	@Override
	public String toString() {
		return "Pair: " + pairManager.getPair() +
				" checkCounter = " + pairManager.checkCounter.get();
	}
}

class PairChecker implements Runnable {

	private PairManager pairManager;

	public PairChecker(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while (true) {
			pairManager.checkCounter.incrementAndGet();
			pairManager.getPair().checkState();
		}
	}
}