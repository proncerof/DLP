package ast.statements;

import java.util.List;

import ast.Expression;
import ast.Statement;
import ast.expressions.AbstractExpression;
import ast.expressions.Variable;
import visitor.Visitor;

public class Invocation extends AbstractExpression implements Statement {

	private Variable function;
	private List<Expression> parameters;

	public Invocation(int row, int column, Variable function, List<Expression> parameters) {
		super(row, column);

		this.function = function;
		this.parameters = parameters;

	}

	public Variable getFunction() {
		return function;
	}

	public List<Expression> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return function+ " " +parameters+ "\n";
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}
