package io.legaldocml.diff.text;

import static io.legaldocml.diff.text.MyersDiff.buildPath;
import static io.legaldocml.diff.text.MyersDiff.buildRevision;

import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TextDiffs {

	private TextDiffs() {
	}

	public static List<Change> diff(char[] original, char[] revised) {
		return buildRevision(buildPath(original, revised));
	}
	
}
