package com.axel.jvm.oom;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/20
 */
public class HeapOOM {

	static class OOMObject{
	}

	public static void main(String[] args) {
		List<OOMObject> list = Lists.newArrayList();
		while (true) {
			list.add(new OOMObject());
		}
	}
}
