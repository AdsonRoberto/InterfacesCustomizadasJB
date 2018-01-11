package org.jb.codegen.util;

public class ClassUtil {
	public static Class<?> loadClass(String className) throws ClassNotFoundException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();//Thread.currentThread().getContextClassLoader();
		Class<?> c = cl.loadClass(className);//Class.forName(className + "." + oldClassName, false, classLoader);
		return c;
	}
}
