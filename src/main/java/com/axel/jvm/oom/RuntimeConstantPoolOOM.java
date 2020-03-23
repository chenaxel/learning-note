package com.axel.jvm.oom;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/20
 * @blame Android Team
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
//		List<String> list = Lists.newArrayList();
//		int i = 0;
//		while (true) {
//			list.add(String.valueOf(i++).intern());
//		}
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}