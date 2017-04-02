package ast.types;

import ast.Type;
import visitor.Visitor;

public class Real extends AbstractType {

	private static Real instance;

	private Real() {
		super(0, 0);
	}

	@Override
	public String toString() {
		return "Real";
	}

	public static Real getInstance() {
		if (instance == null) {
			instance = new Real();
			return instance;
		}
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
	@Override
	public Type arithmetic(Type t) {
		if (t instanceof Real || t instanceof Int || t instanceof Char)
            return Real.getInstance();

        return null;
	}
	
	@Override
	public Type arithmetic() {
		return Real.getInstance();
	}
	
	@Override
	public Type comparison(Type t) {
		if (t instanceof Real || t instanceof Int || t instanceof Char)
            return Int.getInstance();

		return null;
	}
	
	@Override
	public Type promotesTo(Type t) {
		if(t instanceof Real)
			return t;
		return super.promotesTo(t);
	}

	@Override
	public int numerberOfBytes() {
		return 4;
	}
	
	

}
