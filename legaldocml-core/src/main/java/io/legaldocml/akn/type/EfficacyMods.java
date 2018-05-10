package io.legaldocml.akn.type;

import com.google.common.collect.ImmutableMap;

import java.util.function.Function;

/**
 * The simple type EfficacyMods lists all the types of modifications in efficacy as values for the type attribute of the
 * efficacyMod element.
 *
 * <pre>
 *   <xsd:simpleType name="EfficacyMods">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="entryIntoEfficacy"/>
 * 		 <xsd:enumeration text="endOfEfficacy"/>
 * 		 <xsd:enumeration text="inapplication"/>
 * 		 <xsd:enumeration text="retroactivity"/>
 * 		 <xsd:enumeration text="extraefficacy"/>
 * 		 <xsd:enumeration text="postponementOfEfficacy"/>
 * 		 <xsd:enumeration text="prorogationOfEfficacy"/>
 * 	   </xsd:restriction>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum EfficacyMods {

    ENTRY_INTO_EFFICACY("entryIntoEfficacy"),
    END_OF_EFFICACY("endOfEfficacy"),
    INAPPLICATION("inapplication"),
    RETROACTIVITY("retroactivity"),
    EXTRA_EFFICACY("extraefficacy"),
    POST_PONEMENT_OF_EFFICACY("postponementOfEfficacy"),
    PROROGATION_OF_EFFICACY("prorogationOfEfficacy");

    public static final ImmutableMap<String, EfficacyMods> VALUES = ImmutableMap.<String, EfficacyMods>builder()
            .put(ENTRY_INTO_EFFICACY.value(), ENTRY_INTO_EFFICACY)
            .put(END_OF_EFFICACY.value(), END_OF_EFFICACY)
            .put(INAPPLICATION.value(), INAPPLICATION)
            .put(RETROACTIVITY.value(), RETROACTIVITY)
            .put(EXTRA_EFFICACY.value(), EXTRA_EFFICACY)
            .put(POST_PONEMENT_OF_EFFICACY.value(), POST_PONEMENT_OF_EFFICACY)
            .put(PROROGATION_OF_EFFICACY.value(), PROROGATION_OF_EFFICACY)
            .build();

    private final String value;

    private EfficacyMods(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Function<String, EfficacyMods> function() {
        return VALUES::get;
    }

}