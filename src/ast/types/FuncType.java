package ast.types;

import java.util.List;

import ast.Type;
import ast.definitions.VarDefinition;
import visitor.Visitor;

public class FuncType extends AbstractType {

	private Type returnType;
	private List<VarDefinition> parameters;

	public FuncType(int row, int column, Type returnType, List<VarDefinition> parameters) {
		super(row, column);
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "returnType=" + returnType + ", parameters=" + parameters;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<VarDefinition> getParameters() {
		return parameters;
	}
	
	@Override
	public Type funcInvocation(List<Type> t) {
		if(t.size()!=parameters.size())
			return null;
		
		for(int i=0; i<t.size(); i++){
			if(t.get(i).promotesTo(parameters.get(i).getType())==null)
				return null;
		}
		
		return this.returnType;
	}

	@Override
	public int numerberOfBytes() {
		int bytes = 0;
		for(VarDefinition v : parameters)
			bytes += v.getType().numerberOfBytes();
		
		bytes+=returnType.numerberOfBytes();
			
		return bytes;
	}

}
