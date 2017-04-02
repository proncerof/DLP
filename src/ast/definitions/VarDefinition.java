package ast.definitions;

import ast.Statement;
import ast.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {

	private int offset;

	public VarDefinition(int row, int column, String name, Type type) {
		super(row, column, type, name);

	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		System.out.println(getName()+": "+offset);
	}

	@Override
	public String toString() {
		return getType()+" "+getName();
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}