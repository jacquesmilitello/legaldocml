package io.legaldocml.schematron.model;

import io.legaldocml.util.CharArray;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface SchAttributeGetterSetter extends BiConsumer<SchObject, CharArray>, Function<SchObject, Object> {
}
