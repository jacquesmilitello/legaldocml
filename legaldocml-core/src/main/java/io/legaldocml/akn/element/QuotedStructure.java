package io.legaldocml.akn.element;

/**
 * The element quotedStructure is a popup element containing a full structure proposed as an insertion or a replacement.
 * Use attribute for when quotedStructure is used in a mmod or rmod to point to the id of the corresponding refelement.
 *
 * <pre>
 * 	&lt;xsd:element name="quotedStructure"&gt;
 * 		&lt;xsd:complexType&gt;
 * 			&lt;xsd:complexContent&gt;
 * 				&lt;xsd:extension base="popupStructure"&gt;
 * 					&lt;xsd:attribute name="for" type="xsd:anyURI" /&gt;
 * 				&lt;xsd:extension&gt;
 * 			&lt;xsd:complexContent&gt;
 * 		&lt;xsd:complexType&gt;
 * 	&lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface QuotedStructure extends ModTypeItem {

    /**
     * XML Tag element name.
     */
    String ELEMENT = "quotedStructure";

    /**
     * {@inheritDoc}
     */
    @Override
    default String name() {
        return ELEMENT;
    }

}