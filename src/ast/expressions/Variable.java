package ast.expressions;

import ast.Definition;
import visitor.Visitor;

public class Variable extends AbstractExpression {

	private String name;
	private Definition definition;

	public Variable(int row, int column, String name) {
		super(row, column);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return name + "";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
