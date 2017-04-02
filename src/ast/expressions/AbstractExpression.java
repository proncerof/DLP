package ast.expressions;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Type;

public abstract class AbstractExpression extends AbstractASTNode implements Expression {

	private boolean lvalue;
	private Type type;

	public AbstractExpression(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean getLValue() {
		return lvalue;
	}

	@Override
	public void setLValue(boolean lvalue) {
		this.lvalue = lvalue;
	}
	
	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public void setType(Type type) {
		this.type = type;
	}

}
