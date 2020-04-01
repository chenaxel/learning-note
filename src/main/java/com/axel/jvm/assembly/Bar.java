package com.axel.jvm.assembly;

import java.util.ArrayList;
import java.util.List;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/31
 */
public class Bar {

	static class OOMObject {
		public byte[] placeHolder = new byte[64 * 1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
		System.gc();
	}
}
