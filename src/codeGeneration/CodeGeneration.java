package codeGeneration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGeneration {

	private BufferedWriter w;

	public CodeGeneration(String input, String ouput) {
		try {
			w = new BufferedWriter(new FileWriter(ouput));
			w.write("#source \"" + input + "\"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void callMain() {
		try {
			w.write("' Invocation to the main function\n");
			w.write("call main\n");
			w.write("halt\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void comment(String comment) {
		try {
			w.write("\t' " + comment + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void out(char suffix) {
		try {
			w.write("\tout" + suffix);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void in(char suffix) {
		try {
			w.write("\tin" + suffix);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void store(char suffix) {
		try {
			w.write("\tstore" + suffix);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void convertTo(char s1, char s2) {
		try {
			if (s1 != s2) {
				if (s1 == 'b')
					w.write("\tb2i\n");

				if (s2 == 'f')
					w.write("\ti2f\n");
				
				if (s1 == 'f')
					w.write("\tf2i\n");
				
				if (s2 == 'b')
					w.write("\ti2b\n");
	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void push(int value) {
		try {
			w.write("\tpush " + value);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void push(char value) {
		try {
			w.write("\tpushb " + (int) value);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void push(double value) {
		try {
			w.write("\tpushf " + value);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(char suffix) {
		try {
			w.write("\tload" + suffix);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void arithmetic(String operand, char suffix) {
		try {
			switch (operand) {
			case "+":
				w.write("\tadd" + suffix);
				break;

			case "-":
				w.write("\tsub" + suffix);
				break;

			case "*":
				w.write("\tmul" + suffix);
				break;

			case "/":
				w.write("\tdiv" + suffix);
				break;
			case "%":
				w.write("\tmod" + suffix);
				break;

			default:
				break;
			}
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void comparison(String operator, char suffix) {
		try {
			switch (operator) {
			case ">":
				w.write("\tgt" + suffix);
				break;

			case "<":
				w.write("\tlt" + suffix);
				break;

			case ">=":
				w.write("\tge" + suffix);
				break;

			case "<=":
				w.write("\tle" + suffix);
				break;

			case "==":
				w.write("\teq" + suffix);
				break;

			case "!=":
				w.write("\tne" + suffix);
				break;

			default:
				break;
			}
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pushAddress(int offset) {
		try {
			w.write("\tpusha " + offset);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pushBp() {
		try {
			w.write("\tpush bp");
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newFunction(String name, int numBytes) {
		try {
			w.write(name + ":");
			w.newLine();
			w.write("enter "+numBytes);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logical(String operator) {
		try {
			switch (operator) {
			case "&&":
				w.write("\tand");
				break;

			case "||":
				w.write("\tor");
				break;

			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void not() {
		try {
			w.write("\tnot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newLine(int lineNumber){
		try {
			w.write("\n#line "+lineNumber+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
