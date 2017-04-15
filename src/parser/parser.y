%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
import ast.*;
import ast.types.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import java.util.*;
%}

// * Yacc declarations
//   Token definition
%token INT_CONSTANT CHAR_CONSTANT REAL_CONSTANT
%token INT DOUBLE CHAR
%token ID
%token VOID RETURN MAIN STRUCT IF ELSE WHILE READ WRITE
%token AND OR EQUALS LTOE GTOE NOTEQUALS

%nonassoc IFX CAST
%nonassoc ELSE FIELD
%right '=' 
%left AND OR '!'
%left '>' GTOE '<' LTOE NOTEQUALS EQUALS
%left '+' '-'
%left '*' '/' '%'
%right UNARY_MINUS
%nonassoc '[' ']'
%left '.'
%nonassoc '(' ')'

%%
// * Actions

final: program	main											{List<Definition> program = (List<Definition>)$1;
																	program.add((FuncDefinition) $2);
																	ast = new Program(scanner.getLine(), scanner.getColumn(),program);}
	 ;
	 
main: VOID MAIN '('')''{'body'}'								{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),new ArrayList<>());
																	$$ = new FuncDefinition(scanner.getLine(), scanner.getColumn(),"main",funcType,(List<Statement>)$6);}

program: program variable_definition							{List<Definition> program = (List<Definition>)$1;
	   																program.addAll((List<Definition>)$2);
	   																$$ = program;}
	   | program function										{List<Definition> program = (List<Definition>)$1;
	   																program.add((Definition)$2);
	   																$$ = program;}
	   | /* epsilon */											{$$ = new ArrayList<>();}

function: built_in_type ID '(' ')' '{' body '}'					{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),(Type)$1,new ArrayList<>());
																	$$ = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcType,(List<Statement>)$6);}
		| VOID ID '(' ')' '{' body '}'							{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),new ArrayList<>());
																	$$ = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcType,(List<Statement>)$6);}
		| built_in_type ID '(' parameter ')' '{' body '}'		{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),(Type)$1,(List<VarDefinition>)$4);
																	$$ = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcType,(List<Statement>)$7);}
		| VOID ID '(' parameter ')' '{' body '}'				{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),(List<VarDefinition>)$4);
																	$$ = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcType,(List<Statement>)$7);}
		;
		
parameter: parameter ',' built_in_type ID 						{List<VarDefinition> variables = (List<VarDefinition>)$1;
		 															variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)$4,(Type)$3));
		 															$$ = variables;}
		 | built_in_type ID										{List<VarDefinition> variables = new ArrayList<>();
		 															variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,(Type)$1));
		 															$$ = variables;}
		 ;

body: variable_definition_sequence statement_sequence 			{List<Statement> statements = new ArrayList<>();
																	statements.addAll((List<VarDefinition>)$1);
																	statements.addAll((List<Statement>)$2);
																	$$=statements; }
	;

variable_definition_sequence: variable_definition_sequence variable_definition		{((List<VarDefinition>)$1).addAll((List<VarDefinition>)$2);
																						$$ = $1;}
							| /* epsilon */											{$$ = new ArrayList<>();}
							;
	  
variable_definition: built_in_type id_enumeration ';'  { List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)$2)
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)$1));
															$$ = variables;
															}
					| array_struct id_enumeration ';'  { List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)$2)
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)$1));
															$$ = variables;
															}
				   ;
				   
array_struct: array_constructor			{$$ = $1;}
		    | struct_definition			{$$ = $1;}
		    ;

struct_definition: STRUCT '{' struct_body '}'  {$$ = new Struct(scanner.getLine(), scanner.getColumn(),(List<RecordField>)$3);}
				 ;

struct_body: struct_body recordField_definition		{for(RecordField r : (List<RecordField>)$2){
														if(((List<RecordField>)$1).contains(r))
															new ErrorType(scanner.getLine(), scanner.getColumn(),"id repetido:"+r.getName());
														((List<RecordField>)$1).add(r);
													  }	
													  $$ = $1;}
							| /* epsilon */			{$$ = new ArrayList<>();}
							;
	  
recordField_definition: built_in_type id_enumeration ';'  { List<RecordField> records = new ArrayList<>();
															for(String id : (List<String>)$2)
																records.add(new RecordField(scanner.getLine(), scanner.getColumn(),id,(Type)$1));
															$$ = records;
															}
					| array_struct id_enumeration ';'  { List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)$2)
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)$1));
															$$ = variables;
															}
				   ;

id_enumeration: id_enumeration ',' ID 					{List<String> ids = (List<String>)$1;
															if(ids.contains((String)$3))
																new ErrorType(scanner.getLine(), scanner.getColumn(),"id repetido:"+(String)$3);
															ids.add((String)$3);
															$$ = ids;}
			  | ID										{$$ = new ArrayList<>();
			  												((List<String>)$$).add((String)$1);}
			  ;

array_constructor: array_constructor '['INT_CONSTANT']' 	{$$ = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)$3,(Type)$1);}
				 | built_in_type '['INT_CONSTANT']'		{$$ = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)$3,(Type)$1);}
				 | struct_definition '['INT_CONSTANT']'	{$$ = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)$3,(Type)$1);}
				 ;

statement_sequence: statement_sequence statement			{((List<Statement>)$1).add((Statement)$2);
																$$ = $1;}
				  | /* epsilon */							{List<Statement> statements = new ArrayList<>();
				   												$$ = statements;}
				  ;
			  
statement: if_statement										{$$ = $1;}				
		 | WHILE '('expression')'if_while_body 				{$$ = new WhileStatement(scanner.getLine(), scanner.getColumn(),(Expression)$3,(List<Statement>)$5);}
		 | assigment ';'									{$$ = $1;}
		 | WRITE expression_sequence ';'					{$$ = new Write(scanner.getLine(), scanner.getColumn(),(List<Expression>)$2);}
		 | READ expression_sequence ';'						{$$ = new Read(scanner.getLine(), scanner.getColumn(),(List<Expression>)$2);}
		 | function_invocation ';'							{$$ = $1;}
		 | RETURN expression ';'							{$$ = new Return(scanner.getLine(), scanner.getColumn(),(Expression)$2);}
		 ;
		 
if_statement:  IF '('expression')' if_while_body 	
	       ELSE if_while_body								{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$3,(List<Statement>)$5,(List<Statement>)$7);}
		 | IF '('expression')'if_while_body	%prec	IFX		{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$3,(List<Statement>)$5);}
		 
if_while_body : '{'statement_sequence'}'					{$$ = $2;}
		| statement											{List<Statement> statements= new ArrayList<>();
																statements.add((Statement)$1);
																$$=statements;}
		;
		
		 
expression_sequence: expression_sequence ',' expression		{((List<Expression>)$1).add((Expression)$3);
																$$ = $1;}
				   | expression 							{List<Expression> expressions = new ArrayList<>();
				   												expressions.add((Expression)$1);
				   												$$ = expressions;}
				   ;
		  
assigment: expression '=' expression					{$$ = new Assignment(scanner.getLine(), scanner.getColumn(),(Expression)$1,(Expression)$3);}				
		 ;
		 
expression: expression '+' expression					{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)$1,"+",(Expression)$3);}
		  | expression '-' expression	 				{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)$1,"-",(Expression)$3);}
	      | expression '*'expression	 				{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)$1,"*",(Expression)$3);}
		  | expression '/'expression	 				{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)$1,"/",(Expression)$3);}
		  | expression '%'expression	 				{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)$1,"%",(Expression)$3);}
          | '-' expression %prec UNARY_MINUS			{$$ = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)$2);}
          | '!' expression								{$$ = new UnaryNot(scanner.getLine(), scanner.getColumn(),(Expression)$2);}
          | expression LTOE expression  				{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,"<=",(Expression)$3);}
          | expression GTOE expression  				{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,">=",(Expression)$3);}
          | expression EQUALS expression  				{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,"==",(Expression)$3);}
          | expression NOTEQUALS expression  			{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,"!=",(Expression)$3);}
          | expression OR expression  					{$$ = new Logical(scanner.getLine(), scanner.getColumn(),(Expression)$1,"||",(Expression)$3);}
          | expression AND expression  					{$$ = new Logical(scanner.getLine(), scanner.getColumn(),(Expression)$1,"$$",(Expression)$3);}
          | expression '>' expression  					{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,">",(Expression)$3);}
          | expression '<'expression  					{$$ = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)$1,"<",(Expression)$3);}
          | expression'.'ID			%prec FIELD 	    {$$ = new FieldAccess(scanner.getLine(), scanner.getColumn(),(Expression)$1,(String)$3);}
          | expression '[' expression ']'				{$$ = new Indexing(scanner.getLine(), scanner.getColumn(),(Expression)$1,(Expression)$3);}
          | '(' expression ')'							{$$ = $2;}
          | '(' built_in_type ')' expression %prec CAST	{$$ = new Cast(scanner.getLine(), scanner.getColumn(),(Type)$2,(Expression)$4);}
          | function_invocation							{$$ = $1;}
          | CHAR_CONSTANT								{$$ = new CharLiteral(scanner.getLine(),scanner.getColumn(),(char)$1);}
          | INT_CONSTANT								{$$ = new IntLiteral(scanner.getLine(),scanner.getColumn(),(int)$1);}
          | REAL_CONSTANT								{$$ = new RealLiteral(scanner.getLine(),scanner.getColumn(),(double)$1);}
          | ID											{$$ = new Variable(scanner.getLine(),scanner.getColumn(),(String)$1);}
          ;

function_invocation: ID '(' expression_sequence ')'		{Variable variable = new Variable(scanner.getLine(),scanner.getColumn(),(String)$1);	
															$$ = new Invocation(scanner.getLine(),scanner.getColumn(),variable,(List<Expression>)$3);}
				   | ID '('')'							{Variable variable = new Variable(scanner.getLine(),scanner.getColumn(),(String)$1);
				   											$$ = new Invocation(scanner.getLine(),scanner.getColumn(),variable,new ArrayList<>());}
				   ;

built_in_type: CHAR  									{$$ = Char.getInstance();}
			 | INT										{$$ = Int.getInstance();}
			 | DOUBLE									{$$ = Real.getInstance();}
			 ;
          
%%

// * Code
//   Members of the generated Parser class
// We must implement at least:
// - int yylex()
// - void yyerror(String)

// * Lexical analyzer
private Scanner scanner;

// * Invocation to the scanner
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex();
		this.yylval = scanner.getYylval(); 
    } catch(Throwable e) {
	    System.err.println ("Lexical error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Syntax error handler
public void yyerror (String error) {
    System.err.println ("Syntax error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor
public Parser(Scanner scanner) {
	this.scanner= scanner;
	//this.yydebug = true;
}

private ASTNode ast;
public ASTNode getAST(){
	return ast;
}
