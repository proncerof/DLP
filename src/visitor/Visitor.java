package visitor;

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

public interface Visitor {

	Object visit(FuncDefinition f, Object o);
	Object visit(VarDefinition v, Object o);
	Object visit(Program p, Object o);
	Object visit(Read r, Object o);
	Object visit(Write w, Object o);
	Object visit(Assignment a, Object o);
	Object visit(IfStatement i, Object o);
	Object visit(Invocation i, Object o);
	Object visit(Return r, Object o);
	Object visit(WhileStatement w, Object o);
	Object visit(Arithmetic a, Object o);
	Object visit(Cast c, Object o);
	Object visit(CharLiteral c, Object o);
	Object visit(Comparison c, Object o);
	Object visit(FieldAccess f, Object o);
	Object visit(Indexing i, Object o);
	Object visit(IntLiteral i, Object o);
	Object visit(Logical l, Object o);
	Object visit(RealLiteral r, Object o);
	Object visit(UnaryMinus u, Object o);
	Object visit(UnaryNot u, Object o);
	Object visit(Variable v, Object o);
	Object visit(Array a, Object o);
	Object visit(Char c, Object o);
	Object visit(ErrorType e, Object o);
	Object visit(FuncType f, Object o);
	Object visit(Int i, Object o);
	Object visit(Real r, Object o);
	Object visit(Struct s, Object o);
	Object visit(VoidType v, Object o);
	Object visit(RecordField recordField, Object o);
	
}
