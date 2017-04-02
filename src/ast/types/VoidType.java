package ast.types;

import visitor.Visitor;

public class VoidType extends AbstractType {

	private static VoidType instance;

	private VoidType() {
		super(0, 0);
	}

	@Override
	public String toString() {
		return "void";
	}

	public static VoidType getInstance() {
		if (instance == null) {
			instance = new VoidType();
			return instance;
		}
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public int numerberOfBytes() {
		return 0;
	}
	
	

}
