package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * The complex type basehierarchy is not used by any element, but is derived by other types to contain the basic
 * structure of hierarchical elements.
 *
 * <pre>
 *   &lt;xsd:complexType name="basehierarchy"&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	     &lt;xsd:element ref="num" minOccurs="0" maxOccurs="1"/&gt;
 * 		 &lt;xsd:element ref="heading" minOccurs="0" maxOccurs="1"/&gt;
 * 		 &lt;xsd:element ref="subheading" minOccurs="0" maxOccurs="1"/&gt;
 *     &lt;xsd:sequence&gt;
 *   &lt;xsd:complexType&gt;
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