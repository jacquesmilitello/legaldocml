package io.legaldocml.akn.util;

import io.legaldocml.akn.element.P;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknListTest {


    @Test
    void emptyListCloneTest() {
        AknList<P> list = new AknList<>(new P[2]);
        AknList<P> clone = new AknList<>(new P[2]);
        list.clone(clone, P.class, null);
        assertNotSame(list, clone);
        assertEquals(clone.size(),0);
    }

    @Test
    void cloneTest() {
        AknList<P> list = new AknList<>(new P[2]);
        AknList<P> clone = new AknList<>(new P[2]);
        P p = new P();
        p.setEid(NoWhiteSpace.valueOf("123"));
        list.add(p);
        list.clone(clone, P.class, null);
        assertNotSame(list, clone);
        assertEquals(clone.size(),1);
        assertEquals(list.get(0).getEid(), clone.get(0).getEid());
        assertNotSame(list.get(0), clone.get(0));
    }

}
