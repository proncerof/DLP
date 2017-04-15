package codeGeneration;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Assignment;
import ast.statements.Read;
import ast.statements.Write;

public class ExecuteCGVisitor extends AbstractCGVisitor {

	private CodeGeneration cg;
	private ValueCGVisitor value;
	private AddressCGVisitor address;

	public ExecuteCGVisitor(String input, String output) {
		this.cg = new CodeGeneration(input, output);
		this.address = new AddressCGVisitor(cg);
		this.value = new ValueCGVisitor(cg,this.address);
	}

	@Override
	public Object visit(Program p, Object o) {
		for (Definition d : p.getDefinitions())
			if (d instanceof VarDefinition)
				d.accept(this, o);

		cg.callMain();

		for (Definition d : p.getDefinitions())
			if (d instanceof FuncDefinition)
				d.accept(this, o);

		cg.close();

		return null;
	}
	
	@Override
	public Object visit(VarDefinition v, Object o) {
		cg.comment("* " + v + " offset (" + ((VarDefinition) v).getOffset() + ")");
		return null;
	}
	
	@Override
	public Object visit(FuncDefinition f, Object o) {
		cg.newFunction(f.getName(), f.getTotalBytes());
		
		int currentLine = 0;
		for(Statement s : f.getStatements()){
			if(s.getRow() != currentLine){
				cg.newLine(s.getRow());
				currentLine = s.getRow();
			}
			s.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Write w, Object o) {
		for (Expression exp : w.getExpressions()) {
			exp.accept(value, o);
			System.out.println(exp);
			cg.out(exp.getType().suffix());
		}
		return null;
	}

	@Override
	public Object visit(Read r, Object o) {
		for (Expression exp : r.getExpressions()) {
			exp.accept(address, o);
			cg.in(exp.getType().suffix());
			cg.store(exp.getType().suffix());
		}
		return null;
	}

	@Override
	public Object visit(Assignment a, Object o) {
		a.getLeft().accept(address, o);
		a.getRight().accept(value, o);
		cg.convertTo(a.getRight().getType().suffix(), a.getLeft().getType().suffix());
		cg.store(a.getLeft().getType().suffix());
		return null;
	}

}
