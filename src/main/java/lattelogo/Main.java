package lattelogo;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String toStringASCII(ParseTree tree) {

        StringBuilder builder = new StringBuilder();

        walk(tree, builder);

        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    private static void walk(ParseTree tree, StringBuilder builder) {

        List<ParseTree> firstStack = new ArrayList<>();
        firstStack.add(tree);

        List<List<ParseTree>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            }
            else {
                tree = childStack.remove(0);

                String node = tree.getClass().getSimpleName().replace("Context", "");
                node = Character.toLowerCase(node.charAt(0)) + node.substring(1);

                String indent = "";

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                }

                builder.append(indent)
                        .append(childStack.isEmpty() ? "'- " : "|- ")
                        .append(node.startsWith("terminal") ? tree.getText() : node)
                        .append("\n");

                if (tree.getChildCount() > 0) {
                    List<ParseTree> children = new ArrayList<>();
                    for (int i = 0; i < tree.getChildCount(); i++) {
                        children.add(tree.getChild(i));
                    }
                    childListStack.add(children);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String source =
                "to p1 :n             \n" +
                "  output :n / 2      \n" +
                "end                  \n" +
                "                     \n" +
                "to p2 :n1 :n2        \n" +
                "  output :n1 + :n2   \n" +
                "end                  \n" +
                "                     \n" +
                "print p1 p2 10 20    \n";

        UCBLogoLexer lexer = new UCBLogoLexer(new ANTLRInputStream(source));
        UCBLogoParser parser = new UCBLogoParser(new CommonTokenStream(lexer));

        System.out.println("The parse tree before scanning ahead to resolve procedures:\n");
        System.out.println(toStringASCII(parser.parse()));

        System.out.println("-----------------------------------------------------------");

        ParseTree tree = new UCBLogoParser(source).parse();

        System.out.println("The parse tree before scanning ahead to resolve procedures:\n");
        System.out.println(toStringASCII(tree));
    }
}