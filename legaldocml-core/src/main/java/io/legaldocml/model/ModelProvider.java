package io.legaldocml.model;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ModelProvider {

    Language language(String code);

    Country country(String code);

}