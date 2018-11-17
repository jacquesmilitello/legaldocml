package io.legaldocml.xliff.type;

/**
 * <pre>
 *   <xs:simpleType name="yesNo">
 *     <xs:restriction base="xs:string">
 *       <xs:enumeration value="yes"/>
 *       <xs:enumeration value="no"/>
 *     </xs:restriction>
 *   </xs:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum YesNo {

    YES(true),
    NO(false);

    private final boolean value;

    private final String toString;

    private YesNo(boolean value) {
        this.value = value;
        this.toString = name().toLowerCase();
    }

    public boolean getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString;
    }
}
