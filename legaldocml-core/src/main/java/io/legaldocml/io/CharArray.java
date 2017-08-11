package io.legaldocml.io;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CharArray extends CharSequence {

	/**
	 * @return a new char array
	 */
	char[] value();

	/**
	 * @return a new char array
	 */
	char[] raw();

    CharArray prefix(int prefixNS);
}