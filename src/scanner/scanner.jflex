// ************  User Code  ********************

package scanner;
import parser.Parser;

%%
// ************  Options ********************

// % debug // * For debugging purposes
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Fields and methods ********************

// * To get the line number
public int getLine() { 
	// * JFlex starts in zero
	return this.yyline+1;
}

// * To get the column number
public int getColumn() { 
	// * JFlex starts in zero
	return yycolumn+1;
}

// * Semantic value of the token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}


%}

// ************  Macros ********************
digit = [0-9]
letter = [a-zA-Z]
double = {digit}*"."{digit}+|{digit}+"."{digit}*
real = (({double}|{digit}+)[e|E][-\+]?[0-9]+)|{double}
operator = "+"|"-"|"*"|"/"|"="|">"|"<"|"!"|"("|")"|"{"|"}"|","|";"|"["|"]"|"%"|"."|"&"|"|"
ID={letter}({digit}|{letter})*
Char_Ascii = '\\0*([0-1]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])'
Char_Constant_t = '\\t'
Char_Constant_n = '\\n'

%%
// ************  Lexical Rules ********************
[ \t\n\r\f]				{}
"//".* 					{this.yylval = yytext();}
"/*"~"*/" 	{this.yylval = yytext();}
                 
int 					{this.yylval = yytext(); 
							return Parser.INT;}		
char	 				{this.yylval = yytext();
							return Parser.CHAR;}
double	 				{this.yylval = yytext();
							return Parser.DOUBLE;}
while 					{this.yylval = yytext();
							return Parser.WHILE;}
return	 				{this.yylval = yytext();
							return Parser.RETURN;}
main 					{this.yylval = yytext();
							return Parser.MAIN;}
void					{this.yylval = yytext(); 
							return Parser.VOID;}
struct 					{this.yylval = yytext(); 
							return Parser.STRUCT;}
if						{this.yylval = yytext(); 
							return Parser.IF;}

else					{this.yylval = yytext(); 
							return Parser.ELSE;}
							
{ID}	 				{this.yylval = yytext(); 
							return Parser.ID;}

{digit}* 				{this.yylval = new Integer(yytext()); 
							return Parser.INT_CONSTANT;}
							
{real} 					{this.yylval = new Double(yytext()); 
							return Parser.REAL_CONSTANT;}
							
"&&"					{this.yylval = yytext();
                    		return Parser.AND;}
                    		
"||"					{this.yylval = yytext();
                    		return Parser.OR;}

"=="					{this.yylval = yytext();
                    		return Parser.EQUALS;}
                    		
"!="					{this.yylval = yytext();
                    		return Parser.NOTEQUALS;}

"<="					{this.yylval = yytext();
                    		return Parser.LTOE;}

">="					{this.yylval = yytext();
                    		return Parser.GTOE;}
							
'.'    					{this.yylval = yycharat(1);
                    		return Parser.CHAR_CONSTANT;}
                   
{Char_Ascii}        	{this.yylval = (char) Integer.parseInt(yytext().substring(2, yytext().length()-1));
                   			return Parser.CHAR_CONSTANT;}
                   
{Char_Constant_t } 		{this.yylval = new Character('\t');
                   			return Parser.CHAR_CONSTANT;}
                   
{Char_Constant_n } 		{this.yylval = new Character('\n');
                    		return Parser.CHAR_CONSTANT;}
{operator}				{this.yylval = yytext();
							return (int) yycharat(0);}
. 						{System.err.println(getLine()+" "+getColumn()+" "+yytext());}


