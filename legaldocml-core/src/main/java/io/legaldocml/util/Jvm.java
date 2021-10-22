package io.legaldocml.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class Jvm {

	private static final int JVM_VERSION;
	private static final boolean IS_JAVA_9_OR_PLUS;
	
	private Jvm() {
	}
	
	static {
		JVM_VERSION = getVersion();
		IS_JAVA_9_OR_PLUS = JVM_VERSION > 8; 
	}
	
	public static boolean isJava8() {
		return JVM_VERSION == 8;
	}
	
	public static boolean isJava9OrPlus() {
		return IS_JAVA_9_OR_PLUS;
	}

	private static int getVersion() {
		try {
			final Method method = Runtime.class.getDeclaredMethod("version");
			Object version = method.invoke(Runtime.getRuntime());
			Class<?> clz = Class.forName("java.lang.Runtime$Version");
			return (Integer) clz.getDeclaredMethod("major").invoke(version);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
				| ClassNotFoundException e) {
			// ignore and fall exception
		}
		return Integer.parseInt(Runtime.class.getPackage().getSpecificationVersion().split("\\.")[1]);
	}

}
