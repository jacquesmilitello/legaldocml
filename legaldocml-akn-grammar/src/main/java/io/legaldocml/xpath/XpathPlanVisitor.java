package io.legaldocml.xpath;

import io.legaldocml.xpath.antlr.XpathParser;
import io.legaldocml.xpath.antlr.XpathVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


final class XpathPlanVisitor implements XpathVisitor<XpathPlan> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XpathPlanVisitor.class);

    @Override
    public  XpathPlan visitMain(XpathParser.MainContext ctx) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.MainContext [{}]", ctx.getText());
        }

        XpathPlan result = new XpathPlan();

        int n = ctx.getChildCount();
        for (int i=0; i<n; i++) {

            ParseTree c = ctx.getChild(i);
            XpathPlan childResult = c.accept(this);

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Xpath.MainContext.Child [{}]", childResult);
            }

            //result = aggregateResult(result, childResult);
        }

        return result;
    }


    @Override
    public XpathPlan visitExpr(XpathParser.ExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.ExprContext [{}]", ctx.getText());
        }

        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitOrExpr(XpathParser.OrExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.OrExprContext [{}]", ctx.getText());
        }

        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitAndExpr(XpathParser.AndExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.AndExprContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitEqualityExpr(XpathParser.EqualityExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.EqualityExprContext [{}]", ctx.getText());
        }

        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitRelationalExpr(XpathParser.RelationalExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.RelationalExprContext [{}]", ctx.getText());
        }
          return visitChildren(ctx);
    }


    @Override
    public XpathPlan visitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.AdditiveExprContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.MultiplicativeExprContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.UnaryExprNoRootContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.UnionExprNoRootContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.PathExprNoRootContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitLocationPath(XpathParser.LocationPathContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LocationPathContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.RelativeLocationPathContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.AbsoluteLocationPathNorootContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitStep(XpathParser.StepContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.StepContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.AxisSpecifierContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public XpathPlan visitNodeTest(XpathParser.NodeTestContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.NodeTestContext [{}]", ctx.getText());
        }
        //return visitChildren(ctx);
        return null;
    }

    @Override
    public XpathPlan visitNameTest(XpathParser.NameTestContext ctx) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Xpath.NodeTestContext [{}]", ctx.getText());
        }
        return visitChildren(ctx);
    }


























    @Override
    public XpathPlan visitPredicate(XpathParser.PredicateContext ctx) {
        System.out.println("PredicateContext -->" + ctx.getText());
        return null;
    }

    @Override
    public XpathPlan visitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
        System.out.println("AbbreviatedStepContext -->" + ctx.getText());
        return null;
    }



    @Override
    public XpathPlan visitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
        System.out.println("PrimaryExprContext -->" + ctx.getText());
        return null;
    }

    @Override
    public XpathPlan visitFunctionCall(XpathParser.FunctionCallContext ctx) {
        System.out.println("FunctionCallContext -->" + ctx.getText());
        return null;
    }





    @Override
    public XpathPlan visitFilterExpr(XpathParser.FilterExprContext ctx) {
        System.out.println("FilterExprContext -->" + ctx.getText());
        return null;
    }











    @Override
    public XpathPlan visitQName(XpathParser.QNameContext ctx) {
        System.out.println("QNameContext -->" + ctx.getText());
        return null;
    }

    @Override
    public XpathPlan visitFunctionName(XpathParser.FunctionNameContext ctx) {
        System.out.println("FunctionNameContext -->" + ctx.getText());
        return null;
    }

    @Override
    public XpathPlan visitVariableReference(XpathParser.VariableReferenceContext ctx) {
        System.out.println("VariableReferenceContext -->" + ctx.getText());
        return null;
    }

    @Override
    public XpathPlan visitNCName(XpathParser.NCNameContext ctx) {
        System.out.println("NCNameContext -->" + ctx.getText());
        return null;
    }



    @Override
    public XpathPlan visit(ParseTree tree) {
        System.out.println("ParseTree -->" +tree);
        return tree.accept(this);
    }

    public XpathPlan visitChildren(RuleNode node) {

        XpathPlan result = new XpathPlan();
        int n = node.getChildCount();
        for (int i=0; i<n; i++) {
            ParseTree c = node.getChild(i);
            XpathPlan childResult = c.accept(this);

            // result = aggregateResult(result, childResult);
        }

        return result;
    }

    @Override
    public XpathPlan visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public XpathPlan visitErrorNode(ErrorNode node) {
        System.out.println("ErrorNode -->" +node);
        return null;
    }



}
