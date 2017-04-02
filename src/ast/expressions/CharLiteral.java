package ast.expressions;

import visitor.Visitor;

public class CharLiteral extends AbstractExpression {

    private char value;

    public CharLiteral(int row, int column, char value) {
        super(row, column);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public Object accept(Visitor v, Object o) {
        return v.visit(this, o);
    }


}
