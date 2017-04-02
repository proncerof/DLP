package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class Comparison extends AbstractExpression{

	private Expression left, right;
	private String operator;
	
	public Comparison(int row, int column, Expression left, String operator, Expression right) {
		super(row, column);
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		return left+ " " +operator+" "+right;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
	
}
