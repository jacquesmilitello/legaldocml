package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Strings {

	/**
	 * Empty String.
	 */
	public static final String EMPTY = "";

	/**
	 * 'null' String.
	 */
	public static final String NULL = "null";

	/**
	 * A String for a space character.
	 */
	public static final String SPACE = " ";

	/**
	 * Hide constructor.
	 */
	private Strings() {
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * Strings.isBlank(null)      = true
	 * Strings.isBlank("")        = true
	 * Strings.isBlank(" ")       = true
	 * Strings.isBlank("bob")     = false
	 * Strings.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a CharSequence is empty ("") or null.
	 * 
	 * <pre>
	 * Strings.isEmpty(null)      = true
	 * Strings.isEmpty("")        = true
	 * Strings.isEmpty(" ")       = false
	 * Strings.isEmpty("bob")     = false
	 * Strings.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null.
	 * @return {@code true} if the CharSequence is empty or null.
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

}
