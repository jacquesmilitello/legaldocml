package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.NoWhiteSpace;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Id extends AknObject {

    /**
     * Attribute name "id".
     */
    String ATTRIBUTE_ID = "id";
    /**
     * Attribute name "evolvingId".
     */
    String ATTRIBUTE_EVOLVING_ID = "evolvingId";

    /**
     * Attribute name "eId".
     */
    String ATTRIBUTE_EID = "eId";

    /**
     * Attribute name "wId".
     */
    String ATTRIBUTE_WID = "wId";

    /**
     * Attribute name "GUID".
     */
    String ATTRIBUTE_GUID = "GUID";

    /**
     * @return
     */
    @Deprecated
    String getId();

    @Deprecated
    void setId(String id);

    @Deprecated
    String getEvolvingId();

    @Deprecated
    void setEvolvingId(String evolvingId);

    NoWhiteSpace getEid();

    void setEid(NoWhiteSpace eid);

    NoWhiteSpace getWid();

    void setWid(NoWhiteSpace wid);

    NoWhiteSpace getGUID();

    void setGUID(NoWhiteSpace guid);

}