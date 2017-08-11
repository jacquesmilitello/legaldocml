package io.legaldocml.util;

import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.visitor.AknVisitor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ToStringVisitor implements AknVisitor {

    //    private VisitorStatus status = VisitorStatus.CONTINUE;
//
    private StringBuilder builder = new StringBuilder();

    //
//    @Override
//    public VisitorStatus status() {
//        return this.status;
//    }
//
    public void visit(StringInlineCM stringInlineCM) {
        builder.append(stringInlineCM.toString());
    }

    //
//
//
    @Override
    public String toString() {
        return builder.toString();
    }
//
//    public void reset() {
//        this.builder.setLength(0);
//    }
}
