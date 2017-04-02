package ast;

public interface Definition extends ASTNode{

	String getName();
	Type getType();
	int getScope();
	void setScope(int scope);
	
}
