package io.legaldocml.akn.container;


import io.legaldocml.akn.group.HTMLblock;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLblockContainer extends Container {

    void add(HTMLblock block);

}
