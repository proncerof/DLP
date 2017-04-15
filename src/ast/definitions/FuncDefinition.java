package ast.definitions;

import java.util.List;

import ast.Statement;
import ast.Type;
import visitor.Visitor;

public class FuncDefinition extends AbstractDefinition {

	private List<Statement> statements;
	private int totalBytes;

	public FuncDefinition(int row, int column, String name, Type type, List<Statement> statements) {
		super(row, column, type, name);
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setTotalBytes(int bytes) {
		this.totalBytes = bytes;
	}

	public int getTotalBytes() {
		return totalBytes;
	}

	@Override
	public String toString() {
		String result = "";

		result += getName() + " " + getType().toString() + "\n";

		for (Statement statement : statements)
			result += "\t" + statement.toString() + "\n";
		return result;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
