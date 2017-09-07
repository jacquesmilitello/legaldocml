package io.legaldocml.xpath.impl;

import io.legaldocml.xpath.XPath2Lexer;
import io.legaldocml.xpath.XPath2Parser;
import io.legaldocml.xpath.XPath2ParserBaseListener;
import io.legaldocml.xpath.XPathException;
import io.legaldocml.xpath.XPathExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class XPathExpressionBuilder extends XPath2ParserBaseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(XPathExpressionBuilder.class);

    private final XPathQnameBuilder qnameBuilder = new XPathQnameBuilder();
    private QueryBuilder queryBuilder;
    private QueryPredicateBuilder queryPredicateBuilder;

    private final XPathImpl xpath;
    private final XPath2Parser parser;

    public boolean predicate = false;


    public XPathExpressionBuilder(XPathImpl xpath, XPath2Parser parser) {
        this.xpath = xpath;
        this.parser = parser;
    }

    @Override
    public void exitIntegerLiteral(XPath2Parser.IntegerLiteralContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("exitIntegerLiteral [{}]", ctx.getText());
        }
        if (this.predicate) {
            this.queryPredicateBuilder.append(Integer.valueOf(ctx.getText()));
            return;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public void enterPredicate(XPath2Parser.PredicateContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("enterPredicate [{}]",this.predicate);
        }
        this.predicate = true;
        queryPredicateBuilder = new QueryPredicateBuilder();
    }

    @Override
    public void exitPredicate(XPath2Parser.PredicateContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("exitPredicate [{}] -> [{}]", this.predicate, ctx.expr().getText());
        }
        this.predicate = false;

        this.queryBuilder.append(this.queryPredicateBuilder);
        this.queryPredicateBuilder = null;

//      /  this.qnameBuilder.append();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enterPathExpr(XPath2Parser.PathExprContext ctx) {
        if (XPath2Lexer.FORWARD_SLASH == ctx.start.getType()) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("absolute path");
            }
            this.queryBuilder = QueryBuilder.absolute(this.xpath);
            return;
        }

        if (this.predicate) {
            // continue because => prediate
            return;
        }

        throw new UnsupportedOperationException(""+ctx.start.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exitQName(XPath2Parser.QNameContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("qname [{}]", ctx.getText());
        }

        try {
            queryBuilder.append(qnameBuilder.build());
        } catch (XPathException cause) {
            //  ctx.exception= new FailedPredicateException(parser);
        }
        qnameBuilder.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exitNCName(XPath2Parser.NCNameContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("NCName [{}]", ctx.getText());
        }
        qnameBuilder.append(ctx.getText());
    }

    public XPathExpression build() {
        return new XPathExpressionImpl(queryBuilder.getSteps());

    }
}
