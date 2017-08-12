package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the opinion element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="opinionType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="dissenting"/&gt;
 * 		 &lt;xsd:enumeration text="agreeing"/&gt;
 * 		 &lt;xsd:enumeration text="null"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
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