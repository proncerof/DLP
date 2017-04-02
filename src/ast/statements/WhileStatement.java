package ast.statements;

import java.util.List;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import visitor.Visitor;

public class WhileStatement extends AbstractASTNode implements Statement {

	private Expression expression;
	private List<Statement> statements;

	public WhileStatement(int row, int column, Expression expression, List<Statement> statements) {
		super(row, column);
		this.expression = expression;
		this.statements = statements;
	}

	public Expression getExpression() {
		return expression;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public String toString() {
		String result = "While(" + expression + "){\n";

		for (Statement statement : statements)
			result += "\t" + statement;

		result += "\n}\n";
		return result;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
