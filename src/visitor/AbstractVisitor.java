package visitor;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RealLiteral;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;
import ast.statements.Assignment;
import ast.statements.IfStatement;
import ast.statements.Invocation;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.WhileStatement;
import ast.statements.Write;
import ast.types.Array;
import ast.types.Char;
import ast.types.ErrorType;
import ast.types.FuncType;
import ast.types.Int;
import ast.types.Real;
import ast.types.RecordField;
import ast.types.Struct;
import ast.types.VoidType;

public abstract class AbstractVisitor implements Visitor {

	@Override
	public Object visit(FuncDefinition f, Object o) {
		f.getType().accept(this, o);

		for (Statement s : f.getStatements())
			s.accept(this, o);

		return null;
	}

	@Override
	public Object visit(VarDefinition v, Object o) {
		v.getType().accept(this, o);

		return null;
	}

	@Override
	public Object visit(Program p, Object o) {
		for (Definition d : p.getDefinitions())
			d.accept(this, o);

		return null;
	}

	@Override
	public Object visit(Read r, Object o) {
		for (Expression e : r.getExpressions()) {
			e.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Write w, Object o) {
		for (Expression e : w.getExpressions())
			e.accept(this, o);
		return null;
	}

	@Override
	public Object visit(Assignment a, Object o) {
		a.getLeft().accept(this, o);
		a.getRight().accept(this, o);

		return null;
	}

	@Override
	public Object visit(IfStatement i, Object o) {
		i.getExpression().accept(this, o);

		for (Statement s : i.getIfStatements())
			s.accept(this, o);

		for (Statement s : i.getElseStatements())
			s.accept(this, o);

		return null;
	}

	@Override
	public Object visit(Invocation i, Object o) {
		i.getFunction().accept(this, o);

		for (Expression e : i.getParameters())
			e.accept(this, o);

		return null;
	}

	@Override
	public Object visit(Return r, Object o) {
		r.getExpression().accept(this, o);
		return null;
	}

	@Override
	public Object visit(WhileStatement w, Object o) {
		w.getExpression().accept(this, o);

		for (Statement s : w.getStatements())
			s.accept(this, o);
		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object o) {
		a.getLeft().accept(this, o);
		a.getRight().accept(this, o);

		return null;
	}

	@Override
	public Object visit(Cast c, Object o) {
		c.getCastType().accept(this, o);
		c.getExpression().accept(this, o);
		
		return null;
	}

	@Override
	public Object visit(CharLiteral c, Object o) {
		return null;
	}

	@Override
	public Object visit(Comparison c, Object o) {
		c.getLeft().accept(this, o);
		c.getRight().accept(this, o);

		return null;
	}

	@Override
	public Object visit(FieldAccess f, Object o) {
		f.getLeft().accept(this, o);

		return null;
	}

	@Override
	public Object visit(Indexing i, Object o) {
		i.getLeft().accept(this, o);
		i.getRight().accept(this, o);

		return null;
	}

	@Override
	public Object visit(IntLiteral i, Object o) {
		return null;
	}

	@Override
	public Object visit(Logical l, Object o) {
		l.getLeft().accept(this, o);
		l.getRight().accept(this, o);

		return null;
	}

	@Override
	public Object visit(RealLiteral r, Object o) {
		return null;
	}

	@Override
	public Object visit(UnaryMinus u, Object o) {
		u.getExpression().accept(this, o);

		return null;
	}

	@Override
	public Object visit(UnaryNot u, Object o) {
		u.getExpression().accept(this, o);

		return null;
	}

	@Override
	public Object visit(Variable v, Object o) {
		return null;
	}

	@Override
	public Object visit(Array a, Object o) {
		a.getOfType().accept(this, o);
		
		return null;
	}

	@Override
	public Object visit(Char c, Object o) {
		return null;
	}

	@Override
	public Object visit(ErrorType e, Object o) {
		return null;
	}

	@Override
	public Object visit(FuncType f, Object o) {
		f.getReturnType().accept(this, o);
		for(VarDefinition v : f.getParameters())
			v.accept(this, o);
		return null;
	}

	@Override
	public Object visit(Int i, Object o) {
		return null;
	}

	@Override
	public Object visit(Real r, Object o) {
		return null;
	}

	@Override
	public Object visit(Struct s, Object o) {
		for(RecordField r : s.getRecordFields())
			r.accept(this, o);

		return null;
	}

	@Override
	public Object visit(VoidType v, Object o) {
		return null;
	}

	@Override
	public Object visit(RecordField r, Object o) {
		r.getType().accept(this, o);
		
		return null;
	}

}
