package io.legaldocml.xpath.eval;

import io.legaldocml.xpath.cerebro.CerebroLink;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public final class StepEval {

    private final CerebroLink link;
    private Predicate predicate;
    private Function<List, Object> postFunction;

    public StepEval(CerebroLink link) {
        this.link = link;
    }

    public Object eval(Object param) {

        Object result = link.apply(param);

        if (result instanceof List) {
            if (this.postFunction != null) {
                return this.postFunction.apply((List) result);
            } else  {

            }
        }

        if (this.predicate == null) {
            return result;
        }

        if (predicate.test(result)) {
            return result;
        } else {
            return null;
        }
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public void setPostFunction(Function<List, Object> postFunction) {
        this.postFunction = postFunction;
    }
}
