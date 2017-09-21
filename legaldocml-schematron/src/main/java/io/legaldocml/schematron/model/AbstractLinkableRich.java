package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.schematron.model.attribute.Linkable;
import io.legaldocml.schematron.model.attribute.Rich;
import io.legaldocml.util.Uri;

import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

abstract class AbstractLinkableRich implements SchObject, Rich, Linkable {

    static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(SchAttributes.ROLE, biConsumerString(getFieldOffset(AbstractLinkableRich.class, "role")))
                .put(SchAttributes.SUBJECT, biConsumerString(getFieldOffset(AbstractLinkableRich.class, "subject")))
                .put(SchAttributes.ICON, biConsumerUri(getFieldOffset(AbstractLinkableRich.class, "icon")))
                .put(SchAttributes.SEE, biConsumerUri(getFieldOffset(AbstractLinkableRich.class, "see")))
                .put(SchAttributes.FPI, biConsumerUri(getFieldOffset(AbstractLinkableRich.class, "fpi")))
                .build();
    }

    private String role;
    private String subject;
    private Uri icon;
    private Uri see;
    private String fpi;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRole() {
        return this.role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubject() {
        return this.subject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uri getIcon() {
        return this.icon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIcon(Uri icon) {
        this.icon = icon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uri getSee() {
        return this.see;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSee(Uri see) {
        this.see = see;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFpi() {
        return this.fpi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFpi(String fpi) {
        this.fpi = fpi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
