package codeGeneration;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RealLiteral;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;

public class ValueCGVisitor extends AbstractCGVisitor {

	private CodeGeneration cg;
	private AddressCGVisitor address;

	public ValueCGVisitor(CodeGeneration cg, AddressCGVisitor address) {
		this.cg = cg;
		this.address = address;
	}

	@Override
	public Object visit(IntLiteral i, Object o) {
		cg.push(i.getValue());
		return null;
	}

	@Override
	public Object visit(CharLiteral c, Object o) {
		cg.push(c.getValue());
		return null;
	}

	@Override
	public Object visit(RealLiteral r, Object o) {
		cg.push(r.getValue());
		return null;
	}

	@Override
	public Object visit(Variable v, Object o) {
		v.accept(address, o);
		cg.load(v.getType().suffix());
		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object o) {
		a.getLeft().accept(this, o);
		cg.convertTo(a.getLeft().getType().suffix(), a.getType().suffix());

		a.getRight().accept(this, o);
		cg.convertTo(a.getRight().getType().suffix(), a.getType().suffix());

		cg.arithmetic(a.getOperator(), a.getType().suffix());

		return null;
	}

	@Override
	public Object visit(Comparison c, Object o) {
		c.getLeft().accept(this, o);
		cg.convertTo(c.getLeft().getType().suffix(), c.getType().suffix());

		c.getRight().accept(this, o);
		cg.convertTo(c.getRight().getType().suffix(), c.getType().suffix());

		cg.comparison(c.getOperator(), c.getType().suffix());

		return null;
	}
	
	@Override
	public Object visit(Cast c, Object o) {
		c.getExpression().accept(this, o);
		cg.convertTo(c.getExpression().getType().suffix(),c.getCastType().suffix());
		return null;
	}
	
	@Override
	public Object visit(Logical l, Object o) {
		l.getLeft().accept(this, o);
		l.getRight().accept(this, o);
		
		cg.logical(l.getOperator());
		
		return null;
	}
	
	@Override
	public Object visit(UnaryNot u, Object o) {
		u.getExpression().accept(this, o);
		cg.not();
		return null;
	}

}
