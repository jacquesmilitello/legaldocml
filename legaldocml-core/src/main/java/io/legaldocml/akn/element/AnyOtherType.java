package io.legaldocml.akn.element;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.element.Attributes.ADDRESS_HREF;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.util.CharArrays.immutable;
import static io.legaldocml.util.Iterables.iterable;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.other.ExternalElementWithNS;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.Module;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.ListIterable;

/**
 * The complex type anyOtherType defines an open content model for elements that may use elements from other namespaces.
 * <p>
 * <pre>
 *   <xsd:complexType name="anyOtherType">
 * 	   <xsd:choice>
 * 	     <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="core"/>
 * 	   <xsd:attributeGroup ref="idopt"/>
 * 	   <xsd:attributeGroup ref="linkopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AnyOtherType extends AbstractIdCore implements LinkOpt, Core, IdOpt {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AnyOtherType.class);

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(HREF, attributeGetterSetter4Uri(HREF, getFieldOffset(AnyOtherType.class, "href")))
                .build();
    }

    private Uri href;

    private AknList<AnyOtherTypeElement> others;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setHref(Uri href) {
        this.href = href;
    }


    public final ListIterable<AnyOtherTypeElement> getChilds() {
        return iterable(this.others);
    }

    public final void add(AnyOtherTypeElement el) {
        if (this.others == null) {
            this.others = new AknList<>(new AnyOtherTypeElement[4]);
        }
        this.others.add(el);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        Core.super.write(writer);
        IdOpt.super.write(writer);
        if (this.href != null) {
            writer.writeAttribute(ADDRESS_HREF, 4, this.href.getChars());
        }
        if (this.others != null) {
            this.others.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qName = reader.getQName();
        super.read(reader);
        reader.nextStartOrEndElement();

        if (qName.equals(reader.getQName())) {
            // ok end of parent tag -> to extension -> return;
            return;
        }

        this.others = new AknList<>(new AnyOtherTypeElement[4]);

        while (!qName.equals(reader.getQName())) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("External Element prefix [{}]", reader.getQName().getPrefix());
            }

            CharArray array = reader.getNamespaces().get(immutable(reader.getQName().getPrefix()));
            Module module = reader.<AkomaNtosoContext>getContext().getModule(array);

            AnyOtherTypeElement element;
            if (module == null) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("inline declation of namespace -> [{}]", reader.getQName());
                }
                element = new ExternalElementWithNS(reader.getQName(), reader.getNamespaces().get(immutable(reader.getQName().getPrefix())));
            } else {
                element = module.element(reader.getQName().getLocalName(), AnyOtherTypeElement.class).get();

            }
            element.read(reader);
            this.others.add(element);


            reader.nextStartOrEndElement();
        }

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

    }

}