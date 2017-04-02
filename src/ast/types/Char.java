package ast.types;

import ast.Type;
import visitor.Visitor;

public class Char extends AbstractType {

	private static Char instance;

	public Char() {
		super(0, 0);
	}

	@Override
	public String toString() {
		return "char";
	}

	public static Char getInstance() {
		if (instance == null) {
			instance = new Char();
			return instance;
		}
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public boolean isLogical() {
		return true;
	}

	@Override
	public Type arithmetic(Type t) {
		if(t instanceof Real || t instanceof Int)
			return t;
		else if(t instanceof Char)
			return Int.getInstance();
		
		return null;
	}
	
	@Override
	public Type arithmetic() {
		return Int.getInstance();
	}
	
	@Override
	public Type comparison(Type t) {
		if (t instanceof Real || t instanceof Int || t instanceof Char)
            return Int.getInstance();

		return null;
	}
	
	@Override
	public Type logical(Type t) {
		if(t.isLogical())
			return Int.getInstance();
		return null;
	}
	
	@Override
	public Type promotesTo(Type t) {
		if(t instanceof Real || t instanceof Int || t instanceof Char)
			return t;
		return null;
	}

	@Override
	public int numerberOfBytes() {
		return 1;
	}
	
	
}
