package io.legaldocml.model;

/**
 * We use the ISO 639-1:2002 and ISO 639-1:2002, Codes for the representation of names of languages — Part 1: Alpha-2
 * code, is the first part of the ISO 639 series of international standards for language codes. Part 1 covers the
 * registration of two-letter codes. There are 136 two-letter codes registered. The registered codes cover the world's
 * major languages.
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Language {

    /**
     * @return the 2 digits representation of the language.
     */
    String getCode();

    /**
     * @return the 3 digits representation of the language.
     */
    String getBibliographic();

    /**
     * @return the 3 digits representation of the language.
     */
    String getTerminology();

    /**
     * @return the english name of the language.
     */
    String getName();

}
