package io.legaldocml.akn.element;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.element.Groups.TLCs;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.docRefs;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

import java.io.IOException;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Iterables;
import io.legaldocml.util.ListIterable;
import io.legaldocml.util.ToStringBuilder;

/**
 * The complex type refItems is a list of types of references used in the
 * references section.
 * <p>
 * 
 * <pre>
 *   <xsd:complexType name="refItems">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *       <xsd:group ref="docRefs"/>
 *       <xsd:group ref="TLCs"/>
 *     <xsd:choice>
 *     <xsd:attributeGroup ref="source"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class RefItems implements Source {

	private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

	private static final ImmutableMap<String, Supplier<RefItem>> ELEMS;

	static {
		ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
				.put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(RefItems.class, "source"))).build();

		ELEMS = ImmutableMap.<String, Supplier<RefItem>>builder().putAll(convertSuper(docRefs()))
				.putAll(convertSuper(TLCs())).build();
	}

	// Mandatory (min 1)
	private final AknList<RefItem> refItems = new AknList<RefItem>(new RefItem[6]);

	// Mandatory
	private AgentRef source;

	/**
	 * {@inheritDoc}
	 */
	public AgentRef getSource() {
		return this.source;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSource(AgentRef source) {
		this.source = source;
	}

	public final ListIterable<RefItem> getRefItems() {
		return Iterables.iterable(this.refItems);
	}

	public final void add(RefItem item) {
		this.refItems.add(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(XmlWriter writer) throws IOException {
		writeSource(writer, this);
		this.refItems.write(writer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(XmlReader reader) {
		reader.forEach(this, ATTRIBUTE_CONSUMER);
		XmlReaderHelper.read(reader, this.refItems, ELEMS);
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
	public void accept(AknVisitor visitor) {
		this.refItems.accept(visitor);
	}

	 /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        builder.append(SOURCE, this.source);
        return builder.toString();
    }
    
}