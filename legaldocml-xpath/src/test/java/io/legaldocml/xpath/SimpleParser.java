package io.legaldocml.xpath;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

public class SimpleParser {

    @Test
    public void testSimple() {

        compile("/akoma/test/meta");

    }

    public static void compile(String xpath) {
        XPath2Lexer lexer = new XPath2Lexer(CharStreams.fromString(xpath));
        XPath2 parser = new XPath2(new CommonTokenStream(lexer));
        XPath2Listener listener = new XPath2BaseListener() {
            @Override
            public void visitTerminal(TerminalNode node) {
                super.visitTerminal(node);
                System.out.println("==>" + node.getText());
                new Exception().printStackTrace();
            }



            @Override
            public void exitStringLiteral(XPath2.StringLiteralContext ctx) {
                super.exitStringLiteral(ctx);
                System.out.println("==>" + ctx.STRING_LITERAL());
                new Exception().printStackTrace();
            }
        };
        parser.addParseListener(listener);
        parser.xPath();
    }
}
