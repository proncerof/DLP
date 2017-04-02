package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import visitor.Visitor;

public class Return extends AbstractASTNode implements Statement {

	private Expression expression;
	
	public Return(int row, int column, Expression expression) {
		super(row, column);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "Return " + expression + "\n";
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	


}
