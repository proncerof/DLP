package ast.types;

import ast.AbstractASTNode;
import ast.Type;
import visitor.Visitor;

public class RecordField extends AbstractASTNode {

	private String name;
	private Type type;
	private int offset;

	public RecordField(int row, int column, String name, Type type) {
		super(row, column);
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		System.out.println(getName() + ": " + offset);
	}

	@Override
	public String toString() {
		return type + " " + name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordField other = (RecordField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}