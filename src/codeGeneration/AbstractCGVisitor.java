package codeGeneration;

import ast.Program;
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
import visitor.Visitor;

public abstract class AbstractCGVisitor implements Visitor{

	@Override
	public Object visit(FuncDefinition f, Object o) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(VarDefinition v, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Program p, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Read r, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Write w, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Assignment a, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(IfStatement i, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Invocation i, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Return r, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(WhileStatement w, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Arithmetic a, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Cast c, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(CharLiteral c, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Comparison c, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(FieldAccess f, Object o) {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(Indexing i, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(IntLiteral i, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Logical l, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(RealLiteral r, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(UnaryMinus u, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(UnaryNot u, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Variable v, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Array a, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Char c, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(ErrorType e, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(FuncType f, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Int i, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Real r, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(Struct s, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(VoidType v, Object o) {
		throw new IllegalStateException();
		
	}

	@Override
	public Object visit(RecordField recordField, Object o) {
		throw new IllegalStateException();
		
	}

}
