//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Java declarations*/
/*   This code is copied in the beginning of the generated Java file*/
import scanner.Scanner;
import ast.*;
import ast.types.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import java.util.*;
//#line 27 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short CHAR_CONSTANT=258;
public final static short REAL_CONSTANT=259;
public final static short INT=260;
public final static short DOUBLE=261;
public final static short CHAR=262;
public final static short ID=263;
public final static short VOID=264;
public final static short RETURN=265;
public final static short MAIN=266;
public final static short STRUCT=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short WHILE=270;
public final static short READ=271;
public final static short WRITE=272;
public final static short AND=273;
public final static short OR=274;
public final static short EQUALS=275;
public final static short LTOE=276;
public final static short GTOE=277;
public final static short NOTEQUALS=278;
public final static short IFX=279;
public final static short CAST=280;
public final static short FIELD=281;
public final static short UNARY_MINUS=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    1,    1,    1,    5,    5,    5,    5,    7,
    7,    3,    8,    8,    4,    4,   11,   11,   13,   14,
   14,   15,   15,   10,   10,   12,   12,   12,    9,    9,
   16,   16,   16,   16,   16,   16,   16,   17,   17,   19,
   19,   21,   21,   20,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   22,   22,
    6,    6,    6,
};
final static short yylen[] = {                            2,
    2,    7,    2,    2,    0,    7,    7,    8,    8,    4,
    2,    2,    2,    0,    3,    3,    1,    1,    4,    2,
    0,    3,    3,    3,    1,    4,    4,    4,    2,    0,
    1,    5,    2,    3,    3,    2,    3,    7,    5,    3,
    1,    3,    1,    3,    3,    3,    3,    3,    3,    2,
    2,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    4,    3,    4,    1,    1,    1,    1,    1,    4,    3,
    1,    1,    1,
};
final static short yydefred[] = {                         5,
    0,    0,   72,   73,   71,    0,    0,    1,    3,    4,
    0,    0,    0,    0,    0,    0,   21,    0,    0,    0,
   25,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   15,   16,    0,    0,    0,    0,    0,    0,   19,    0,
    0,   20,    0,    0,   27,   24,   26,   28,   14,   11,
    0,    0,   14,    0,    0,   14,    0,    0,    0,   14,
    0,    0,   22,   23,    0,   14,    7,   13,    0,    0,
    0,   10,    2,    6,    0,   66,   65,   67,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   29,   31,    0,
    0,    0,    9,    8,    0,    0,   64,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   33,   36,   70,    0,   37,    0,    0,    0,
   35,   34,    0,   62,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   69,    0,    0,    0,    0,   61,   30,   41,    0,   32,
    0,    0,   40,   38,
};
final static short yydgoto[] = {                          1,
    2,    8,   58,    9,   10,   36,   37,   59,   70,   20,
   12,   13,   14,   27,   42,  158,   89,   90,  159,   91,
  101,   97,
};
final static short yysindex[] = {                         0,
    0,  118,    0,    0,    0, -153, -120,    0,    0,    0,
  -78, -243,  -64,  -59,   12,   13,    0,   17, -175,  -18,
    0,    7, -173, -156,  -29,   76,  -33,   61,   26, -142,
    0,    0,   33,   34,   16, -109,  108,   38,    0,  -60,
 -243,    0,   49,  115,    0,    0,    0,    0,    0,    0,
   57,  -84,    0,   31,   32,    0,   64,   68, -116,    0,
  -81,   70,    0,    0,   86,    0,    0,    0,  -60,  151,
   89,    0,    0,    0,   96,    0,    0,    0,  149,  419,
  154,  180,  419,  419,  419,  419,  380,    0,    0,   62,
  164,  167,    0,    0,  524,   88,    0,  419,  419,  224,
   41,   59,  283,  -32,  194,   95,  419,  419,  419,  419,
  419,  419,  419,  419,  419,  419,  419,  419,  419,  419,
  419,  -20,    0,    0,    0,  169,    0,  121,  128,  419,
    0,    0,  419,    0,  283,  283,   69,   69,   69,   69,
  224,   69,   69,  503,  503,  -32,  -32,  -32,  162,    0,
    0,  185,  185,  224,  224,    0,    0,    0,  -25,    0,
  223,  185,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -9,   -6,    0,    0,    0,   84,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  250,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  133,
    0,    0,    0,    0,    0,    0,    0,    0,  -37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  155,    0,    0,    0,    0,    0,    0,    0,   -4,
    0,    0,  201,  -26,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  424,  564,  410,  470,  492,  529,
  200,  542,  551,  417,  482,    1,   27,   36,    0,    0,
    0,    0,    0,    6,  555,    0,    0,    0,  309,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  291,  205,    0,  325,  237,    0,  116,  -11,
  245,    0,    0,    0,    0,  -68,    0,  619, -104,    0,
  -56,  136,
};
final static int YYTABLESIZE=838;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         68,
   22,   88,   17,   68,   68,   68,   68,   68,   68,   68,
   50,   35,   19,  122,   50,   50,   50,   50,   50,   21,
   50,   68,   68,   68,   68,   30,   23,  102,   54,   55,
   19,   24,   50,   50,   50,   50,   43,   47,  126,   43,
   31,   47,   47,   47,   47,   47,   42,   47,  160,   42,
   30,   25,   26,   68,   43,   68,   28,  164,  121,   47,
   47,   47,   47,   48,   42,   32,   50,   48,   48,   48,
   48,   48,   49,   48,   30,   30,   49,   49,   49,   49,
   49,   29,   49,   33,  130,   48,   48,   48,   48,   63,
   64,   39,   88,   47,   49,   49,   49,   49,  120,  131,
   34,   43,  130,  118,  116,  120,  117,  122,  119,   15,
  118,  116,   16,  117,  122,  119,   38,  132,   45,   48,
   46,  115,  113,  114,  120,   47,   48,   25,   49,  118,
  116,  120,  117,  122,  119,  134,  118,  116,   49,  117,
  122,  119,   25,    3,    4,    5,  127,  115,   51,  114,
    7,   52,  121,   50,  115,   57,  114,  120,   52,  121,
   53,  152,  118,  116,  120,  117,  122,  119,  153,  118,
  116,   56,  117,  122,  119,    3,    4,    5,  121,   60,
  115,   72,  114,   85,   18,  121,   66,  115,   95,  114,
   87,   64,   67,   98,   73,   86,   64,   64,  120,   64,
   64,   64,   21,  118,  116,   92,  117,  122,  119,  151,
   74,  121,  130,   93,   64,   64,   64,   85,  121,   99,
   94,  115,  123,  114,   87,  124,    3,    4,    5,   86,
    3,    4,    5,    7,  133,   68,   68,   68,   68,   68,
   68,   51,  150,  162,   51,   64,   50,   50,   50,   50,
   50,   50,  121,   17,  156,   85,   18,   12,   44,   51,
  120,   51,   87,   68,   44,  118,  116,   86,  117,  122,
  119,   41,  161,   47,   47,   47,   47,   47,   47,    0,
    0,    0,   30,  115,    0,  114,    0,   92,   92,   30,
    0,    0,    0,   51,   30,    0,   92,   92,    0,   48,
   48,   48,   48,   48,   48,    0,    0,  157,   49,   49,
   49,   49,   49,   49,  121,    0,    0,    0,    0,  120,
    3,    4,    5,    0,  118,  116,   11,  117,  122,  119,
    0,    0,    0,    0,  107,  108,  109,  110,  111,  112,
    0,   39,  115,   62,  114,    0,   65,  163,   39,    0,
   71,   40,    0,   39,    0,    0,   75,    0,    0,    0,
  107,  108,  109,  110,  111,  112,    0,  107,  108,  109,
  110,  111,  112,  121,   30,    0,   61,    3,    4,    5,
    0,    6,    0,   69,    7,    0,    0,    0,    0,    0,
    0,    0,    0,  107,  108,  109,  110,  111,  112,    0,
  107,  108,  109,  110,  111,  112,    0,   76,   77,   78,
    0,  105,   85,   79,    0,   80,    0,    0,   81,   87,
   82,   83,   84,    0,   86,    0,    0,   64,   64,   64,
   64,   64,   64,   39,  107,  108,  109,  110,  111,  112,
    0,   76,   77,   78,    0,    0,    0,   79,    0,   80,
   54,   85,   81,   54,   82,   83,   84,   45,   87,   45,
   45,   45,    0,   86,   57,    0,    0,   57,   54,   54,
   54,   54,    0,   51,   51,   45,   45,   45,   45,   76,
   77,   78,   57,    0,   57,   79,    0,   80,    0,    0,
   81,    0,   82,   83,   84,    0,  107,  108,  109,  110,
  111,  112,   54,    0,    0,    0,   30,   30,   30,   45,
   52,    0,   30,   52,   30,    0,   57,   30,    0,   30,
   30,   30,   46,    0,   46,   46,   46,    0,   52,   52,
   52,   52,   53,    0,    0,   53,    0,    0,    0,  120,
   46,   46,   46,   46,  118,    0,    0,    0,  122,  119,
   53,   53,   53,   53,    0,    0,   85,  109,  110,  111,
  112,    0,   52,   87,  125,   39,   39,   39,   86,   55,
    0,   39,   55,   39,   46,    0,   39,    0,   39,   39,
   39,    0,   58,    0,   53,   58,    0,   55,   55,   55,
   55,   59,    0,  121,   59,   63,    0,    0,   63,    0,
   58,   58,   58,   58,   56,    0,    0,   56,    0,   59,
   59,   59,   59,   63,    0,   63,    0,    0,    0,    0,
    0,   55,   56,    0,   56,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,    0,   76,   77,   78,    3,
    4,    5,   79,   59,    0,    0,    0,   63,    0,    0,
    0,    0,    0,    0,    0,    0,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   76,   77,   78,    0,    0,
    0,   79,   54,   54,   54,   54,   54,   54,    0,   45,
   45,   45,   45,   45,   45,    0,   57,   57,   96,    0,
    0,  100,  100,  103,  104,  106,    0,    0,    0,    0,
    0,    0,    0,  100,    0,    0,  128,  129,    0,    0,
    0,    0,    0,    0,    0,  135,  136,  137,  138,  139,
  140,  141,  142,  143,  144,  145,  146,  147,  148,  149,
    0,    0,   52,   52,   52,   52,   52,   52,  154,    0,
    0,  155,    0,    0,   46,   46,   46,   46,   46,   46,
    0,    0,    0,    0,   53,   53,   53,   53,   53,   53,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   76,   77,   78,    0,    0,    0,   79,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,   55,   55,   55,   55,   55,    0,    0,    0,
    0,    0,    0,    0,   58,   58,   58,   58,   58,   58,
    0,    0,    0,   59,   59,   59,   59,   59,   59,    0,
    0,    0,    0,    0,    0,    0,   56,   56,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   12,   70,  123,   41,   42,   43,   44,   45,   46,   47,
   37,   41,   91,   46,   41,   42,   43,   44,   45,  263,
   47,   59,   60,   61,   62,   44,   91,   84,   40,   41,
   91,   91,   59,   60,   61,   62,   41,   37,   95,   44,
   59,   41,   42,   43,   44,   45,   41,   47,  153,   44,
   44,   40,   40,   91,   59,   93,   40,  162,   91,   59,
   60,   61,   62,   37,   59,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   44,   41,   42,   43,   44,
   45,  257,   47,  257,   44,   59,   60,   61,   62,   59,
   59,  125,  161,   93,   59,   60,   61,   62,   37,   59,
  257,   41,   44,   42,   43,   37,   45,   46,   47,  263,
   42,   43,  266,   45,   46,   47,   41,   59,   93,   93,
  263,   60,   61,   62,   37,   93,   93,   44,   93,   42,
   43,   37,   45,   46,   47,   41,   42,   43,  123,   45,
   46,   47,   59,  260,  261,  262,   59,   60,   41,   62,
  267,   44,   91,  263,   60,   41,   62,   37,   44,   91,
  123,   41,   42,   43,   37,   45,   46,   47,   41,   42,
   43,  123,   45,   46,   47,  260,  261,  262,   91,  123,
   60,  263,   62,   33,  263,   91,  123,   60,   40,   62,
   40,   37,  125,   40,  125,   45,   42,   43,   37,   45,
   46,   47,  263,   42,   43,   70,   45,   46,   47,   41,
  125,   91,   44,  125,   60,   61,   62,   33,   91,   40,
  125,   60,   59,   62,   40,   59,  260,  261,  262,   45,
  260,  261,  262,  267,   41,  273,  274,  275,  276,  277,
  278,   41,  263,  269,   44,   91,  273,  274,  275,  276,
  277,  278,   91,  263,   93,   33,  263,  125,   59,   59,
   37,   61,   40,   59,   28,   42,   43,   45,   45,   46,
   47,   27,  157,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   33,   60,   -1,   62,   -1,  152,  153,   40,
   -1,   -1,   -1,   93,   45,   -1,  161,  162,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,  123,  273,  274,
  275,  276,  277,  278,   91,   -1,   -1,   -1,   -1,   37,
  260,  261,  262,   -1,   42,   43,    2,   45,   46,   47,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   33,   60,   53,   62,   -1,   56,  125,   40,   -1,
   60,   27,   -1,   45,   -1,   -1,   66,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,  273,  274,  275,
  276,  277,  278,   91,  125,   -1,   52,  260,  261,  262,
   -1,  264,   -1,   59,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
  273,  274,  275,  276,  277,  278,   -1,  257,  258,  259,
   -1,   87,   33,  263,   -1,  265,   -1,   -1,  268,   40,
  270,  271,  272,   -1,   45,   -1,   -1,  273,  274,  275,
  276,  277,  278,  125,  273,  274,  275,  276,  277,  278,
   -1,  257,  258,  259,   -1,   -1,   -1,  263,   -1,  265,
   41,   33,  268,   44,  270,  271,  272,   41,   40,   43,
   44,   45,   -1,   45,   41,   -1,   -1,   44,   59,   60,
   61,   62,   -1,  273,  274,   59,   60,   61,   62,  257,
  258,  259,   59,   -1,   61,  263,   -1,  265,   -1,   -1,
  268,   -1,  270,  271,  272,   -1,  273,  274,  275,  276,
  277,  278,   93,   -1,   -1,   -1,  257,  258,  259,   93,
   41,   -1,  263,   44,  265,   -1,   93,  268,   -1,  270,
  271,  272,   41,   -1,   43,   44,   45,   -1,   59,   60,
   61,   62,   41,   -1,   -1,   44,   -1,   -1,   -1,   37,
   59,   60,   61,   62,   42,   -1,   -1,   -1,   46,   47,
   59,   60,   61,   62,   -1,   -1,   33,  275,  276,  277,
  278,   -1,   93,   40,   41,  257,  258,  259,   45,   41,
   -1,  263,   44,  265,   93,   -1,  268,   -1,  270,  271,
  272,   -1,   41,   -1,   93,   44,   -1,   59,   60,   61,
   62,   41,   -1,   91,   44,   41,   -1,   -1,   44,   -1,
   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,   59,
   60,   61,   62,   59,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   93,   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,  257,  258,  259,  260,
  261,  262,  263,   93,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,
   -1,  263,  273,  274,  275,  276,  277,  278,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,   80,   -1,
   -1,   83,   84,   85,   86,   87,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   95,   -1,   -1,   98,   99,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  107,  108,  109,  110,  111,
  112,  113,  114,  115,  116,  117,  118,  119,  120,  121,
   -1,   -1,  273,  274,  275,  276,  277,  278,  130,   -1,
   -1,  133,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,   -1,   -1,   -1,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","CHAR_CONSTANT",
"REAL_CONSTANT","INT","DOUBLE","CHAR","ID","VOID","RETURN","MAIN","STRUCT","IF",
"ELSE","WHILE","READ","WRITE","AND","OR","EQUALS","LTOE","GTOE","NOTEQUALS",
"IFX","CAST","FIELD","UNARY_MINUS",
};
final static String yyrule[] = {
"$accept : final",
"final : program main",
"main : VOID MAIN '(' ')' '{' body '}'",
"program : program variable_definition",
"program : program function",
"program :",
"function : built_in_type ID '(' ')' '{' body '}'",
"function : VOID ID '(' ')' '{' body '}'",
"function : built_in_type ID '(' parameter ')' '{' body '}'",
"function : VOID ID '(' parameter ')' '{' body '}'",
"parameter : parameter ',' built_in_type ID",
"parameter : built_in_type ID",
"body : variable_definition_sequence statement_sequence",
"variable_definition_sequence : variable_definition_sequence variable_definition",
"variable_definition_sequence :",
"variable_definition : built_in_type id_enumeration ';'",
"variable_definition : array_struct id_enumeration ';'",
"array_struct : array_constructor",
"array_struct : struct_definition",
"struct_definition : STRUCT '{' struct_body '}'",
"struct_body : struct_body recordField_definition",
"struct_body :",
"recordField_definition : built_in_type id_enumeration ';'",
"recordField_definition : array_struct id_enumeration ';'",
"id_enumeration : id_enumeration ',' ID",
"id_enumeration : ID",
"array_constructor : array_constructor '[' INT_CONSTANT ']'",
"array_constructor : built_in_type '[' INT_CONSTANT ']'",
"array_constructor : struct_definition '[' INT_CONSTANT ']'",
"statement_sequence : statement_sequence statement",
"statement_sequence :",
"statement : if_statement",
"statement : WHILE '(' expression ')' if_while_body",
"statement : assigment ';'",
"statement : WRITE expression_sequence ';'",
"statement : READ expression_sequence ';'",
"statement : function_invocation ';'",
"statement : RETURN expression ';'",
"if_statement : IF '(' expression ')' if_while_body ELSE if_while_body",
"if_statement : IF '(' expression ')' if_while_body",
"if_while_body : '{' statement_sequence '}'",
"if_while_body : statement",
"expression_sequence : expression_sequence ',' expression",
"expression_sequence : expression",
"assigment : expression '=' expression",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : '-' expression",
"expression : '!' expression",
"expression : expression LTOE expression",
"expression : expression GTOE expression",
"expression : expression EQUALS expression",
"expression : expression NOTEQUALS expression",
"expression : expression OR expression",
"expression : expression AND expression",
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression '.' ID",
"expression : expression '[' expression ']'",
"expression : '(' expression ')'",
"expression : '(' built_in_type ')' expression",
"expression : function_invocation",
"expression : CHAR_CONSTANT",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : ID",
"function_invocation : ID '(' expression_sequence ')'",
"function_invocation : ID '(' ')'",
"built_in_type : CHAR",
"built_in_type : INT",
"built_in_type : DOUBLE",
};

//#line 209 "../../src/parser/parser.y"

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
//#line 532 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 36 "../../src/parser/parser.y"
{List<Definition> program = (List<Definition>)val_peek(1);
																	program.add((FuncDefinition) val_peek(0));
																	ast = new Program(scanner.getLine(), scanner.getColumn(),program);}
break;
case 2:
//#line 41 "../../src/parser/parser.y"
{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),new ArrayList<>());
																	yyval = new FuncDefinition(scanner.getLine(), scanner.getColumn(),"main",funcType,(List<Statement>)val_peek(1));}
break;
case 3:
//#line 44 "../../src/parser/parser.y"
{List<Definition> program = (List<Definition>)val_peek(1);
	   																program.addAll((List<Definition>)val_peek(0));
	   																yyval = program;}
break;
case 4:
//#line 47 "../../src/parser/parser.y"
{List<Definition> program = (List<Definition>)val_peek(1);
	   																program.add((Definition)val_peek(0));
	   																yyval = program;}
break;
case 5:
//#line 50 "../../src/parser/parser.y"
{yyval = new ArrayList<>();}
break;
case 6:
//#line 52 "../../src/parser/parser.y"
{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),(Type)val_peek(6),new ArrayList<>());
																	yyval = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(5),funcType,(List<Statement>)val_peek(1));}
break;
case 7:
//#line 54 "../../src/parser/parser.y"
{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),new ArrayList<>());
																	yyval = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(5),funcType,(List<Statement>)val_peek(1));}
break;
case 8:
//#line 56 "../../src/parser/parser.y"
{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),(Type)val_peek(7),(List<VarDefinition>)val_peek(4));
																	yyval = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(6),funcType,(List<Statement>)val_peek(1));}
break;
case 9:
//#line 58 "../../src/parser/parser.y"
{FuncType funcType = new FuncType(scanner.getLine(), scanner.getColumn(),VoidType.getInstance(),(List<VarDefinition>)val_peek(4));
																	yyval = new FuncDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(6),funcType,(List<Statement>)val_peek(1));}
break;
case 10:
//#line 62 "../../src/parser/parser.y"
{List<VarDefinition> variables = (List<VarDefinition>)val_peek(3);
		 															variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(0),(Type)val_peek(1)));
		 															yyval = variables;}
break;
case 11:
//#line 65 "../../src/parser/parser.y"
{List<VarDefinition> variables = new ArrayList<>();
		 															variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(0),(Type)val_peek(1)));
		 															yyval = variables;}
break;
case 12:
//#line 70 "../../src/parser/parser.y"
{List<Statement> statements = new ArrayList<>();
																	statements.addAll((List<VarDefinition>)val_peek(1));
																	statements.addAll((List<Statement>)val_peek(0));
																	yyval=statements; }
break;
case 13:
//#line 76 "../../src/parser/parser.y"
{((List<VarDefinition>)val_peek(1)).addAll((List<VarDefinition>)val_peek(0));
																						yyval = val_peek(1);}
break;
case 14:
//#line 78 "../../src/parser/parser.y"
{yyval = new ArrayList<>();}
break;
case 15:
//#line 81 "../../src/parser/parser.y"
{ List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)val_peek(1))
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)val_peek(2)));
															yyval = variables;
															}
break;
case 16:
//#line 86 "../../src/parser/parser.y"
{ List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)val_peek(1))
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)val_peek(2)));
															yyval = variables;
															}
break;
case 17:
//#line 93 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 18:
//#line 94 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 19:
//#line 97 "../../src/parser/parser.y"
{yyval = new Struct(scanner.getLine(), scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 20:
//#line 100 "../../src/parser/parser.y"
{for(RecordField r : (List<RecordField>)val_peek(0)){
														if(((List<RecordField>)val_peek(1)).contains(r))
															new ErrorType(scanner.getLine(), scanner.getColumn(),"id repetido:"+r.getName());
														((List<RecordField>)val_peek(1)).add(r);
													  }	
													  yyval = val_peek(1);}
break;
case 21:
//#line 106 "../../src/parser/parser.y"
{yyval = new ArrayList<>();}
break;
case 22:
//#line 109 "../../src/parser/parser.y"
{ List<RecordField> records = new ArrayList<>();
															for(String id : (List<String>)val_peek(1))
																records.add(new RecordField(scanner.getLine(), scanner.getColumn(),id,(Type)val_peek(2)));
															yyval = records;
															}
break;
case 23:
//#line 114 "../../src/parser/parser.y"
{ List<VarDefinition> variables = new ArrayList<>();
															for(String id : (List<String>)val_peek(1))
																variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(),id,(Type)val_peek(2)));
															yyval = variables;
															}
break;
case 24:
//#line 121 "../../src/parser/parser.y"
{List<String> ids = (List<String>)val_peek(2);
															if(ids.contains((String)val_peek(0)))
																new ErrorType(scanner.getLine(), scanner.getColumn(),"id repetido:"+(String)val_peek(0));
															ids.add((String)val_peek(0));
															yyval = ids;}
break;
case 25:
//#line 126 "../../src/parser/parser.y"
{yyval = new ArrayList<>();
			  												((List<String>)yyval).add((String)val_peek(0));}
break;
case 26:
//#line 130 "../../src/parser/parser.y"
{yyval = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)val_peek(1),(Type)val_peek(3));}
break;
case 27:
//#line 131 "../../src/parser/parser.y"
{yyval = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)val_peek(1),(Type)val_peek(3));}
break;
case 28:
//#line 132 "../../src/parser/parser.y"
{yyval = Array.createOrderedArray(scanner.getLine(), scanner.getColumn(),(int)val_peek(1),(Type)val_peek(3));}
break;
case 29:
//#line 135 "../../src/parser/parser.y"
{((List<Statement>)val_peek(1)).add((Statement)val_peek(0));
																yyval = val_peek(1);}
break;
case 30:
//#line 137 "../../src/parser/parser.y"
{List<Statement> statements = new ArrayList<>();
				   												yyval = statements;}
break;
case 31:
//#line 141 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 32:
//#line 142 "../../src/parser/parser.y"
{yyval = new WhileStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),(List<Statement>)val_peek(0));}
break;
case 33:
//#line 143 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 34:
//#line 144 "../../src/parser/parser.y"
{yyval = new Write(scanner.getLine(), scanner.getColumn(),(List<Expression>)val_peek(1));}
break;
case 35:
//#line 145 "../../src/parser/parser.y"
{yyval = new Read(scanner.getLine(), scanner.getColumn(),(List<Expression>)val_peek(1));}
break;
case 36:
//#line 146 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 37:
//#line 147 "../../src/parser/parser.y"
{yyval = new Return(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(1));}
break;
case 38:
//#line 151 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(List<Statement>)val_peek(2),(List<Statement>)val_peek(0));}
break;
case 39:
//#line 152 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),(List<Statement>)val_peek(0));}
break;
case 40:
//#line 154 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 41:
//#line 155 "../../src/parser/parser.y"
{List<Statement> statements= new ArrayList<>();
																statements.add((Statement)val_peek(0));
																yyval=statements;}
break;
case 42:
//#line 161 "../../src/parser/parser.y"
{((List<Expression>)val_peek(2)).add((Expression)val_peek(0));
																yyval = val_peek(2);}
break;
case 43:
//#line 163 "../../src/parser/parser.y"
{List<Expression> expressions = new ArrayList<>();
				   												expressions.add((Expression)val_peek(0));
				   												yyval = expressions;}
break;
case 44:
//#line 168 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),(Expression)val_peek(0));}
break;
case 45:
//#line 171 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0));}
break;
case 46:
//#line 172 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0));}
break;
case 47:
//#line 173 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0));}
break;
case 48:
//#line 174 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0));}
break;
case 49:
//#line 175 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"%",(Expression)val_peek(0));}
break;
case 50:
//#line 176 "../../src/parser/parser.y"
{yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(0));}
break;
case 51:
//#line 177 "../../src/parser/parser.y"
{yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(0));}
break;
case 52:
//#line 178 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"<=",(Expression)val_peek(0));}
break;
case 53:
//#line 179 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),">=",(Expression)val_peek(0));}
break;
case 54:
//#line 180 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"==",(Expression)val_peek(0));}
break;
case 55:
//#line 181 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"!=",(Expression)val_peek(0));}
break;
case 56:
//#line 182 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"||",(Expression)val_peek(0));}
break;
case 57:
//#line 183 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"$$",(Expression)val_peek(0));}
break;
case 58:
//#line 184 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),">",(Expression)val_peek(0));}
break;
case 59:
//#line 185 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),"<",(Expression)val_peek(0));}
break;
case 60:
//#line 186 "../../src/parser/parser.y"
{yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),(String)val_peek(0));}
break;
case 61:
//#line 187 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(3),(Expression)val_peek(1));}
break;
case 62:
//#line 188 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 63:
//#line 189 "../../src/parser/parser.y"
{yyval = new Cast(scanner.getLine(), scanner.getColumn(),(Type)val_peek(2),(Expression)val_peek(0));}
break;
case 64:
//#line 190 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 65:
//#line 191 "../../src/parser/parser.y"
{yyval = new CharLiteral(scanner.getLine(),scanner.getColumn(),(char)val_peek(0));}
break;
case 66:
//#line 192 "../../src/parser/parser.y"
{yyval = new IntLiteral(scanner.getLine(),scanner.getColumn(),(int)val_peek(0));}
break;
case 67:
//#line 193 "../../src/parser/parser.y"
{yyval = new RealLiteral(scanner.getLine(),scanner.getColumn(),(double)val_peek(0));}
break;
case 68:
//#line 194 "../../src/parser/parser.y"
{yyval = new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(0));}
break;
case 69:
//#line 197 "../../src/parser/parser.y"
{Variable variable = new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(3));	
															yyval = new Invocation(scanner.getLine(),scanner.getColumn(),variable,(List<Expression>)val_peek(1));}
break;
case 70:
//#line 199 "../../src/parser/parser.y"
{Variable variable = new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(2));
				   											yyval = new Invocation(scanner.getLine(),scanner.getColumn(),variable,new ArrayList<>());}
break;
case 71:
//#line 203 "../../src/parser/parser.y"
{yyval = Char.getInstance();}
break;
case 72:
//#line 204 "../../src/parser/parser.y"
{yyval = Int.getInstance();}
break;
case 73:
//#line 205 "../../src/parser/parser.y"
{yyval = Real.getInstance();}
break;
//#line 1027 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
