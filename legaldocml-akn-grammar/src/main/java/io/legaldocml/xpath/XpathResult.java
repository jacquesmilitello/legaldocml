package io.legaldocml.xpath;

import java.util.function.Supplier;

/**
 * Created by jacques.militello on 07/06/2017.
 */
public interface XpathResult<T> extends Supplier<T>{

    XpathResultType type();

}
