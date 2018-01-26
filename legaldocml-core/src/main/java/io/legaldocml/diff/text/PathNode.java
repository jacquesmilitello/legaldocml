package io.legaldocml.diff.text;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class PathNode {

	/**
	 * Position in the original sequence.
	 */
	public final int i;
	/**
	 * Position in the revised sequence.
	 */
	public final int j;
	/**
	 * The previous node in the path.
	 */
	public final PathNode prev;

	public final boolean snake;

	public final boolean bootstrap;

	public PathNode(int i, int j, PathNode prev, boolean snake, boolean bootstrap) {
		this.i = i;
		this.j = j;
		if (snake) {
			this.prev = prev;
		} else {
			this.prev = prev == null ? null : prev.previousSnake();
		}
		this.bootstrap = bootstrap;
		this.snake = snake;
	}

	public boolean isSnake() {
		return snake;
	}

	public boolean isBootstrap() {
		return bootstrap;
	}

	public final PathNode previousSnake() {
		if (isBootstrap()) {
			return null;
		}
		if (!isSnake() && prev != null) {
			return prev.previousSnake();
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder("[");
		PathNode node = this;
		while (node != null) {
			buf.append("(");
			buf.append(Integer.toString(node.i));
			buf.append(",");
			buf.append(Integer.toString(node.j));
			buf.append(")");
			node = node.prev;
		}
		buf.append("]");
		return buf.toString();
	}
}
