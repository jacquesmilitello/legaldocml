package io.legaldocml.akn.element;

/**
 * The element quotedStructure is a popup element containing a full structure proposed as an insertion or a replacement.
 * Use attribute for when quotedStructure is used in a mmod or rmod to point to the id of the corresponding refelement.
 * <p/>
 * <pre>
 * 	<xsd:element name="quotedStructure">
 * 		<xsd:complexType>
 * 			<xsd:complexContent>
 * 				<xsd:extension base="popupStructure">
 * 					<xsd:attribute name="for" type="xsd:anyURI" />
 * 				</xsd:extension>
 * 			</xsd:complexContent>
 * 		</xsd:complexType>
 * 	</xsd:element>
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