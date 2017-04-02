package ast.statements;

import java.util.ArrayList;
import java.util.List;

import ast.AbstractASTNode;
import ast.Expression;
import ast.Statement;
import visitor.Visitor;

public class IfStatement extends AbstractASTNode implements Statement {

	private Expression expression;
	private List<Statement> ifStatements, elseStatements;

	public IfStatement(int row, int column, Expression expression, List<Statement> ifStatements,
			List<Statement> elseStatements) {
		super(row, column);
		this.expression = expression;
		this.ifStatements = ifStatements;
		this.elseStatements = elseStatements;
	}
	
	public IfStatement(int row, int column, Expression expression, List<Statement> ifStatements) {
		super(row, column);
		this.expression = expression;
		this.ifStatements = ifStatements;
		this.elseStatements = new ArrayList<>();
	}

	public Expression getExpression() {
		return expression;
	}

	public List<Statement> getIfStatements() {
		return ifStatements;
	}

	public List<Statement> getElseStatements() {
		return elseStatements;
	}

	@Override
	public String toString() {
		String result = "if(" + expression + "){\n";
		for(Statement statement : ifStatements)
			result+="\t"+statement;
		
		result+="\n\t}else{\n";
		for(Statement statement : elseStatements)
			result+="\t"+statement;
		
		result+="\n}\n";
		
		return result;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	

}
