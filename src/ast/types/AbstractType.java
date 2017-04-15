package ast.types;

import java.util.List;

import ast.AbstractASTNode;
import ast.Type;
import visitor.Visitor;

public abstract class AbstractType extends AbstractASTNode implements Type {

	AbstractType(int row, int column) {
		super(row, column);
	}

	@Override
	public Type arithmetic(Type t) {
		return null;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparison(Type t) {
		return null;
	}

	@Override
	public Type logical(Type t) {
		return null;
	}

	@Override
	public Type promotesTo(Type t) {
		return null;
	}

	@Override
	public Type indexing(Type t) {
		return null;
	}

	@Override
	public boolean isLogical() {
		return false;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return null;
	}

	@Override
	public Type funcInvocation(List<Type> t) {
		return null;
	}

	@Override
	public Type fieldAccess(String c) {
		return null;
	}

	@Override
	public Type castTo(Type t) {
		return null;
	}

	@Override
	public boolean isPrimitive() {
		return false;
	}

	@Override
	public char suffix() {
		return 0;
	}

}
