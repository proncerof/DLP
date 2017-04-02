package ast.types;

import java.util.List;

import ast.Type;
import visitor.Visitor;

public class Struct extends AbstractType {

	private List<RecordField> recordFields;

	public Struct(int row, int column, List<RecordField> recordFields) {
		super(row, column);
		this.recordFields = recordFields;
	}

	public List<RecordField> getRecordFields() {
		return recordFields;
	}

	@Override
	public String toString() {
		return "Struct {" + recordFields + "}";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type fieldAccess(String c) {
		for(RecordField f : recordFields)
			if(f.getName().equals(c))
				return f.getType();
			 
		return null;
	}

	@Override
	public int numerberOfBytes() {
		int bytes= 0;
		for(RecordField r : recordFields)
			bytes += r.getType().numerberOfBytes();
		
		return bytes;
	}
}
