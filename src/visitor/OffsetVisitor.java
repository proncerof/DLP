package visitor;

import ast.Definition;
import ast.Program;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.types.FuncType;
import ast.types.RecordField;
import ast.types.Struct;

public class OffsetVisitor extends AbstractVisitor {

	@Override
	public Object visit(Program p, Object o) {
		int offset = 0;
		for (Definition d : p.getDefinitions()) {
			d.accept(this, o);
			if (d instanceof VarDefinition) {
				((VarDefinition) d).setOffset(offset);
				offset += d.getType().numerberOfBytes();
			}
		}
		return null;
	}

	@Override
	public Object visit(FuncDefinition f, Object o) {
		f.getType().accept(this, o);

		int offset = 0;
		for (Statement s : f.getStatements()){
			s.accept(this, o);
			if(s instanceof VarDefinition){
				offset-= ((VarDefinition) s).getType().numerberOfBytes();
				((VarDefinition) s).setOffset(offset);	
			}
		}

		return null;
	}

	@Override
	public Object visit(FuncType f, Object o) {
		f.getReturnType().accept(this, o);
		
		int offset = 4;
		for(int i=f.getParameters().size()-1 ; i>=0 ; i--){
			VarDefinition parameter = f.getParameters().get(i);
			parameter.accept(this, o);
			parameter.setOffset(offset);
			offset+=parameter.getType().numerberOfBytes();
		}
		return null;
	}

	@Override
	public Object visit(Struct s, Object o) {
		int offset = 0;
		for (RecordField r : s.getRecordFields()) {
			r.accept(this, o);
			r.setOffset(offset);
			offset += r.getType().numerberOfBytes();
		}
		return null;
	}

}
