package codeGeneration;

import ast.definitions.VarDefinition;
import ast.expressions.Variable;

public class AddressCGVisitor extends AbstractCGVisitor {

	private CodeGeneration cg;

	public AddressCGVisitor(CodeGeneration cg) {
		this.cg = cg;
	}

	@Override
	public Object visit(Variable v, Object o) {
		if (v.getDefinition().getScope() == 0) {
			cg.pushAddress(((VarDefinition) v.getDefinition()).getOffset());
		}else{
			cg.pushBp();
			cg.push(((VarDefinition) v.getDefinition()).getOffset());
			cg.arithmetic("+", 'i');
		}
			
		return null;
	}

}
