package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class Arithmetic extends AbstractExpression{

	private Expression left, right;
	private String operand;

	public Arithmetic(int row, int column, Expression left, String operand, Expression right) {
		super(row, column);
		this.left = left;
		this.right = right;
		this.operand = operand;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public String getOperand() {
		return operand;
	}

	@Override
	public String toString() {
		return left + " " +operand+ " "+right;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}
