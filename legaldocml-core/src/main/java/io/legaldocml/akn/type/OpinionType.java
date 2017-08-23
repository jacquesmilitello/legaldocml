package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the opinion element.
 *
 * <pre>
 *   <xsd:simpleType name="opinionType">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="dissenting"/>
 * 		 <xsd:enumeration text="agreeing"/>
 * 		 <xsd:enumeration text="null"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum OpinionType {

    dissenting("dissenting"), agreeing("agreeing"), _null("null");

    private final String value;

    OpinionType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

}