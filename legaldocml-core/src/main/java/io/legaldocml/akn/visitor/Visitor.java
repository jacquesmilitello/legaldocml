package io.legaldocml.akn.visitor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Visitor {

    default VisitorStatus status() {
        return VisitorStatus.CONTINUE;
    }

}
