package io.legaldocml.xpath;

import io.legaldocml.xpath.antlr.XpathListener;
import io.legaldocml.xpath.antlr.XpathParser;
import io.legaldocml.xpath.query.XpathQueryBuilder;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


final class XpathPlanListener implements XpathListener {

    private final XpathBuilder builder = new XpathBuilder();

    private XpathQueryBuilder queryBuilder;

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XpathPlanListener.class);

    public XpathBuilder getBuilder() {
        return this.builder;
    }

    @Override
    public void enterMain(XpathParser.MainContext ctx) {

    }

    @Override
    public void exitMain(XpathParser.MainContext ctx) {

    }

    @Override
    public void enterLocationPath(XpathParser.LocationPathContext ctx) {
    }

    @Override
    public void exitLocationPath(XpathParser.LocationPathContext ctx) {

    }

    @Override
    public void enterAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        queryBuilder = builder.newAbsoluteQuery();
    }

    @Override
    public void exitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {

    }

    @Override
    public void enterRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
    }

    @Override
    public void exitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {

    }

    @Override
    public void enterStep(XpathParser.StepContext ctx) {
    }

    @Override
    public void exitStep(XpathParser.StepContext ctx) {

    }

    @Override
    public void enterAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
    }

    @Override
    public void exitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {

    }

    @Override
    public void enterNodeTest(XpathParser.NodeTestContext ctx) {
    }

    @Override
    public void exitNodeTest(XpathParser.NodeTestContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("NodeTestContext [{}]", ctx.getText());
        }
        queryBuilder.node(ctx.getText());
    }

    @Override
    public void enterPredicate(XpathParser.PredicateContext ctx) {
    }

    @Override
    public void exitPredicate(XpathParser.PredicateContext ctx) {

    }

    @Override
    public void enterAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
    }

    @Override
    public void exitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {

    }

    @Override
    public void enterExpr(XpathParser.ExprContext ctx) {
    }

    @Override
    public void exitExpr(XpathParser.ExprContext ctx) {

    }

    @Override
    public void enterPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
    }

    @Override
    public void exitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {

    }

    @Override
    public void enterFunctionCall(XpathParser.FunctionCallContext ctx) {
    }

    @Override
    public void exitFunctionCall(XpathParser.FunctionCallContext ctx) {

    }

    @Override
    public void enterUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {

    }

    @Override
    public void exitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {

    }

    @Override
    public void enterPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {

    }

    @Override
    public void exitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {

    }

    @Override
    public void enterFilterExpr(XpathParser.FilterExprContext ctx) {

    }

    @Override
    public void exitFilterExpr(XpathParser.FilterExprContext ctx) {

    }

    @Override
    public void enterOrExpr(XpathParser.OrExprContext ctx) {

    }

    @Override
    public void exitOrExpr(XpathParser.OrExprContext ctx) {

    }

    @Override
    public void enterAndExpr(XpathParser.AndExprContext ctx) {

    }

    @Override
    public void exitAndExpr(XpathParser.AndExprContext ctx) {

    }

    @Override
    public void enterEqualityExpr(XpathParser.EqualityExprContext ctx) {

    }

    @Override
    public void exitEqualityExpr(XpathParser.EqualityExprContext ctx) {

    }

    @Override
    public void enterRelationalExpr(XpathParser.RelationalExprContext ctx) {

    }

    @Override
    public void exitRelationalExpr(XpathParser.RelationalExprContext ctx) {

    }

    @Override
    public void enterAdditiveExpr(XpathParser.AdditiveExprContext ctx) {

    }

    @Override
    public void exitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {

    }

    @Override
    public void enterMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {

    }

    @Override
    public void exitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {

    }

    @Override
    public void enterUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {

    }

    @Override
    public void exitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {

    }

    @Override
    public void enterQName(XpathParser.QNameContext ctx) {
    }

    @Override
    public void exitQName(XpathParser.QNameContext ctx) {

    }

    @Override
    public void enterFunctionName(XpathParser.FunctionNameContext ctx) {

    }

    @Override
    public void exitFunctionName(XpathParser.FunctionNameContext ctx) {

    }

    @Override
    public void enterVariableReference(XpathParser.VariableReferenceContext ctx) {

    }

    @Override
    public void exitVariableReference(XpathParser.VariableReferenceContext ctx) {

    }

    @Override
    public void enterNameTest(XpathParser.NameTestContext ctx) {
    }

    @Override
    public void exitNameTest(XpathParser.NameTestContext ctx) {

    }

    @Override
    public void enterNCName(XpathParser.NCNameContext ctx) {
    }

    @Override
    public void exitNCName(XpathParser.NCNameContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("TerminalNode [{}]", node.getText());
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
