package io.legaldocml.xpath.impl;

import io.legaldocml.xpath.eval.StepEval;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

final class QueryPredicateBuilder {

    private Predicate predicate;
    Function<List, Object> function;


    public void append(Integer index) {
        function = list -> list.get(index);
    }

    public void buildOn(StepEval last) {

        if (this.predicate != null) {
            last.setPredicate(this.predicate);
        }

        if (this.function != null) {
            last.setPostFunction(this.function);
        }

    }
}
