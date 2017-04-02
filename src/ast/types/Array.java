package ast.types;

import ast.Type;
import visitor.Visitor;

public class Array extends AbstractType {

    private int size;
    private Type ofType;

    public Array(int row, int column, int size, Type ofType) {
        super(row, column);
        this.size = size;
        this.ofType = ofType;
    }

    public static Array createOrderedArray(int row, int column, int size, Type ofType) {
        if (!(ofType instanceof Array))
            return new Array(row, column, size, ofType);
        else {
            Array oType = (Array) ofType;
            Array apuntador = oType;
            Type apuntado = apuntador.ofType;
            while (apuntado instanceof Array) {
                apuntador = (Array) apuntado;
                apuntado = apuntador.ofType;
            }
            apuntador.ofType = new Array(row, column, size, apuntador.ofType);
            return oType;
        }
    }

    public int getSize() {
        return size;
    }

    public Type getOfType() {
        return ofType;
    }

    @Override
    public String toString() {
        return ofType + "[" + size + "]";
    }

    @Override
    public Object accept(Visitor v, Object o) {
        return v.visit(this, o);
    }

    @Override
    public Type indexing(Type t) {
        return t.promotesTo(Int.getInstance());
    }

	@Override
	public int numerberOfBytes() {
		return this.ofType.numerberOfBytes()*size;
	}
}
