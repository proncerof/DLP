package ast.statements;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import visitor.Visitor;

public class Assignment extends AbstractASTNode implements Statement {

	private Expression left, right;

	public Assignment(int line, int column, Expression left, Expression right) {
		super(line, column);
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
		return left + "=" + right+ "\n";
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	


}
