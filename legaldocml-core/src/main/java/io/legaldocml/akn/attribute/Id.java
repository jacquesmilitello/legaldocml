package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.NoWhiteSpace;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Id extends AknObject {

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