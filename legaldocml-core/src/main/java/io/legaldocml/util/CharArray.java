package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CharArray extends CharSequence {

	/**
	 * @return a new char array
	 */
	char[] value();

    CharArray subSequence(int start, int end);

	boolean stringEquals(String name);
}