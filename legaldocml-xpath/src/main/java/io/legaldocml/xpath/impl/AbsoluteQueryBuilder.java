package io.legaldocml.xpath.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.module.Module;
import io.legaldocml.xpath.XPath;
import io.legaldocml.xpath.cerebro.Cerebro;
import io.legaldocml.xpath.eval.StepEval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

final class AbsoluteQueryBuilder extends QueryBuilder {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbsoluteQueryBuilder.class);

    private final List<StepEval> stetps = new ArrayList<>();

    private Class<? extends AknObject> parent;

    protected AbsoluteQueryBuilder(XPath xPath) {
        super(xPath);
    }

    public void append(XPathQname qname) {

        Module module = getXPath().getModule(qname.getPrefix());

        //Class<? extends AknObject> aknClass = module.element(qname.getLocalname());

        //if (LOGGER.isDebugEnabled()) {
         //   LOGGER.debug("append({}) -> [{}]", qname, aknClass);
       // }

        //if (parent == null) {
         //   this.parent = aknClass;
         //   return;
        //}

        //this.stetps.add(new StepEval(Cerebro.getLink(this.parent, aknClass)));
        //this.parent = aknClass;

    }

    @Override
    public void append(QueryPredicateBuilder queryPredicateBuilder) {
        StepEval last = this.stetps.get(this.stetps.size() -1);
        queryPredicateBuilder.buildOn(last);
    }

    @Override
    List<StepEval> getSteps() {
        return this.stetps;
    }

}
