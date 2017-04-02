package ast;

import java.util.List;

public interface Type extends ASTNode {
	
    boolean isLogical();

    Type arithmetic(Type t);

    Type arithmetic();

    Type comparison(Type t);

    Type logical(Type t);

    Type promotesTo(Type t);
    
    Type funcInvocation(List<Type> t);

    Type indexing(Type t);

    Type fieldAccess(String c);
    
    int numerberOfBytes();

}
