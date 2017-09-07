package io.legaldocml.xpath.impl;

import io.legaldocml.xpath.XPath;
import io.legaldocml.xpath.eval.StepEval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class QueryBuilder {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);

    private final XPath xPath;

    protected QueryBuilder(XPath xPath) {
        this.xPath = xPath;
    }

    protected final XPath getXPath() {
        return xPath;
    }

    public abstract void append(XPathQname qname);

    public static QueryBuilder absolute(XPath xPath) {
        return new AbsoluteQueryBuilder(xPath);
    }

    public abstract void append(QueryPredicateBuilder queryPredicateBuilder);

    abstract List<StepEval> getSteps();


}
