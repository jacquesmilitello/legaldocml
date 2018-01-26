package io.legaldocml.diff.text;

import io.legaldocml.util.ToStringBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Change {

	private final ChangeType type;
	private final int startOriginal;
	private final int endOriginal;
	private final int startRevised;
	private final int endRevised;

	Change(ChangeType type, int startOriginal, int endOriginal, int startRevised, int endRevised) {
		this.type = type;
		this.startOriginal = startOriginal;
		this.endOriginal = endOriginal;
		this.startRevised = startRevised;
		this.endRevised = endRevised;
	}

	public ChangeType getType() {
		return type;
	}

	public int getStartOriginal() {
		return startOriginal;
	}

	public int getEndOriginal() {
		return endOriginal;
	}

	public int getStartRevised() {
		return startRevised;
	}

	public int getEndRevised() {
		return endRevised;
	}

	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(false)
			.append("type", type)
			.append("startOriginal", startOriginal)
			.append("endOriginal", endOriginal)
			.append("startRevised", startRevised)
			.append("endRevised", endRevised)
			.toString();
		// @formatter:on
	}

}
