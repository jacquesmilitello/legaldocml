package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DebateBusinessBuilder extends BusinessBuilder<Debate> {

    public DebateBusinessBuilder() {
        super(Debate.class);
    }

    @Override
    protected Debate newDocumenyType() {
        return new Debate();
    }

}
