package com.axel.concurrency.thread.terminate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/30
 */
public class OrnamentalGarden {

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance(i));
		}
		// Run for a while, then stop and collect the data:
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}

class Entrance implements Runnable {

	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<>();
	private int number = 0;
	private final int id;
	private static volatile boolean cancel = false;

	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}

	public static void cancel() {
		cancel = true;
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {

		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	@Override
	public void run() {
		while (!cancel) {
			synchronized (this) {
				++number;
			}
			System.out.println((this + " Total: " + count.increment()));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(("sleep interrupted"));
			}
		}
		System.out.println("Stopping " + this);
	}
}

class Count {
	private int count = 0;
	private Random random = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (random.nextBoolean()) {
			Thread.yield();
		}
		return count = ++temp;
	}

	public synchronized int value() {
		return count;
	}
}