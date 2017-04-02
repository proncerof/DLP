package ast.definitions;

import ast.AbstractASTNode;
import ast.Definition;
import ast.Type;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition {

	private Type type;
	private String name;
	private int scope;

	public AbstractDefinition(int row, int column, Type type, String name) {
		super(row, column);
		this.type = type;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public int getScope() {
		return scope;
	}

	@Override
	public void setScope(int scope) {
		this.scope = scope;
	}

}
