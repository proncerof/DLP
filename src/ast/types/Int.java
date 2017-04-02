
package ast.types;

import ast.Type;
import visitor.Visitor;

public class Int extends AbstractType {

    private static Int instance;

    private Int() {
        super(0, 0);
    }

    @Override
    public String toString() {
        return "int";
    }

    public static Int getInstance() {
        if (instance == null) {
            instance = new Int();
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
    public Type arithmetic(Type type) {
        if (type instanceof Real || type instanceof Int)
            return type;
        else if (type instanceof Char)
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
    	if(t instanceof Real || t instanceof Int)
    		return t;
    	return null;
    }

	@Override
	public int numerberOfBytes() {
		return 2;
	}
}
