
package ast.expressions;

import ast.Expression;
import ast.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression{

	private Type castType;
	private Expression expression;

	public Cast(int row, int column, Type castType, Expression expression) {
		super(row, column);
		this.castType = castType;
		this.expression = expression;
	}

	public Type getCastType() {
		return castType;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "("+castType+")"+expression;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}
