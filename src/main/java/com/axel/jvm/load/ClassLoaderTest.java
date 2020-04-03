package com.axel.jvm.load;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/3
 */
@Slf4j
public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {

				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					log.info("", e);
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object object = myLoader.loadClass("com.axel.jvm.load.ClassLoaderTest").newInstance();
		System.out.println(object.getClass());
		System.out.println(object instanceof com.axel.jvm.load.ClassLoaderTest);
	}
}
