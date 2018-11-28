package io.legaldocml.diff.text;

import org.junit.jupiter.api.Test;

import java.util.List;

class MyersDiffTest {

	@Test
	void test() {

		char[] dest = "Ceci est un de grand chage test hel".toCharArray();
		char[] source = "ceci est un petit test".toCharArray();
		List<Change> changes = TextDiffs.diff(source, dest);

		StringBuilder builder = new StringBuilder();
		int j = 0;
		for (int i = changes.size() - 1; i >= 0; i--) {
			Change change = changes.get(i);
			if (change.getStartRevised() > j) {
				builder.append(new String(dest, j, change.getStartRevised() - j));
			}
			
			builder.append("<" + change.getType() + ">");
			if (change.getType() == ChangeType.DELETE) {
				builder.append(new String(source, change.getStartOriginal(), change.getEndOriginal() - change.getStartOriginal()));
			} else {
				builder.append(new String(dest, change.getStartRevised(), change.getEndRevised() - change.getStartRevised()));	
			}
			builder.append("</" + change.getType() + ">");
			j = change.getEndRevised();

		}
	}
}