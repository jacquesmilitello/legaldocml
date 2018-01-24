package io.legaldocml.util;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ListIterable<T> extends Iterable<T> {

    int size();

    default Stream<T> stream() {
    	return StreamSupport.stream(this.spliterator(), false);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(iterator(), size(), Spliterator.SIZED);
    }
    
}
