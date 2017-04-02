package ast;

public abstract class AbstractASTNode implements ASTNode {

	private int row, column;
	
	public AbstractASTNode(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public int getRow() {
		return row;
	}
	
	@Override
	public int getColumn() {
		return column;
	}

}
