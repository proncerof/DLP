package ast.statements;

import java.util.List;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import visitor.Visitor;

public class Read extends AbstractASTNode implements Statement {

	private List<Expression> expressions;

	public Read(int row, int column, List<Expression> expressions) {
		super(row, column);
		this.expressions = expressions;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	@Override
	public String toString() {
		return "Read " + expressions + "\n";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
	
}
