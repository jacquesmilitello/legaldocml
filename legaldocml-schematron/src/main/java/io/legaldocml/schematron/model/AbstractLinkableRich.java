package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.schematron.model.attribute.Linkable;
import io.legaldocml.schematron.model.attribute.Rich;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.schematron.model.SchAttributes.FPI;
import static io.legaldocml.schematron.model.SchAttributes.ICON;
import static io.legaldocml.schematron.model.SchAttributes.ROLE;
import static io.legaldocml.schematron.model.SchAttributes.SEE;
import static io.legaldocml.schematron.model.SchAttributes.SUBJECT;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

abstract class AbstractLinkableRich implements SchObject, Rich, Linkable {

    static final ImmutableMap<String, AttributeGetterSetter<SchObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<SchObject>>builder()
                .put(ROLE, attributeGetterSetter4String(ROLE, getFieldOffset(AbstractLinkableRich.class, "role")))
                .put(SUBJECT, attributeGetterSetter4String(SUBJECT, getFieldOffset(AbstractLinkableRich.class, "subject")))
                .put(ICON, attributeGetterSetter4Uri(ICON, getFieldOffset(AbstractLinkableRich.class, "icon")))
                .put(SEE, attributeGetterSetter4Uri(SEE, getFieldOffset(AbstractLinkableRich.class, "see")))
                .put(FPI, attributeGetterSetter4Uri(FPI, getFieldOffset(AbstractLinkableRich.class, "fpi")))
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
    public ImmutableMap<String, AttributeGetterSetter<SchObject>> attributes() {
        return ATTRIBUTES;
    }

}
