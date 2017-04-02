package ast;

import java.util.List;

import visitor.Visitor;

public class Program extends AbstractASTNode{

	private List<Definition> definitions;

	public Program(int row, int column, List<Definition> definitions) {
		super(row, column);
		this.definitions= definitions;
	}
	
	public List<Definition> getDefinitions() {
		return definitions;
	}

	@Override
	public String toString() {
		String result = "";
		for(Definition def : definitions)
			result+= def.toString()+"\n";
		return result;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
