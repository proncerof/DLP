package visitor;

import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor {

    private SymbolTable st = new SymbolTable();

    @Override
    public Object visit(VarDefinition v, Object o) {
        v.getType().accept(this, o);
        if (!st.insert(v))
            new ErrorType(v, "Variable repeated");

        return null;
    }

    @Override
    public Object visit(FuncDefinition f, Object o) {
        if (!st.insert(f))
            new ErrorType(f, "Function repeated");
        st.set();
        f.getType().accept(this, o);
        for (Statement s : f.getStatements())
            s.accept(this, o);
        st.reset();
        return null;
    }

    @Override
    public Object visit(Variable v, Object o) {
        v.setDefinition(st.find(v.getName()));
        if (v.getDefinition() == null)
            v.setDefinition(new VarDefinition(v.getRow(), v.getColumn(), v.getName(), new ErrorType(v, "Variable not declared")));
        return null;
    }


}
