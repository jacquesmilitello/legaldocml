package io.legaldocml.iso;

import com.google.common.collect.ImmutableMap;

/**
 * We use the ISO 639-1:2002 and ISO 639-1:2002, Codes for the representation of names of languages — Part 1: Alpha-2
 * code, is the first part of the ISO 639 series of international standards for language codes. Part 1 covers the
 * registration of two-letter codes. There are 136 two-letter codes registered. The registered codes cover the world's
 * major languages.
 */
public final class Language {

    /**
     * Code for this language.
     */
    private final String code;

    private Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Language)) {
            return false;
        }
        return this.code.equals(((Language) obj).code);
    }

    public boolean isCodeEquals(String code) {
        return this.code.equals(code);
    }

    /**
     * French.
     */
    public static final Language FR = new Language("fr");

    /**
     * English.
     */
    public static final Language EN = new Language("en");

    /**
     * All Official languages plus the XM.
     */
    private static final ImmutableMap<String, Language> ALL;

    static {
        ALL = ImmutableMap.<String, Language>builder()
                .put(FR.getCode(), FR)
                .put(EN.getCode(), EN)
                .build();
    }

    public static Language valueOf(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Language code must me not null !!");
        }
        if (code.length() != 2) {
            throw new IllegalArgumentException("Language code [" + code + "] must me 2 chars !!");
        }

        Language result = ALL.get(code.toLowerCase());

        if (result != null) {
            return result;
        }

        return new Language(code.toLowerCase());
    }
}
