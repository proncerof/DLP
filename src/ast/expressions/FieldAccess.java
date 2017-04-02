package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class FieldAccess extends AbstractExpression {

	private Expression left;
	private String right;

	public FieldAccess(int row, int column, Expression left, String right) {
		super(row, column);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	@Override
	public String toString() {
		return left+"."+right;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
}
