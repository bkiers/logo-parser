package lattelogo.parser;

import lattelogo.lang.NumberValue;
import lattelogo.lang.StringValue;
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
    public Node visitDivideExpression(@NotNull UCBLogoParser.DivideExpressionContext ctx) {
        return super.visitDivideExpression(ctx);
    }

    @Override
    public Node visitNameExpression(@NotNull UCBLogoParser.NameExpressionContext ctx) {
        return new LookupNode(ctx.NAME().getText());
    }

    @Override
    public Node visitAdditionExpression(@NotNull UCBLogoParser.AdditionExpressionContext ctx) {
        return super.visitAdditionExpression(ctx);
    }

    @Override
    public Node visitQuotedWordExpression(@NotNull UCBLogoParser.QuotedWordExpressionContext ctx) {
        return new ValueNode(new StringValue(ctx.QUOTED_WORD().getText().substring(1)));
    }

    @Override
    public Node visitProcedureCallExpression(@NotNull UCBLogoParser.ProcedureCallExpressionContext ctx) {
        return super.visitProcedureCallExpression(ctx);
    }

    @Override
    public Node visitGreaterThanEqualsExpression(@NotNull UCBLogoParser.GreaterThanEqualsExpressionContext ctx) {
        return super.visitGreaterThanEqualsExpression(ctx);
    }

    @Override
    public Node visitSubtractionExpression(@NotNull UCBLogoParser.SubtractionExpressionContext ctx) {
        return super.visitSubtractionExpression(ctx);
    }

    @Override
    public Node visitBody_def(@NotNull UCBLogoParser.Body_defContext ctx) {
        return super.visitBody_def(ctx);
    }

    @Override
    public Node visitGreaterThanExpression(@NotNull UCBLogoParser.GreaterThanExpressionContext ctx) {
        return super.visitGreaterThanExpression(ctx);
    }

    @Override
    public Node visitEqualsExpression(@NotNull UCBLogoParser.EqualsExpressionContext ctx) {
        return super.visitEqualsExpression(ctx);
    }

    @Override
    public Node visitMacro_def(@NotNull UCBLogoParser.Macro_defContext ctx) {
        return super.visitMacro_def(ctx);
    }

    @Override
    public Node visitLessThanExpression(@NotNull UCBLogoParser.LessThanExpressionContext ctx) {
        return super.visitLessThanExpression(ctx);
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
    public Node visitProcedureCallExtraInput(@NotNull UCBLogoParser.ProcedureCallExtraInputContext ctx) {
        return super.visitProcedureCallExtraInput(ctx);
    }

    @Override
    public Node visitLessThanEqualsExpression(@NotNull UCBLogoParser.LessThanEqualsExpressionContext ctx) {
        return super.visitLessThanEqualsExpression(ctx);
    }

    @Override
    public Node visitVariableExpression(@NotNull UCBLogoParser.VariableExpressionContext ctx) {
        return super.visitVariableExpression(ctx);
    }

    @Override
    public Node visitNotEqualsExpressionExpression(@NotNull UCBLogoParser.NotEqualsExpressionExpressionContext ctx) {
        return super.visitNotEqualsExpressionExpression(ctx);
    }

    @Override
    public Node visitList(@NotNull UCBLogoParser.ListContext ctx) {
        return super.visitList(ctx);
    }

    @Override
    public Node visitMultiplyExpression(@NotNull UCBLogoParser.MultiplyExpressionContext ctx) {
        return super.visitMultiplyExpression(ctx);
    }

    @Override
    public Node visitVariables(@NotNull UCBLogoParser.VariablesContext ctx) {
        return super.visitVariables(ctx);
    }

    @Override
    public Node visitExpressions(@NotNull UCBLogoParser.ExpressionsContext ctx) {
        return super.visitExpressions(ctx);
    }

    @Override
    public Node visitProcedure_call_extra_input(@NotNull UCBLogoParser.Procedure_call_extra_inputContext ctx) {
        return super.visitProcedure_call_extra_input(ctx);
    }

    @Override
    public Node visitParensExpression(@NotNull UCBLogoParser.ParensExpressionContext ctx) {
        return super.visitParensExpression(ctx);
    }

    @Override
    public Node visitProcedure_def(@NotNull UCBLogoParser.Procedure_defContext ctx) {
        return super.visitProcedure_def(ctx);
    }

    @Override
    public Node visitUnaryMinusExpression(@NotNull UCBLogoParser.UnaryMinusExpressionContext ctx) {
        return super.visitUnaryMinusExpression(ctx);
    }

    @Override
    public Node visitWordExpression(@NotNull UCBLogoParser.WordExpressionContext ctx) {
        return new ValueNode(new StringValue(ctx.WORD().getText()));
    }

    @Override
    public Node visitFloatExpression(@NotNull UCBLogoParser.FloatExpressionContext ctx) {
        return new ValueNode(new NumberValue(Double.valueOf(ctx.FLOAT().getText())));
    }

    @Override
    public Node visitIntExpression(@NotNull UCBLogoParser.IntExpressionContext ctx) {
        return new ValueNode(new NumberValue(Long.valueOf(ctx.INT().getText())));
    }

    @Override
    public Node visitBody_instruction(@NotNull UCBLogoParser.Body_instructionContext ctx) {
        return super.visitBody_instruction(ctx);
    }

    @Override
    public Node visitListExpression(@NotNull UCBLogoParser.ListExpressionContext ctx) {
        return super.visitListExpression(ctx);
    }

    @Override
    public Node visitArrayExpression(@NotNull UCBLogoParser.ArrayExpressionContext ctx) {
        return super.visitArrayExpression(ctx);
    }

    @Override
    public Node visitArray(@NotNull UCBLogoParser.ArrayContext ctx) {
        return super.visitArray(ctx);
    }

    public static void main(String[] args) {

        String source = "make \"xy 666 print xy";

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
