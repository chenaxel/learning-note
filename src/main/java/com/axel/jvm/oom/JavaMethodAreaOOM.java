package com.axel.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/23
 */
public class JavaMethodAreaOOM {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(OOMObject.class);
		enhancer.setUseCache(false);
		enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
		enhancer.create();
	}

	static class OOMObject {
	}
}
