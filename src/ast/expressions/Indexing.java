package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class Indexing extends AbstractExpression {

	private Expression left, right;
	
	public Indexing(int row, int column, Expression left, Expression right) {
		super(row, column);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public String toString() {
		return left+"["+right+"]";
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}
