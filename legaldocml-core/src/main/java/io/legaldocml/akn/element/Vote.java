package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.VoteAtts;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.VoteRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.CHOICE;
import static io.legaldocml.akn.AknElements.VOTE;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.element.Attributes.biConsumerVoteRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeVoteAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element vote is an inline that wraps either the name of the voter (when organized by choice) or the vote (when
 * organized by name) in a voting report.
 *
 * <pre>
 *   <xsd:element name="vote">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="voteAtts"/>
 * 		   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Vote extends InlineType implements VoteAtts, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_VOTE = Buffers.address(VOTE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AS, biConsumerRoleRef(AS, getFieldOffset(Vote.class, "as")))
                .put(BY, biConsumerAgentRef(BY, getFieldOffset(Vote.class, "by")))
                .put(CHOICE, biConsumerVoteRef(CHOICE, getFieldOffset(Vote.class, "choice")))
                .build();
    }

    private RoleRef as;
    private AgentRef by;
    private VoteRef choice;

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getBy() {
        return this.by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VoteRef getChoice() {
        return this.choice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChoice(VoteRef choice) {
        this.choice = choice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_VOTE, 4);
        writeVoteAtts(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_VOTE, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return VOTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}