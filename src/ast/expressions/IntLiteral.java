package ast.expressions;

import visitor.Visitor;

public class IntLiteral extends AbstractExpression {

	private int value;

	public IntLiteral(int row, int column, int value) {
		super(row, column);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value+"";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
}
