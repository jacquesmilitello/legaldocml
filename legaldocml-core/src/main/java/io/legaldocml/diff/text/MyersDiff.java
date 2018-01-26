package io.legaldocml.diff.text;

import java.util.ArrayList;
import java.util.List;

/**
 * Original : http://www.xmailserver.org/diff2.pdf
 * 
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class MyersDiff {

	private MyersDiff() {}
	
	static PathNode buildPath(char[] orig, char[] rev) {
		
		int n = orig.length;
		int m = rev.length;

		int max = n + m + 1;
		int size = 1 + 2 * max;
		int middle = size / 2;
		final PathNode diagonal[] = new PathNode[size];

		diagonal[middle + 1] = new PathNode(0, -1, null, true, true);
		for (int d = 0; d < max; d++) {
			for (int k = -d; k <= d; k += 2) {
				int kmiddle = middle + k;
				int kplus = kmiddle + 1;
				int kminus = kmiddle - 1;
				PathNode prev;
				int i;

				if ((k == -d) || (k != d && diagonal[kminus].i < diagonal[kplus].i)) {
					i = diagonal[kplus].i;
					prev = diagonal[kplus];
				} else {
					i = diagonal[kminus].i + 1;
					prev = diagonal[kminus];
				}

				diagonal[kminus] = null; // no longer used

				int j = i - k;

				PathNode node = new PathNode(i, j, prev, false, false);

				while (i < n && j < m && orig[i] == rev[j]) {
					i++;
					j++;
				}

				if (i != node.i) {
					node = new PathNode(i, j, node, true, false);
				}

				diagonal[kmiddle] = node;

				if (i >= n && j >= m) {
					return diagonal[kmiddle];
				}
			}
			diagonal[middle + d - 1] = null;
		}
		throw new IllegalStateException();
	}

	static List<Change> buildRevision(PathNode actualPath) {

		PathNode path = actualPath;
		List<Change> changes = new ArrayList<>();
		if (path.isSnake()) {
			path = path.prev;
		}
		while (path != null && path.prev != null && path.prev.j >= 0) {
			if (path.isSnake()) {
				throw new IllegalStateException();
			}
			int i = path.i;
			int j = path.j;

			path = path.prev;
			int ianchor = path.i;
			int janchor = path.j;

			if (ianchor == i && janchor != j) {
				changes.add(new Change(ChangeType.INSERT, ianchor, i, janchor, j));
			} else if (ianchor != i && janchor == j) {
				changes.add(new Change(ChangeType.DELETE, ianchor, i, janchor, j));
			} else {
				changes.add(new Change(ChangeType.CHANGE, ianchor, i, janchor, j));
			}
		
			if (path.isSnake()) {
				path = path.prev;
			}
		}
		return changes;
	}

}