package io.legaldocml.business.impl;

import io.legaldocml.business.AknIdentifier;
import io.legaldocml.util.ToStringBuilder;

import java.util.Objects;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class DefaultAknIdentifier extends AknIdentifier {

    private final String workPart;
    private final String expressionPart;
    private final String manifestationPart;
    private final String separator;



    public DefaultAknIdentifier(String workPart, String expressionPart, String manifestationPart, String separator) {
        this.workPart = workPart;
        this.expressionPart = expressionPart;
        this.manifestationPart = manifestationPart;
        this.separator = separator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String work() {
        return workPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String workPart() {
        return workPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String expression() {
        return new StringBuilder().append(workPart).append(separator).append(expressionPart).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String expressionPart() {
        return this.expressionPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String manifestation() {
        return new StringBuilder().append(workPart).append(separator).append(expressionPart).append(separator).append(manifestationPart).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String manifestationPart() {
        return this.manifestationPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doEquals(AknIdentifier aknIdentifier) {
        return Objects.equals(this.workPart, aknIdentifier.workPart()) &&
                Objects.equals(this.expressionPart, aknIdentifier.expressionPart()) &&
                Objects.equals(this.manifestationPart, aknIdentifier.manifestationPart());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("work", this.workPart)
                .append("expression", this.expressionPart)
                .append("manifestation", this.manifestationPart)
                .toString();
    }

}
