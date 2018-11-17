package io.legaldocml.xliff.type;

import com.google.common.collect.ImmutableMap;

import java.util.function.Function;

/**
 * <pre>
 *   <xs:simpleType name="stateType">
 *     <xs:restriction base="xs:string">
 *       <xs:enumeration value="initial"/>
 *       <xs:enumeration value="translated"/>
 *       <xs:enumeration value="reviewed"/>
 *       <xs:enumeration value="final"/>
 *     </xs:restriction>
 *   </xs:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum StateType {

    INITIAL,
    TRANSLATED,
    REVIEWED,
    FINAL;

    private static final ImmutableMap<String,StateType> VALUES;

    static {
        VALUES = ImmutableMap.<String, StateType>builder()
                .put(INITIAL.toString, INITIAL)
                .put(TRANSLATED.toString, TRANSLATED)
                .put(REVIEWED.toString, REVIEWED)
                .put(FINAL.toString, FINAL)
                .build();
    }

    public static final Function<String, StateType>  FUNCTION = string -> {
        StateType stateType = VALUES.get(string);
        return stateType;
    };

    private final String toString;

    private StateType() {
        this.toString = name().toLowerCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString;
    }

}
