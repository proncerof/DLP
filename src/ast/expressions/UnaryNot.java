package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class UnaryNot extends AbstractExpression {

	private Expression expression;

	public UnaryNot(int row, int column, Expression expression) {
		super(row, column);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "!"+expression;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
}
