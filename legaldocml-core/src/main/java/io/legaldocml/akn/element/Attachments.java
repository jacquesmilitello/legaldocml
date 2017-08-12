package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 *
 * <pre>
 *  &lt;xsd:element name="attachments"&gt;
 *  	&lt;xsd:complexType&gt;
 *      	&lt;xsd:sequence&gt;
 *          	&lt;xsd:element ref="componentRef" minOccurs="1" maxOccurs="unbounded"/&gt;
 *          &lt;xsd:sequence&gt;
 *      	&lt;xsd:attributeGroup ref="coreopt"/&gt;
 * 		&lt;xsd:complexType&gt;
 * 	&lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Attachments extends AknObject {

    /**
     * XML tag element name.
     */
    String ELEMENT = "attachments";

    /**
     * {@inheritDoc}
     */
    @Override
    default String name() {
        return ELEMENT;
    }

}