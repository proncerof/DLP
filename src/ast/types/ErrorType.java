package ast.types;

import ast.ASTNode;
import errorhandler.EH;
import visitor.Visitor;

public class ErrorType extends AbstractType {

	private String message;

	public ErrorType(int row, int column, String message) {
		super(row, column);
		this.message = message;

		EH.getEH().addError(this);
	}

	public ErrorType(ASTNode node, String message) {
		this(node.getRow(), node.getColumn(), message);
	}

	public String getMessage() {
		return "Row: " + getRow() + " Column: " + getColumn() + " " + message;
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
