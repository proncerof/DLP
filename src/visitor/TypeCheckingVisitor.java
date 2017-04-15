package visitor;

import java.util.ArrayList;
import java.util.List;

import ast.Expression;
import ast.Statement;
import ast.Type;
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
import ast.types.Char;
import ast.types.ErrorType;
import ast.types.FuncType;
import ast.types.Int;
import ast.types.Real;
import ast.types.VoidType;

public class TypeCheckingVisitor extends AbstractVisitor {

	@Override
	public Object visit(Read r, Object o) {
		for (Expression e : r.getExpressions()) {
			e.accept(this, o);
			if (!e.getLValue())
				new ErrorType(e, "LValue expected");
		}
		return null;
	}

	@Override
	public Object visit(Assignment a, Object o) {
		a.getRight().accept(this, o);
		a.getLeft().accept(this, o);
		if (!a.getLeft().getLValue())
			new ErrorType(a, "LValue expected");

		Type leftType = a.getLeft().getType();
		a.getLeft().setType(a.getRight().getType().promotesTo(a.getLeft().getType()));
		if (a.getLeft().getType() == null)
			a.getLeft().setType(
					new ErrorType(a, "No se puede asignar un " + a.getRight().getType() + " a un " + leftType));
		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object o) {
		a.getRight().accept(this, o);
		a.getLeft().accept(this, o);
		a.setLValue(false);

		a.setType(a.getLeft().getType().arithmetic(a.getRight().getType()));
		if (a.getType() == null)
			a.setType(new ErrorType(a, "Invalid arithmetic operation"));

		return null;
	}

	@Override
	public Object visit(Cast c, Object o) {
		c.getExpression().accept(this, o);
		c.getCastType().accept(this, o);
		c.setLValue(false);

		c.setType(c.getExpression().getType().castTo(c.getCastType()));
		if (c.getType() == null)
			c.setType(
					new ErrorType(c, "You cannot cast from " + c.getExpression().getType() + " to " + c.getCastType()));
		return null;
	}

	@Override
	public Object visit(CharLiteral c, Object o) {
		c.setLValue(false);
		c.setType(Char.getInstance());
		return null;
	}

	@Override
	public Object visit(Comparison c, Object o) {
		c.getRight().accept(this, o);
		c.getLeft().accept(this, o);
		c.setLValue(false);

		c.setType(c.getLeft().getType().comparison(c.getRight().getType()));
		if (c.getType() == null)
			c.setType(new ErrorType(c, "Invalid comparison operation"));
		return null;
	}

	@Override
	public Object visit(FieldAccess f, Object o) {
		f.getLeft().accept(this, o);
		f.setLValue(true);

		f.setType(f.getLeft().getType().fieldAccess(f.getRight()));
		if (f.getType() == null)
			f.setType(new ErrorType(f, f.getRight() + " is not a field of " + f.getLeft()));
		return null;
	}

	@Override
	public Object visit(Indexing i, Object o) {
		i.getLeft().accept(this, o);
		i.getRight().accept(this, o);
		i.setLValue(true);

		i.setType(i.getLeft().getType().indexing(i.getRight().getType()));
		if (i.getType() == null)
			new ErrorType(i, "Invalid indexing value");
		return null;
	}

	@Override
	public Object visit(IntLiteral i, Object o) {
		i.setLValue(false);
		i.setType(Int.getInstance());
		return null;
	}

	@Override
	public Object visit(Logical l, Object o) {
		l.getLeft().accept(this, o);
		l.getRight().accept(this, o);
		l.setLValue(false);
		l.setType(l.getLeft().getType().logical(l.getRight().getType()));
		if (l.getType() == null)
			l.setType(new ErrorType(l, "Invalid logial expression"));
		return null;
	}

	@Override
	public Object visit(RealLiteral r, Object o) {
		r.setLValue(false);
		r.setType(Real.getInstance());
		return null;
	}

	@Override
	public Object visit(UnaryMinus u, Object o) {
		u.getExpression().accept(this, o);
		u.setLValue(false);

		if (u.getType().arithmetic() == null)
			u.setType(new ErrorType(u, "Invalid unary minus expression"));
		return null;
	}

	@Override
	public Object visit(UnaryNot u, Object o) {
		u.getExpression().accept(this, o);
		u.setLValue(false);
		
		if (!u.getExpression().getType().isLogical())
			u.setType(new ErrorType(u, "You can not negate a non logical operation"));
		else
			u.setType(Int.getInstance());
		return null;
	}

	@Override
	public Object visit(Variable v, Object o) {
		v.setLValue(true);

		v.setType(v.getDefinition().getType());
		return null;
	}

	@Override
	public Object visit(WhileStatement w, Object o) {
		w.getExpression().accept(this, o);
		if (!w.getExpression().getType().isLogical())
			w.getExpression().setType(new ErrorType(w.getExpression(), "The condition is not logical"));

		for (Statement s : w.getStatements()) {
			s.accept(this, o);
		}

		return null;
	}

	@Override
	public Object visit(Invocation i, Object o) {
		i.getFunction().accept(this, o);
		List<Type> types = new ArrayList<>();

		for (Expression e : i.getParameters()) {
			e.accept(this, o);
			types.add(e.getType());
		}

		i.setType(i.getFunction().getType().funcInvocation(types));
		if (i.getType() == null)
			i.setType(new ErrorType(i, "Invocation with wrong parameters"));

		return null;
	}

	@Override
	public Object visit(FuncDefinition f, Object o) {
		Type returnType = (Type) f.getType().accept(this, o);
		boolean hasReturn = false;
		for (Statement s : f.getStatements()) {
			Object hasReturnObject = s.accept(this, returnType);
			if (hasReturnObject != null)
				if ((boolean) hasReturnObject)
					hasReturn = true;
		}

		if (!hasReturn && !(returnType instanceof VoidType))
			new ErrorType(f, "Return expected");
		else if (hasReturn && (returnType instanceof VoidType))
			new ErrorType(f, "No return expected");

		return null;
	}

	@Override
	public Object visit(FuncType f, Object o) {
		f.getReturnType().accept(this, o);
		for (VarDefinition v : f.getParameters())
			v.accept(this, o);

		return f.getReturnType();
	}

	@Override
	public Object visit(Return r, Object o) {
		Type returnType = (Type) o;
		r.getExpression().accept(this, o);

		if (r.getExpression().getType().promotesTo(returnType) == null)
			r.getExpression().setType(new ErrorType(r, "Invalid return type"));

		return true;
	}

	@Override
	public Object visit(IfStatement i, Object o) {
		i.getExpression().accept(this, o);
		boolean returnIf = false, returnElse = false;

		if (!i.getExpression().getType().isLogical())
			i.getExpression().setType(new ErrorType(i.getExpression(), "The condition is not logical"));

		for (Statement s : i.getIfStatements()) {
			Object returnIfObject = s.accept(this, o);
			if (returnIfObject != null) {
				if ((boolean) returnIfObject)
					returnIf = true;
			}
		}

		for (Statement s : i.getElseStatements()) {
			Object returnElseObject = s.accept(this, o);
			if (returnElseObject != null) {
				if ((boolean) returnElseObject)
					returnElse = true;
			}
		}

		return returnIf && returnElse;
	}

}
