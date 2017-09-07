package io.legaldocml.xpath.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.xpath.XPathExpression;
import io.legaldocml.xpath.eval.StepEval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

final class XPathExpressionImpl implements XPathExpression {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XPathExpressionImpl.class);

    private final List<StepEval> steps;

    XPathExpressionImpl(List<StepEval> steps) {
        this.steps = steps;
    }


    @Override
    public <T extends DocumentType> Optional<Object> evaluate(AkomaNtoso<T> akomaNtoso) {
        List<StepEval> evals = this.steps;
        Object result = akomaNtoso;
        for (int i = 0; i < evals.size(); i++) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("In  -> [{}] -> [{}] -> [{}]", i, result, steps.get(i));
            }
            result = evals.get(i).eval(result);

            if (result == null) {
               return Optional.empty();
            }
        }
        return Optional.of(result);
    }

}
