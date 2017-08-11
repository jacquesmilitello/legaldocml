package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * The complex type basehierarchy is not used by any element, but is derived by other types to contain the basic
 * structure of hierarchical elements.
 * <p/>
 * <pre>
 *   <xsd:complexType name="basehierarchy">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="num" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="heading" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="subheading" minOccurs="0" maxOccurs="1"/>
 *     </xsd:sequence>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BaseHierarchy extends AknObject {


    Num getNum();

    void setNum(Num num);

    Heading getHeading();

    void setHeading(Heading heading);

    SubHeading getSubheading();

    void setSubheading(SubHeading subheading);


}