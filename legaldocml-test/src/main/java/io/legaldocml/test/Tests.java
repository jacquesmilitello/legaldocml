package io.legaldocml.test;

import org.junit.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Tests {

	private Tests() {
	}

	/**
	 * Based on
	 * <a href="http://stackoverflow.com/questions/4520216/how-to-add-test-coverage-to-a-private-constructor/10872497#10872497">this SO
	 * answer</a>
	 * 
	 * @param clazz
	 * @throws Exception
	 */
	public static void assertUtilClassIsWellDefined(Class<?> clazz) throws Exception {
        assertSingletonClassIsWellDefined(clazz);
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
		for (final Method method : clazz.getMethods()) {
			if (!Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass().equals(clazz)) {
				Assert.fail("there exists a non-static method:" + method);
			}
		}
	}

	public static void assertSingletonClassIsWellDefined(Class<?> clazz) throws Exception {
		Assert.assertTrue("class must be final", Modifier.isFinal(clazz.getModifiers()));
        Assert.assertEquals("There must be only one constructor", 1, clazz.getDeclaredConstructors().length);
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        if (constructor.isAccessible() || !Modifier.isPrivate(constructor.getModifiers())) {
            Assert.fail("constructor is not private");
        }
	}

}
