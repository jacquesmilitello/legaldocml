package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknCloneContext;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.util.ToStringBuilder;

import java.util.Objects;

import static io.legaldocml.akn.AknAttributes.EID;
import static io.legaldocml.akn.AknAttributes.EVOLVING_ID;
import static io.legaldocml.akn.AknAttributes.GUID;
import static io.legaldocml.akn.AknAttributes.ID;
import static io.legaldocml.akn.AknAttributes.WID;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4NoWhiteSpace;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

@SuppressWarnings("deprecation")
public abstract class AbstractId implements AknObject, Id {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(ID, attributeGetterSetter4NoWhiteSpace(ID, getFieldOffset(AbstractId.class, "eId")))
                .put(EVOLVING_ID, attributeGetterSetter4NoWhiteSpace(EVOLVING_ID, getFieldOffset(AbstractId.class, "wId")))
                .put(EID, attributeGetterSetter4NoWhiteSpace(EID, getFieldOffset(AbstractId.class, "eId")))
                .put(WID, attributeGetterSetter4NoWhiteSpace(WID, getFieldOffset(AbstractId.class, "wId")))
                .put(GUID, attributeGetterSetter4NoWhiteSpace(GUID, getFieldOffset(AbstractId.class, "guid")))
                .build();
    }

    /**
     * CoreAttribute "id" for v2 or "eId" for "v3".
     */
    private NoWhiteSpace eId;

    /**
     * CoreAttribute "evolvingId" for v2 or "wid" for "v3".
     */
    private NoWhiteSpace wId;


    private NoWhiteSpace guid;

    /**
     * {@inheritDoc}
     */
    public final String getId() {
        if (this.eId == null) {
            return null;
        }
        return this.eId.toString();
    }

    /**
     * {@inheritDoc}
     */
    public final void setId(String id) {
        this.eId = NoWhiteSpace.valueOf(id);
    }

    /**
     * {@inheritDoc}
     */
    public final String getEvolvingId() {
        if (this.wId == null) {
            return null;
        }
        return this.wId.toString();
    }

    /**
     * {@inheritDoc}
     */
    public final void setEvolvingId(String evolvingId) {
        this.wId = NoWhiteSpace.valueOf(evolvingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NoWhiteSpace getEid() {
        return this.eId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEid(NoWhiteSpace eId) {
        this.eId = eId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NoWhiteSpace getWid() {
        return this.wId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWid(NoWhiteSpace wId) {
        this.wId = wId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NoWhiteSpace getGUID() {
        return this.guid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGUID(NoWhiteSpace guid) {
        this.guid = guid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        builder.append(EID, this.eId);
        builder.append(WID, this.wId);
        builder.append(GUID, this.guid);
        toString(builder);
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractId other = (AbstractId) obj;
        return Objects.equals(this.guid, other.guid) && Objects.equals(eId, other.eId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (this.guid != null) {
            return guid.hashCode();
        }
        if (this.eId != null) {
            return this.eId.hashCode();
        }
        return super.hashCode();
    }

    protected void toString(ToStringBuilder builder) {
    }

    protected final void clone(AbstractId id, AknCloneContext context) {
        id.setEid(this.eId);
        id.setWid(this.wId);
        id.setGUID(this.guid);
    }

}