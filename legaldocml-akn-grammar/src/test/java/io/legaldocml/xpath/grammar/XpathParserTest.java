package io.legaldocml.xpath.grammar;

import io.legaldocml.xpath.antlr.XpathBaseVisitor;
import io.legaldocml.xpath.antlr.XpathLexer;
import io.legaldocml.xpath.antlr.XpathParser;
import io.legaldocml.xpath.antlr.XpathVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

public class XpathParserTest {


    @Test
    public void test() {

        //XpathLexer lexer = new XpathLexer(CharStreams.fromString("/akomaNtoso/Body/article/text()"));
        XpathLexer lexer = new XpathLexer(CharStreams.fromString("/bookstore/book[price>35]/title"));
        XpathParser parser = new XpathParser(new CommonTokenStream(lexer));


        XpathBaseVisitor visitor = new XpathBaseVisitor() {
            @Override
            public Object visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
                System.out.println("RelativeLocationPathContext -->" + ctx.getText());
                return super.visitRelativeLocationPath(ctx);
            }

            @Override
            public Object visitMain(XpathParser.MainContext ctx) {
                System.out.println("MainContext -->" + ctx.getText());
                return super.visitMain(ctx);
            }

            @Override
            public Object visitLocationPath(XpathParser.LocationPathContext ctx) {
                System.out.println("LocationPathContext -->" + ctx.getText());
                return super.visitLocationPath(ctx);
            }

            @Override
            public Object visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
                System.out.println("AbsoluteLocationPathNorootContext -->" + ctx.getText());
                return super.visitAbsoluteLocationPathNoroot(ctx);
            }

            @Override
            public Object visitStep(XpathParser.StepContext ctx) {
                System.out.println("StepContext -->" + ctx.getText());
                return super.visitStep(ctx);
            }

            @Override
            public Object visitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
                System.out.println("AxisSpecifierContext -->" + ctx);
                return super.visitAxisSpecifier(ctx);
            }

            @Override
            public Object visitNodeTest(XpathParser.NodeTestContext ctx) {
                System.out.println("NodeTestContext -->" + ctx.getText());
                return super.visitNodeTest(ctx);
            }

            @Override
            public Object visitPredicate(XpathParser.PredicateContext ctx) {
                System.out.println("PredicateContext -->" + ctx.getText());
                return super.visitPredicate(ctx);
            }

            @Override
            public Object visitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
                System.out.println("AbbreviatedStepContext -->" + ctx.getText());
                return super.visitAbbreviatedStep(ctx);
            }

            @Override
            public Object visitExpr(XpathParser.ExprContext ctx) {
                System.out.println("ExprContext -->" + ctx.getText());
                return super.visitExpr(ctx);
            }

            @Override
            public Object visitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
                System.out.println("PrimaryExprContext -->" + ctx.getText());
                return super.visitPrimaryExpr(ctx);
            }

            @Override
            public Object visitFunctionCall(XpathParser.FunctionCallContext ctx) {
                System.out.println("FunctionCallContext -->" + ctx.getText());
                return super.visitFunctionCall(ctx);
            }

            @Override
            public Object visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
                System.out.println("UnionExprNoRootContext -->" + ctx.getText());
                return super.visitUnionExprNoRoot(ctx);
            }

            @Override
            public Object visitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
                System.out.println("PathExprNoRootContext -->" + ctx.getText());
                return super.visitPathExprNoRoot(ctx);
            }

            @Override
            public Object visitFilterExpr(XpathParser.FilterExprContext ctx) {
                System.out.println("FilterExprContext -->" + ctx.getText());
                return super.visitFilterExpr(ctx);
            }

            @Override
            public Object visitOrExpr(XpathParser.OrExprContext ctx) {
                System.out.println("OrExprContext -->" + ctx.getText());
                return super.visitOrExpr(ctx);
            }

            @Override
            public Object visitAndExpr(XpathParser.AndExprContext ctx) {
                System.out.println("AndExprContext -->" + ctx.getText());
                return super.visitAndExpr(ctx);
            }

            @Override
            public Object visitEqualityExpr(XpathParser.EqualityExprContext ctx) {
                System.out.println("EqualityExprContext -->" + ctx.getText());
                return super.visitEqualityExpr(ctx);
            }

            @Override
            public Object visitRelationalExpr(XpathParser.RelationalExprContext ctx) {
                System.out.println("RelationalExprContext -->" + ctx.getText());
                return super.visitRelationalExpr(ctx);
            }

            @Override
            public Object visitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
                System.out.println("AdditiveExprContext -->" + ctx.getText());
                return super.visitAdditiveExpr(ctx);
            }

            @Override
            public Object visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
                System.out.println("MultiplicativeExprContext -->" + ctx.getText());
                return super.visitMultiplicativeExpr(ctx);
            }

            @Override
            public Object visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
                System.out.println("UnaryExprNoRootContext -->" + ctx.getText());
                return super.visitUnaryExprNoRoot(ctx);
            }

            @Override
            public Object visitQName(XpathParser.QNameContext ctx) {
                System.out.println("QNameContext -->" + ctx.getText());
                return super.visitQName(ctx);
            }

            @Override
            public Object visitFunctionName(XpathParser.FunctionNameContext ctx) {
                System.out.println("FunctionNameContext -->" + ctx.getText());
                return super.visitFunctionName(ctx);
            }

            @Override
            public Object visitVariableReference(XpathParser.VariableReferenceContext ctx) {
                System.out.println("VariableReferenceContext -->" + ctx.getText());
                return super.visitVariableReference(ctx);
            }

            @Override
            public Object visitNameTest(XpathParser.NameTestContext ctx) {
                System.out.println("NameTestContext -->" + ctx.getText());
                return super.visitNameTest(ctx);
            }

            @Override
            public Object visitNCName(XpathParser.NCNameContext ctx) {
                System.out.println("NCNameContext -->" + ctx.getText());
                return super.visitNCName(ctx);
            }
        };
        visitor.visit(parser.locationPath());
    }
}
