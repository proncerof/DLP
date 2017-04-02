package ast.expressions;

import visitor.Visitor;

public class RealLiteral extends AbstractExpression {
	 
	private double value;
	
	public RealLiteral(int row, int column, double value) {
		super(row, column);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
	
}
