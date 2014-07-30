package lattelogo.parser;

import lattelogo.lang.Value;
import lattelogo.node.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NodeVisitor extends UCBLogoBaseVisitor<Node> {

    @Override
    public Node visitScript(@NotNull UCBLogoParser.ScriptContext ctx) {

        ScriptNode node = new ScriptNode();

        for (UCBLogoParser.InstructionContext instruction : ctx.instruction()) {
            node.addInstruction(this.visit(instruction));
        }

        return node;
    }

    @Override
    public Node visitProcedureDefInstruction(@NotNull UCBLogoParser.ProcedureDefInstructionContext ctx) {
        return this.visit(ctx.procedure_def());
    }

    @Override
    public Node visitMacroDefInstruction(@NotNull UCBLogoParser.MacroDefInstructionContext ctx) {
        return this.visit(ctx.macro_def());
    }

    @Override
    public Node visitProcedureCallExtraInputInstruction(@NotNull UCBLogoParser.ProcedureCallExtraInputInstructionContext ctx) {
        return this.visit(ctx.procedure_call_extra_input());
    }

    @Override
    public Node visitProcedureCallInstruction(@NotNull UCBLogoParser.ProcedureCallInstructionContext ctx) {
        return this.visit(ctx.procedure_call());
    }

    @Override
    public Node visitProcedure_call(@NotNull UCBLogoParser.Procedure_callContext ctx) {

        String procedureName = ctx.NAME().getText();
        List<Node> expressions = new ArrayList<>();

        if (ctx.expressions() != null && ctx.expressions().expression() != null) {
            for (UCBLogoParser.ExpressionContext expressionContext : ctx.expressions().expression()) {
                expressions.add(this.visit(expressionContext));
            }
        }

        return new ProcedureCallNode(procedureName, expressions);
    }

    @Override
    public Node visitProcedure_call_extra_input(@NotNull UCBLogoParser.Procedure_call_extra_inputContext ctx) {

        String procedureName = ctx.NAME().getText();
        List<Node> expressions = new ArrayList<>();

        for (UCBLogoParser.ExpressionContext expressionContext : ctx.expression()) {
            expressions.add(this.visit(expressionContext));
        }

        return new ProcedureCallNode(procedureName, expressions);
    }

    @Override
    public Node visitProcedureCallExtraInputExpression(@NotNull UCBLogoParser.ProcedureCallExtraInputExpressionContext ctx) {
        return this.visit(ctx.procedure_call_extra_input());
    }

    @Override
    public Node visitWordExpression(@NotNull UCBLogoParser.WordExpressionContext ctx) {
        return new ValueNode(new Value(ctx.WORD().getText()));
    }

    @Override
    public Node visitFloatExpression(@NotNull UCBLogoParser.FloatExpressionContext ctx) {
        return new ValueNode(new Value(Double.valueOf(ctx.FLOAT().getText())));
    }

    @Override
    public Node visitIntExpression(@NotNull UCBLogoParser.IntExpressionContext ctx) {
        return new ValueNode(new Value(Long.valueOf(ctx.INT().getText())));
    }

    @Override
    public Node visitNameExpression(@NotNull UCBLogoParser.NameExpressionContext ctx) {
        return new LookupNameNode(ctx.NAME().getText());
    }

    @Override
    public Node visitQuotedWordExpression(@NotNull UCBLogoParser.QuotedWordExpressionContext ctx) {
        return new ValueNode(new Value(ctx.QUOTED_WORD().getText().substring(1)));
    }

    @Override
    public Node visitParensExpression(@NotNull UCBLogoParser.ParensExpressionContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public Node visitVariableExpression(@NotNull UCBLogoParser.VariableExpressionContext ctx) {
        return new LookupVariableNode(ctx.VARIABLE().getText().substring(1));
    }

    @Override
    public Node visitAddExpression(@NotNull UCBLogoParser.AddExpressionContext ctx) {
        return new AddNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitDivideExpression(@NotNull UCBLogoParser.DivideExpressionContext ctx) {
        return new DivideNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitSubtractExpression(@NotNull UCBLogoParser.SubtractExpressionContext ctx) {
        return new SubtractNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitMultiplyExpression(@NotNull UCBLogoParser.MultiplyExpressionContext ctx) {
        return new MultiplyNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitLessThanExpression(@NotNull UCBLogoParser.LessThanExpressionContext ctx) {
        return new LessThanNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitGreaterThanExpression(@NotNull UCBLogoParser.GreaterThanExpressionContext ctx) {
        return new GreaterThanNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitEqualsExpression(@NotNull UCBLogoParser.EqualsExpressionContext ctx) {
        return new EqualsNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitLessThanEqualsExpression(@NotNull UCBLogoParser.LessThanEqualsExpressionContext ctx) {
        return new LessThanEqualsNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitNotEqualsExpression(@NotNull UCBLogoParser.NotEqualsExpressionContext ctx) {
        return new NotEqualsNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitGreaterThanEqualsExpression(@NotNull UCBLogoParser.GreaterThanEqualsExpressionContext ctx) {
        return new GreaterThanEqualsNode(this.visit(ctx.expression(0)), this.visit(ctx.expression(1)));
    }

    @Override
    public Node visitUnaryMinusExpression(@NotNull UCBLogoParser.UnaryMinusExpressionContext ctx) {
        return new MultiplyNode(new ValueNode(new Value(-1)), this.visit(ctx.expression()));
    }

    @Override
    public Node visitProcedureCallExpression(@NotNull UCBLogoParser.ProcedureCallExpressionContext ctx) {
        return this.visit(ctx.procedure_call());
    }

    @Override
    public Node visitListExpression(@NotNull UCBLogoParser.ListExpressionContext ctx) {
        return this.visit(ctx.list());
    }

    @Override
    public Node visitList(@NotNull UCBLogoParser.ListContext ctx) {

        ListNode node = new ListNode();

        for (UCBLogoParser.List_itemContext listItem : ctx.list_item()) {
            node.addItem(this.visit(listItem));
        }

        return node;
    }

    @Override
    public Node visitListItemWord(@NotNull UCBLogoParser.ListItemWordContext ctx) {
        return new ValueNode(new Value(ctx.WORD().getText()));
    }

    @Override
    public Node visitListItemList(@NotNull UCBLogoParser.ListItemListContext ctx) {
        return super.visit(ctx.list());
    }

    @Override
    public Node visitArrayExpression(@NotNull UCBLogoParser.ArrayExpressionContext ctx) {
        return this.visit(ctx.array());
    }

    @Override
    public Node visitArray(@NotNull UCBLogoParser.ArrayContext ctx) {

        ArrayNode node = new ArrayNode();

        for (UCBLogoParser.Array_itemContext arrayItem : ctx.array_item()) {
            node.addItem(this.visit(arrayItem));
        }

        return node;
    }

    @Override
    public Node visitArrayItemWord(@NotNull UCBLogoParser.ArrayItemWordContext ctx) {
        return new ValueNode(new Value(ctx.WORD().getText()));
    }

    @Override
    public Node visitArrayItemArray(@NotNull UCBLogoParser.ArrayItemArrayContext ctx) {
        return super.visit(ctx.array());
    }

    // -------------------------------------------------------------------------------------------------------------- //

    @Override
    public Node visitBody_def(@NotNull UCBLogoParser.Body_defContext ctx) {
        throw new RuntimeException("TODO -> visitBody_def");
    }

    @Override
    public Node visitMacro_def(@NotNull UCBLogoParser.Macro_defContext ctx) {
        throw new RuntimeException("TODO -> visitMacro_def");
    }

    @Override
    public Node visitVariables(@NotNull UCBLogoParser.VariablesContext ctx) {
        throw new RuntimeException("TODO -> visitVariables");
    }

    @Override
    public Node visitExpressions(@NotNull UCBLogoParser.ExpressionsContext ctx) {
        throw new RuntimeException("TODO -> visitExpressions");
    }

    @Override
    public Node visitProcedure_def(@NotNull UCBLogoParser.Procedure_defContext ctx) {
        throw new RuntimeException("TODO -> visitProcedure_def");
    }

    @Override
    public Node visitBody_instruction(@NotNull UCBLogoParser.Body_instructionContext ctx) {
        throw new RuntimeException("TODO -> visitBody_instruction");
    }

    public static void main(String[] args) {

        String source = " make \"1 \"ONE print thing 2-1+\"0";

        UCBLogoLexer lexer = new UCBLogoLexer(new ANTLRInputStream(source));
        UCBLogoParser p = new UCBLogoParser(new CommonTokenStream(lexer));
        for (Token t : lexer.getAllTokens()) {
            System.out.printf("%-15s '%s'\n", p.getTokenNames()[t.getType()], t.getText());
        }

        System.out.println("-----------------------------------");

        UCBLogoParser parser = new UCBLogoParser(source);
        NodeVisitor visitor = new NodeVisitor();
        Node root = visitor.visit(parser.script());
        root.eval(null);
    }
}
